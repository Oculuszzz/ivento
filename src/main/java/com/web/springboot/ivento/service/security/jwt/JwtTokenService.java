/**
 * 
 */
package com.web.springboot.ivento.service.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.springboot.ivento.model.JwtTokenEntity;
import com.web.springboot.ivento.properties.Literals;
import com.web.springboot.ivento.repository.JwtTokenRepository;
import com.web.springboot.ivento.service.exception.TokenRefreshException;
import com.web.springboot.ivento.service.security.UserDetailsImpl;
import com.web.springboot.ivento.service.user.UserServiceImpl;
import com.web.springboot.ivento.utils.MessageUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * @author mokht
 *
 *         A JSON Web Token (JWT) utilise class. This class responsibilities to
 *         generate JWT and do validation.
 *
 */
@Service
public class JwtTokenService {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenService.class);

	@Value("${ivento.app.jwtSecret}") // get value from properties file
	private String jwtSecret;

	@Value("${ivento.app.jwtExpiration}") // get value from properties file
	private Long jwtExpiration;

	@Value("${ivento.app.jwtRefreshExpiration}") // get value from properties file
	private Long refreshTokenExpiration;

	private final JwtTokenRepository jwtTokenRepository;

	private final UserServiceImpl userServiceImpl;

	private final MessageUtils messageUtils;

	/**
	 * @param jwtTokenRepository
	 * @param userServiceImpl
	 * @param messageUtils
	 */
	public JwtTokenService(JwtTokenRepository jwtTokenRepository, UserServiceImpl userServiceImpl,
			MessageUtils messageUtils) {
		this.jwtTokenRepository = jwtTokenRepository;
		this.userServiceImpl = userServiceImpl;
		this.messageUtils = messageUtils;
	}

	public String generateJWTToken(UserDetailsImpl userDetails) {

		return generateJWTToken(userDetails, new HashMap<>());

	}

	public String generateJWTToken(UserDetailsImpl userDetails, Map<String, Object> extraClaims) {

		Key key = getSigningKey();
		JwtBuilder jwtBuilder = Jwts.builder();
		jwtBuilder.setClaims(extraClaims); // Always set claims first before set subject. Or else it's will causes
											// problem when trying decode the token.
		jwtBuilder.setSubject((userDetails.getUsername()));
//		jwtBuilder.setIssuer(issuerUrl);
		jwtBuilder.setIssuedAt(new Date()); // Set current time
		jwtBuilder.setExpiration(new Date((new Date()).getTime() + jwtExpiration)); // Set
		// time
		// to be
		// expected JWT
		// expired
		jwtBuilder.signWith(key, SignatureAlgorithm.HS256); // Decode token to SHA-256

		return jwtBuilder.compact();

	}

	public String generateJWTRefreshToken(UserDetailsImpl userDetails) {

		Key key = getSigningKey();
		JwtBuilder jwtBuilder = Jwts.builder();
		jwtBuilder.setSubject((userDetails.getUsername()));
		jwtBuilder.setIssuedAt(new Date()); // Set current time
		jwtBuilder.setExpiration(new Date((new Date()).getTime() + refreshTokenExpiration)); // Set
		// time
		// to be
		// expected JWT
		// expired
		jwtBuilder.signWith(key, SignatureAlgorithm.HS256); // Decode token to SHA-256

		return jwtBuilder.compact();

	}

	public boolean validateJWTToken(String authToken) {

		boolean tokenOK = false;

		try {

			Key key = getSigningKey();

			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);

			tokenOK = true;

		} catch (ExpiredJwtException e) {

			logger.error(String.format("JwtUtils.validateJWTToken() : JWT Token is expired - %s", e.getMessage()));

		} catch (UnsupportedJwtException e) {

			logger.error(String.format("JwtUtils.validateJWTToken() : Unsupported JWT Token - %s", e.getMessage()));

		} catch (MalformedJwtException e) {

			logger.error(String.format("JwtUtils.validateJWTToken() : Invalid JWT Token format - %s", e.getMessage()));

		} catch (IllegalArgumentException e) {

			logger.error(
					String.format("JwtUtils.validateJWTToken() : Invalid JWT Token argument - %s", e.getMessage()));

		}

		return tokenOK;

	}

	public Boolean validateRefreshToken(String refreshToken) {

		Boolean tokenOK = Boolean.FALSE;

		Optional<JwtTokenEntity> jwtTokenEntity = jwtTokenRepository.findByRefreshToken(refreshToken);

		// Compare sender refresh token and token exists in DB
		if (jwtTokenEntity.isEmpty() || !jwtTokenEntity.get().getRefreshToken().equals(refreshToken)) {

			return tokenOK;

		}

		try {

			Key key = getSigningKey();

			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(refreshToken);

			tokenOK = Boolean.TRUE;

		} catch (ExpiredJwtException e) {

			logger.error(String.format("JwtUtils.validateJWTToken() : Refresh Token is expired - %s", e.getMessage()));

			// Throw Refresh Token Expired
			throw new TokenRefreshException(refreshToken,
					messageUtils.getMessage(Literals.ERROR_REFRESH_TOKEN_EXPIRED));

		} catch (UnsupportedJwtException e) {

			logger.error(String.format("JwtUtils.validateJWTToken() : Unsupported Refresh Token - %s", e.getMessage()));

			throw new TokenRefreshException(refreshToken,
					messageUtils.getMessage(Literals.ERROR_INVALID_REFRESH_TOKEN));

		} catch (MalformedJwtException e) {

			logger.error(
					String.format("JwtUtils.validateJWTToken() : Invalid Refresh Token format - %s", e.getMessage()));

			throw new TokenRefreshException(refreshToken,
					messageUtils.getMessage(Literals.ERROR_INVALID_REFRESH_TOKEN));

		} catch (IllegalArgumentException e) {

			logger.error(
					String.format("JwtUtils.validateJWTToken() : Invalid Refresh Token argument - %s", e.getMessage()));

			throw new TokenRefreshException(refreshToken,
					messageUtils.getMessage(Literals.ERROR_INVALID_REFRESH_TOKEN));

		}

		return tokenOK;

	}

	public Optional<JwtTokenEntity> findByRefreshToken(String token) {
		return jwtTokenRepository.findByRefreshToken(token);
	}

	public String getUsernameFromToken(String token) {

		return getClaim(token, Claims::getSubject);

	}

	public Date getExpirationFromToken(String token) {

		return getClaim(token, Claims::getExpiration);

	}

	public <T> T getClaim(String token, Function<Claims, T> claimResolver) {

		final Claims claims = getAllClaims(token);

		return claimResolver.apply(claims);

	}

	private Claims getAllClaims(String token) {

		Key key = getSigningKey();

		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

	}

	/**
	 * @return
	 */
	private Key getSigningKey() {

		byte[] base64BytesKey = Decoders.BASE64URL.decode(jwtSecret);

		return Keys.hmacShaKeyFor(base64BytesKey);

	}

	@Transactional
	public int deleteByUserId(Long userId) {

		return jwtTokenRepository.deleteByUser(userServiceImpl.findUserEntityById(userId));

	}

}
