/**
 * 
 */
package com.web.springboot.ivento.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.web.springboot.ivento.model.ERole;
import com.web.springboot.ivento.model.UserEntity;

/**
 * @author mokht
 *
 *         Unit test using @DataJpaTest that focuses only on JPA components.
 *         Using this annotation will disable full auto-configuration and
 *         instead apply only configuration relevant to JPA tests.
 *
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
class UserRepositoryTest {

//	@SpringBootTest loads full application context, exactly like how you start a Spring container when you run your Spring Boot application.
//	@WebMvcTest loads only the web layer, which includes security, filter, interceptors, etc for handling request/response. Typically you would write tests for methods under @Controller or @RestController.
//	@DataJpaTest loads only configuration for JPA. It uses an embedded in-memory h2 if not specified otherwise.

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryTest.class);

	@Autowired
	private UserRepository userRepository;

	private UserEntity user;

	private static final String FAKE_USERNAME = "LALA";
	private static final String FAKE_EMAIL = "LALA@test.com";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		logger.info("@BeforeEach - executes before each test method in this class");

		user = new UserEntity();
		user.setId(313131L);
		user.setUsername("UnitTestA");
		user.setEmail("UnitTestA@test.com");
		user.setPassword("test");
		user.setBlocked(Boolean.FALSE);
		user.setImage("IMAGE");
		user.setRole(ERole.ROLE_USER);
		user.setLastLoggedIn(LocalDateTime.now());
		user.setLastUpdated(LocalDateTime.now());

		userRepository.save(user);

	}

	@Test
	void test_Exists_Username() {

		assertThat(userRepository.existsByUsername(user.getUsername())).isTrue();

	}

	@Test
	void test_NonExists_Username() {

		assertThat(userRepository.existsByUsername(FAKE_USERNAME)).isFalse();

	}

	@Test
	void test_Exists_Email() {

		assertThat(userRepository.existsByEmail(user.getEmail())).isTrue();

	}

	@Test
	void test_NonExists_Email() {

		assertThat(userRepository.existsByEmail(FAKE_EMAIL)).isFalse();

	}

	@Test
	void test_Exists_findByUsername() {

		assertThat(userRepository.findByUsername(user.getUsername())).isPresent();

	}

	@Test
	void test_NonExists_findByUsername() {

		assertThat(userRepository.findByUsername(FAKE_USERNAME)).isNotPresent();

	}

	@Test
	void test_Exists_findByEmail() {

		assertThat(userRepository.findByEmail(user.getEmail())).isPresent();

	}

	@Test
	void test_NonExists_findByEmail() {

		assertThat(userRepository.findByEmail(FAKE_USERNAME)).isNotPresent();

	}

}
