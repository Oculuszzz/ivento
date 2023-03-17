package com.web.springboot.ivento.service.security;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.web.springboot.ivento.component.utils.MessageUtils;
import com.web.springboot.ivento.model.ERole;
import com.web.springboot.ivento.model.UserEntity;
import com.web.springboot.ivento.properties.Literals;
import com.web.springboot.ivento.service.exception.UserNotFoundException;
import com.web.springboot.ivento.service.user.UserServiceImpl;

@ExtendWith(MockitoExtension.class) // for JUnit 5
class UserDetailsServiceImplTest {

	private UserDetailsServiceImpl underTest;

	@Mock
	private UserServiceImpl userServiceImplTest;

	@Mock
	private MessageUtils messageUtilsTest;

	@BeforeEach
	void setUp() throws Exception {

		underTest = new UserDetailsServiceImpl(userServiceImplTest);

	}

	@Test
	final void testLoadUserByUsername() {

		// given
		UserEntity userEntity = new UserEntity();
		userEntity.setId(123L);
		userEntity.setUsername("Xea");
		userEntity.setEmail("Xea@test.com");
		userEntity.setPassword("password");
		userEntity.setBlocked(Boolean.FALSE);
		userEntity.setImage("IMAGE");
		userEntity.setRole(ERole.ROLE_USER);
		userEntity.setLastLoggedIn(LocalDateTime.now());
		userEntity.setLastUpdated(LocalDateTime.now());

		given(userServiceImplTest.findUserEntityByUsername(userEntity.getUsername())).willReturn(userEntity);

		// when
		UserDetails result = underTest.loadUserByUsername(userEntity.getUsername());

		// then
		assertEquals(userEntity.getUsername(), result.getUsername());

	}

	@Test
	@Disabled // TODO
	final void testLoadUserByUsername_ThrowException() {

		// given
		String searchedUser = "Xea";
		given(userServiceImplTest.findUserEntityByUsername(searchedUser))
				.willThrow(new UserNotFoundException(messageUtilsTest.getMessage(Literals.ERROR_USER_NOT_FOUND)));

		// when
		// then
		assertThatThrownBy(() -> underTest.loadUserByUsername(searchedUser)).isInstanceOf(UserNotFoundException.class)
				.hasMessage(messageUtilsTest.getMessage(Literals.ERROR_USER_NOT_FOUND));
	}

}
