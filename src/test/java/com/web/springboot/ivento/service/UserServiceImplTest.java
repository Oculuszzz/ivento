/**
 * 
 */
package com.web.springboot.ivento.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.web.springboot.ivento.model.ERole;
import com.web.springboot.ivento.model.UserEntity;
import com.web.springboot.ivento.payload.request.SignupRequest;
import com.web.springboot.ivento.payload.request.UserRequest;
import com.web.springboot.ivento.payload.response.UserResponse;
import com.web.springboot.ivento.properties.Literals;
import com.web.springboot.ivento.repository.UserRepository;
import com.web.springboot.ivento.service.exception.UserException;
import com.web.springboot.ivento.service.exception.UserNotFoundException;
import com.web.springboot.ivento.service.user.UserServiceImpl;
import com.web.springboot.ivento.utils.MessageUtils;

/**
 * @author mokht
 * 
 *         Unit Tests using a Behavior-Driven Development style (BDD).
 *
 */
//@RunWith(MockitoJUnitRunner.class) // for JUnit 4
@ExtendWith(MockitoExtension.class) // for JUnit 5
//@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceImplTest {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);

	private UserServiceImpl underTest;

	@Mock
	private UserRepository userRepositoryTest;

	@Mock
	private MessageUtils messageUtilsTest;

	private UserRequest user;
	private UserEntity userEntity;

	private List<UserRequest> users;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		logger.info("@BeforeEach - executes before each test method in this class");

		underTest = new UserServiceImpl(userRepositoryTest, messageUtilsTest);

		// Mock user and users
		user = new UserRequest();
		user.setId(3L);
		user.setUsername("UserA");
		user.setEmail("userA@test.com");
		user.setBlocked(Boolean.FALSE);
		user.setImage("IMAGE");
		user.setRole(ERole.ROLE_USER);
		user.setLastLoggedIn(LocalDateTime.now());
		user.setLastUpdated(LocalDateTime.now());

		users = new ArrayList<>();
		users.add(user);

		userEntity = new UserEntity();
		userEntity.setId(user.getId());
		userEntity.setUsername(user.getUsername());
		userEntity.setEmail(user.getEmail());
		userEntity.setRole(user.getRole());
		userEntity.setBlocked(user.isBlocked());
		userEntity.setImage(user.getImage());
		userEntity.setLastLoggedIn(user.getLastLoggedIn());
		userEntity.setLastUpdated(user.getLastUpdated());

	}

	/**
	 * Test method for
	 * {@link com.web.springboot.ivento.service.user.UserServiceImpl#findAllUser()}.
	 */
	@Test
	void testFindAllUser() {

		// Given
		List<UserEntity> entities = new ArrayList<>();
		entities.add(userEntity);

		given(userRepositoryTest.findAll()).willReturn(entities);

		// When
		List<UserResponse> results = underTest.findAllUser();

		// Then
		assertThat(results).isNotEmpty();

	}

	@Test
	void testFindAllUser_empty() {

		// Given
		given(userRepositoryTest.findAll()).willReturn(Collections.emptyList());

		// When
		List<UserResponse> results = underTest.findAllUser();

		// Then
		assertThat(results).isEmpty();

	}

	/**
	 * Test method for
	 * {@link com.web.springboot.ivento.service.user.UserServiceImpl#findUserResponseById(java.lang.Long)}.
	 */
	@Test
	void testFindUserById() {

		// Given
		Optional<UserEntity> optional = Optional.of(userEntity);
		given(userRepositoryTest.findById(user.getId())).willReturn(optional);

		// When
		UserResponse result = underTest.findUserResponseById(user.getId());

		// Then
		assertNotNull(result);

	}

	@Test
	void testFindUserById_ThrowUserNotFound() {

		// Given
		Long fakeId = 765L;
		String errMessage = "Error message throw by UserNotfoundException";
		given(messageUtilsTest.getMessage(Literals.ERROR_USER_NOT_FOUND)).willReturn(errMessage);

		// When
		// Then
		assertThatThrownBy(() -> underTest.findUserResponseById(fakeId)).isInstanceOf(UserNotFoundException.class)
				.hasMessage(messageUtilsTest.getMessage(Literals.ERROR_USER_NOT_FOUND));

	}

	/**
	 * Test method for
	 * {@link com.web.springboot.ivento.service.user.UserServiceImpl#updateUser(com.web.springboot.ivento.payload.request.UserRequest)}.
	 */
	@Test
	void testUpdateUser() {

		// given
		UserRequest request = new UserRequest();
		request.setId(3L);
		request.setUsername("XEA");
		request.setEmail("xea@test.com");
		request.setBlocked(Boolean.FALSE);
		request.setImage("IMAGE");
		request.setRole(ERole.ROLE_USER);
		request.setLastLoggedIn(LocalDateTime.now());
		request.setLastUpdated(LocalDateTime.now());

		UserEntity expectedResult = new UserEntity();
		expectedResult.setId(request.getId());
		expectedResult.setUsername(request.getUsername());
		expectedResult.setEmail(request.getEmail());
		expectedResult.setPassword("password");
		expectedResult.setBlocked(request.isBlocked());
		expectedResult.setImage(request.getImage());
		expectedResult.setRole(request.getRole());
		expectedResult.setLastLoggedIn(request.getLastLoggedIn());
		expectedResult.setLastUpdated(request.getLastUpdated());

		// when
		given(userRepositoryTest.findById(request.getId())).willReturn(Optional.of(expectedResult));

		// then
		assertTrue(underTest.updateUser(request));

	}

	/**
	 * Test method for
	 * {@link com.web.springboot.ivento.service.user.UserServiceImpl#addNewUser(com.web.springboot.ivento.payload.request.SignupRequest)}.
	 */
	@Test
	void testAddNewUser() {

		// given
		SignupRequest signupRequest = new SignupRequest();
		signupRequest.setUsername("new_user");
		signupRequest.setEmail("new_user@test.com");
		signupRequest.setPassword("password");
		signupRequest.setBlocked(Boolean.FALSE);
		signupRequest.setImage("IMAGE");
		signupRequest.setRole(ERole.ROLE_USER);
		signupRequest.setLastLoggedIn(LocalDateTime.now());
		signupRequest.setLastUpdated(LocalDateTime.now());

		UserEntity expectedResult = new UserEntity();
		expectedResult.setId(null);
		expectedResult.setUsername("new_user");
		expectedResult.setEmail("new_user@test.com");
		expectedResult.setPassword("password");
		expectedResult.setBlocked(Boolean.FALSE);
		expectedResult.setImage("IMAGE");
		expectedResult.setRole(ERole.ROLE_USER);
		expectedResult.setLastLoggedIn(LocalDateTime.now());
		expectedResult.setLastUpdated(LocalDateTime.now());

		// when
		underTest.addNewUser(signupRequest);

		// then
		ArgumentCaptor<UserEntity> userArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);
		verify(userRepositoryTest).save(userArgumentCaptor.capture()); // Capture argument created by mockito after save
																		// value in repository
		UserEntity userSaved = userArgumentCaptor.getValue();

		assertThat(userSaved).isEqualTo(expectedResult); // Compare result between ArgumentCaptor(after call
															// addNewUser()) and expected result.
	}

	@Test
	void testAddNewUser_ThrowUserIdTaken() {

		// given
		SignupRequest signupRequest = new SignupRequest();
		signupRequest.setUsername("new_user");
		signupRequest.setEmail("new_user@test.com");
		signupRequest.setPassword("password");
		signupRequest.setBlocked(Boolean.FALSE);
		signupRequest.setImage("IMAGE");
		signupRequest.setRole(ERole.ROLE_USER);
		signupRequest.setLastLoggedIn(LocalDateTime.now());
		signupRequest.setLastUpdated(LocalDateTime.now());

		lenient().when(userRepositoryTest.existsByUsername(anyString())).thenReturn(Boolean.TRUE);
		lenient().when(userRepositoryTest.existsByEmail(anyString())).thenReturn(Boolean.TRUE);

		// when
		// then
		assertThatThrownBy(() -> underTest.addNewUser(signupRequest)).isInstanceOf(UserException.class)
				.hasMessage(messageUtilsTest.getMessage(Literals.ERROR_USER_ID_TAKEN));

		verify(userRepositoryTest, never()).save(any()); // To ensure the userRepository will never execute save()

	}

	/**
	 * Test method for
	 * {@link com.web.springboot.ivento.service.user.UserServiceImpl#findUserResponseByUsername(java.lang.String)}.
	 */
	@Test
	void testFindUserByUsername() {

		// given
		String searchUsername = "XEA";

		UserResponse expectedResult = new UserResponse();
		expectedResult.setId(3L);
		expectedResult.setUsername(searchUsername);
		expectedResult.setEmail("xea@test.com");
		expectedResult.setBlocked(Boolean.FALSE);
		expectedResult.setImage("IMAGE");
		expectedResult.setRole(ERole.ROLE_USER);
		expectedResult.setLastLoggedIn(LocalDateTime.now());
		expectedResult.setLastUpdated(LocalDateTime.now());

		UserEntity entity = new UserEntity();
		entity.setId(3L);
		entity.setUsername(searchUsername);
		entity.setEmail("xea@test.com");
		entity.setPassword("password");
		entity.setBlocked(Boolean.FALSE);
		entity.setImage("IMAGE");
		entity.setRole(ERole.ROLE_USER);
		entity.setLastLoggedIn(LocalDateTime.now());
		entity.setLastUpdated(LocalDateTime.now());

		given(userRepositoryTest.findByUsername("XEA")).willReturn(Optional.of(entity));

		// when
		UserResponse result = underTest.findUserResponseByUsername(searchUsername);

		// then
		assertEquals(expectedResult, result);

	}

	@Test
	void testFindUserByUsername_ThrowUserNotFound() {

		// Given
		String searchUsername = "XEA";
		String errMessage = "Error message throw by UserNotfoundException";
		given(messageUtilsTest.getMessage(Literals.ERROR_USER_NOT_FOUND)).willReturn(errMessage);

		// When
		// Then
		assertThatThrownBy(() -> underTest.findUserResponseByUsername(searchUsername))
				.isInstanceOf(UserNotFoundException.class)
				.hasMessage(messageUtilsTest.getMessage(Literals.ERROR_USER_NOT_FOUND));

	}

	/**
	 * Test method for
	 * {@link com.web.springboot.ivento.service.user.UserServiceImpl#findUserByEmail(java.lang.String)}.
	 */
	@Test
	void testFindUserByEmail() {

		// given
		String searchUsername = "XEA@test.com";

		UserResponse expectedResult = new UserResponse();
		expectedResult.setId(3L);
		expectedResult.setUsername("XEA");
		expectedResult.setEmail(searchUsername);
		expectedResult.setBlocked(Boolean.FALSE);
		expectedResult.setImage("IMAGE");
		expectedResult.setRole(ERole.ROLE_USER);
		expectedResult.setLastLoggedIn(LocalDateTime.now());
		expectedResult.setLastUpdated(LocalDateTime.now());

		UserEntity entity = new UserEntity();
		entity.setId(3L);
		entity.setUsername("XEA");
		entity.setEmail(searchUsername);
		entity.setPassword("password");
		entity.setBlocked(Boolean.FALSE);
		entity.setImage("IMAGE");
		entity.setRole(ERole.ROLE_USER);
		entity.setLastLoggedIn(LocalDateTime.now());
		entity.setLastUpdated(LocalDateTime.now());

		given(userRepositoryTest.findByEmail("XEA@test.com")).willReturn(Optional.of(entity));

		// when
		UserResponse result = underTest.findUserByEmail(searchUsername);

		// then
		assertEquals(expectedResult, result);

	}

	@Test
	void testFindUserByEmail_ThrowUserNotFound() {

		// Given
		String searchEmail = "XEA@test.com";
		String errMessage = "Error message throw by UserNotfoundException";
		given(messageUtilsTest.getMessage(Literals.ERROR_USER_NOT_FOUND)).willReturn(errMessage);

		// When
		// Then
		assertThatThrownBy(() -> underTest.findUserByEmail(searchEmail)).isInstanceOf(UserNotFoundException.class)
				.hasMessage(messageUtilsTest.getMessage(Literals.ERROR_USER_NOT_FOUND));

	}

	/**
	 * Test method for
	 * {@link com.web.springboot.ivento.service.user.UserServiceImpl#disableAccountById(java.lang.Long)}.
	 */
	@Test
	void testDisableAccountById() {

		// given
		Long searchedId = 3L;

		UserEntity user = new UserEntity();
		user.setId(3L);
		user.setUsername("XEA");
		user.setEmail("XEA@test.com");
		user.setBlocked(Boolean.TRUE);
		user.setImage("IMAGE");
		user.setRole(ERole.ROLE_USER);
		user.setLastLoggedIn(LocalDateTime.now());
		user.setLastUpdated(LocalDateTime.now());

		given(userRepositoryTest.findById(user.getId())).willReturn(Optional.of(user));

		// when
		// then
		assertTrue(underTest.disableAccountById(searchedId));

	}

	@Test
	void testDisableAccountById_ThrowUserNotFound() {

		// given
		Long searchedId = 3L;

		String errMessage = "Error message throw by UserNotfoundException";
		given(messageUtilsTest.getMessage(Literals.ERROR_USER_NOT_FOUND)).willReturn(errMessage);

		// When
		// Then
		assertThatThrownBy(() -> underTest.disableAccountById(searchedId)).isInstanceOf(UserNotFoundException.class)
				.hasMessage(messageUtilsTest.getMessage(Literals.ERROR_USER_NOT_FOUND));

	}

	/**
	 * Test method for
	 * {@link com.web.springboot.ivento.service.user.UserServiceImpl#enableAccountById(java.lang.Long)}.
	 */
	@Test
	void testEnableAccountById() {

		// given
		Long searchedId = 3L;

		UserEntity user = new UserEntity();
		user.setId(3L);
		user.setUsername("XEA");
		user.setEmail("XEA@test.com");
		user.setBlocked(Boolean.FALSE);
		user.setImage("IMAGE");
		user.setRole(ERole.ROLE_USER);
		user.setLastLoggedIn(LocalDateTime.now());
		user.setLastUpdated(LocalDateTime.now());

		given(userRepositoryTest.findById(user.getId())).willReturn(Optional.of(user));

		// when
		// then
		assertTrue(underTest.enableAccountById(searchedId));

	}

	@Test
	void testEnableAccountById_ThrowUserNotFound() {

		// given
		Long searchedId = 3L;

		String errMessage = "Error message throw by UserNotfoundException";
		given(messageUtilsTest.getMessage(Literals.ERROR_USER_NOT_FOUND)).willReturn(errMessage);

		// When
		// Then
		assertThatThrownBy(() -> underTest.enableAccountById(searchedId)).isInstanceOf(UserNotFoundException.class)
				.hasMessage(messageUtilsTest.getMessage(Literals.ERROR_USER_NOT_FOUND));

	}

}
