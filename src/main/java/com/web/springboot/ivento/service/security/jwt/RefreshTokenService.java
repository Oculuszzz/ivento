/**
 * 
 */
package com.web.springboot.ivento.service.security.jwt;

import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.web.springboot.ivento.component.utils.MessageUtils;
import com.web.springboot.ivento.model.JwtTokenEntity;
import com.web.springboot.ivento.model.UserEntity;
import com.web.springboot.ivento.payload.response.TokenRefreshResponse;
import com.web.springboot.ivento.properties.Literals;
import com.web.springboot.ivento.repository.JwtTokenRepository;
import com.web.springboot.ivento.service.exception.TokenRefreshException;
import com.web.springboot.ivento.service.security.UserDetailsImpl;
import com.web.springboot.ivento.service.user.UserServiceImpl;

/**
 * @author mokht
 *
 */
@Service
public class RefreshTokenService {

	private final JwtTokenRepository jwtTokenRepository;

	private final JwtTokenService jwtTokenService;

	private final UserDetailsService userDetailsService;

	private final UserServiceImpl userServiceImpl;

	private final MessageUtils messageUtils;

	/**
	 * @param jwtTokenRepository
	 * @param jwtTokenService
	 * @param userDetailsService
	 * @param userServiceImpl
	 * @param messageUtils
	 */
	public RefreshTokenService(JwtTokenRepository jwtTokenRepository, JwtTokenService jwtTokenService,
			UserDetailsService userDetailsService, UserServiceImpl userServiceImpl, MessageUtils messageUtils) {
		this.jwtTokenRepository = jwtTokenRepository;
		this.jwtTokenService = jwtTokenService;
		this.userDetailsService = userDetailsService;
		this.userServiceImpl = userServiceImpl;
		this.messageUtils = messageUtils;
	}

	public TokenRefreshResponse validateAndGenerateAccessToken(String refreshToken) {

		if (Boolean.TRUE.equals(jwtTokenService.validateRefreshToken(refreshToken))) {

			UserDetails userDetails = userDetailsService
					.loadUserByUsername(jwtTokenService.getUsernameFromToken(refreshToken));

			// Generate new access token
			String newAccessToken = jwtTokenService.generateJWTToken((UserDetailsImpl) userDetails);
			UserEntity userEntity = userServiceImpl.findUserEntityByUsername(userDetails.getUsername());

			// Delete old tokens from DB
			jwtTokenService.deleteByUserId(userEntity.getId());

			// Save token into db
			jwtTokenRepository.save(new JwtTokenEntity(newAccessToken, refreshToken, LocalDateTime.now(), userEntity));

			return new TokenRefreshResponse(newAccessToken, refreshToken, userEntity.getId(), userEntity.getUsername(),
					userEntity.getEmail(), userEntity.isBlocked(), userEntity.getImage(), userEntity.getRole().name(),
					userEntity.getLastLoggedIn(), userEntity.getLastUpdated());

		} else {

			throw new TokenRefreshException(refreshToken,
					messageUtils.getMessage(Literals.ERROR_INVALID_REFRESH_TOKEN));

		}

	}

}
