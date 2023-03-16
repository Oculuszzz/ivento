/**
 * 
 */
package com.web.springboot.ivento.service.security.authentication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.springboot.ivento.model.JwtTokenEntity;
import com.web.springboot.ivento.model.UserEntity;
import com.web.springboot.ivento.payload.request.AuthenticationRequest;
import com.web.springboot.ivento.payload.request.SignupRequest;
import com.web.springboot.ivento.payload.response.AuthenticationResponse;
import com.web.springboot.ivento.repository.JwtTokenRepository;
import com.web.springboot.ivento.repository.UserRepository;
import com.web.springboot.ivento.service.security.UserDetailsImpl;
import com.web.springboot.ivento.service.security.jwt.JwtTokenService;
import com.web.springboot.ivento.service.user.UserServiceImpl;

/**
 * @author mokht
 *
 */
@Service
public class AuthenticationService {

	private final AuthenticationManager authenticationManager;

	private final UserServiceImpl userService;

	private final JwtTokenRepository jwtTokenRepository;

	private final UserRepository userRepository;

	private final PasswordEncoder encoder;

	private final JwtTokenService jwtTokenService;

	/**
	 * @param authenticationManager
	 * @param userService
	 * @param jwtTokenRepository
	 * @param userRepository
	 * @param encoder
	 * @param jwtTokenService
	 */
	public AuthenticationService(AuthenticationManager authenticationManager, UserServiceImpl userService,
			JwtTokenRepository jwtTokenRepository, UserRepository userRepository, PasswordEncoder encoder,
			JwtTokenService jwtTokenService) {
		super();
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.jwtTokenRepository = jwtTokenRepository;
		this.userRepository = userRepository;
		this.encoder = encoder;
		this.jwtTokenService = jwtTokenService;
	}

	public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		String accessToken = jwtTokenService.generateJWTToken(userDetails);
		String refreshToken = jwtTokenService.generateJWTRefreshToken(userDetails);

		UserEntity userEntity = userService.findUserEntityByUsername(authenticationRequest.getUsername());

		// Delete old tokens from DB
		jwtTokenService.deleteByUserId(userEntity.getId());

		// Update user last logged in
		userEntity.setLastLoggedIn(LocalDateTime.now());
		userEntity = userRepository.save(userEntity);

		// Save token into db
		jwtTokenRepository.save(new JwtTokenEntity(accessToken, refreshToken, LocalDateTime.now(), userEntity));

		return new AuthenticationResponse(accessToken, refreshToken, userDetails.getId(), userDetails.getUsername(),
				userDetails.getEmail(), !userDetails.isEnabled(), userDetails.getImage(), roles.get(0),
				userDetails.getLastLoggedIn(), userDetails.getLastUpdate());

	}

	public AuthenticationResponse register(SignupRequest signupRequest) {

		// Encode password user
		signupRequest.setPassword(encoder.encode(signupRequest.getPassword()));

		userService.addNewUser(signupRequest);

		UserEntity userEntity = userService.findUserEntityByUsername(signupRequest.getUsername());

		UserDetailsImpl userDetails = UserDetailsImpl.build(userEntity);
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		String accessToken = jwtTokenService.generateJWTToken(userDetails);
		String refreshToken = jwtTokenService.generateJWTRefreshToken(userDetails);

		// Save token into db
		jwtTokenRepository.save(new JwtTokenEntity(accessToken, refreshToken, LocalDateTime.now(), userEntity));

		return new AuthenticationResponse(accessToken, refreshToken, userDetails.getId(), userDetails.getUsername(),
				userDetails.getEmail(), userDetails.isEnabled(), userDetails.getImage(), roles.get(0),
				userDetails.getLastLoggedIn(), userDetails.getLastUpdate());

	}

}
