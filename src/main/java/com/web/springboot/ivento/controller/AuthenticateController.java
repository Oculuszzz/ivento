package com.web.springboot.ivento.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.springboot.ivento.payload.request.AuthenticationRequest;
import com.web.springboot.ivento.payload.request.TokenRefreshRequest;
import com.web.springboot.ivento.payload.response.AuthenticationResponse;
import com.web.springboot.ivento.payload.response.TokenRefreshResponse;
import com.web.springboot.ivento.service.security.authentication.AuthenticationService;
import com.web.springboot.ivento.service.security.jwt.RefreshTokenService;

import jakarta.validation.Valid;

/**
 * @author mokht
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticateController {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticateController.class);

	private final AuthenticationService authenticationService;

	private final RefreshTokenService refreshTokenService;

	/**
	 * @param authenticationService
	 * @param refreshTokenService
	 * @param messageUtils
	 */
	public AuthenticateController(AuthenticationService authenticationService,
			RefreshTokenService refreshTokenService) {
		this.authenticationService = authenticationService;
		this.refreshTokenService = refreshTokenService;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticateUser(
			@Valid @RequestBody AuthenticationRequest authenticationRequest) {

		return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));

	}

	@PostMapping("/refreshtoken")
	public ResponseEntity<TokenRefreshResponse> refreshtoken(@Valid @RequestBody TokenRefreshRequest requestBody) {

		String requestRefreshToken = requestBody.getRefreshToken();

		return ResponseEntity.ok(refreshTokenService.validateAndGenerateAccessToken(requestRefreshToken));
	}

//	@PostMapping("/signup")
//	public ResponseEntity<AuthenticationResponse> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
//
//		return ResponseEntity.ok(authenticationService.register(signupRequest));
//
//	}

}
