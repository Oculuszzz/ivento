/**
 * 
 */
package com.web.springboot.ivento.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.springboot.ivento.payload.request.SignupRequest;
import com.web.springboot.ivento.payload.request.UserRequest;
import com.web.springboot.ivento.payload.response.MessageResponse;
import com.web.springboot.ivento.payload.response.UserResponse;
import com.web.springboot.ivento.properties.Literals;
import com.web.springboot.ivento.service.user.UserServiceImpl;

import jakarta.validation.Valid;

/**
 * @author mokht
 *
 */
//@CrossOrigin(origins = "http://localhost:8091") // Already define in application properties
@CrossOrigin
@RestController
@RequestMapping("/api/auth/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserServiceImpl userService;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserRequest>> findAll() {

		return new ResponseEntity<>(userService.findAllUser(), HttpStatus.OK);

	}

	@GetMapping(value = "/find-user")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<UserResponse> findById(@RequestParam Long id) {

		return new ResponseEntity<>(userService.findUserResponseById(id), HttpStatus.OK);

	}

	@PutMapping(value = "update-user")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<MessageResponse> update(@Valid @RequestBody UserRequest userRequest) {

		userService.updateUser(userRequest);

		return ResponseEntity.ok(new MessageResponse(Literals.UPDATE_USER_OK));

	}

	@PostMapping(value = "add-new-user")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> addNew(@Valid @RequestBody SignupRequest signupRequest) {

		if (Boolean.TRUE.equals(userService.existsByUsername(signupRequest.getUsername()))) {

			return ResponseEntity.badRequest().body(new MessageResponse(Literals.ERROR_AUTH_1));

		}

		if (Boolean.TRUE.equals(userService.existsByEmail(signupRequest.getEmail()))) {

			return ResponseEntity.badRequest().body(new MessageResponse(Literals.ERROR_AUTH_2));

		}

		userService.addNewUser(signupRequest);

		return ResponseEntity.ok(new MessageResponse(Literals.ADD_NEW_USER_OK));

	}

	@PutMapping(value = "disable-account")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> disableAccount(@Valid @RequestBody UserRequest userRequest) {

		userService.disableAccountById(userRequest.getId());

		return ResponseEntity.ok(new MessageResponse(Literals.UPDATE_USER_OK));

	}

	@PutMapping(value = "enable-account")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> enableAccount(@Valid @RequestBody UserRequest userRequest) {

		userService.enableAccountById(userRequest.getId());

		return ResponseEntity.ok(new MessageResponse(Literals.UPDATE_USER_OK));

	}

}
