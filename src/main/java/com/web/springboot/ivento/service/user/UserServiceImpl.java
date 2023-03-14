package com.web.springboot.ivento.service.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.web.springboot.ivento.component.utils.MessageUtils;
import com.web.springboot.ivento.model.UserEntity;
import com.web.springboot.ivento.payload.request.SignupRequest;
import com.web.springboot.ivento.payload.request.UserRequest;
import com.web.springboot.ivento.payload.response.UserResponse;
import com.web.springboot.ivento.properties.Literals;
import com.web.springboot.ivento.repository.UserRepository;
import com.web.springboot.ivento.service.exception.UserException;
import com.web.springboot.ivento.service.exception.UserNotFoundException;

import jakarta.validation.Valid;

/**
 * @author mokht
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;

	private final MessageUtils messageUtils;

	/**
	 * Improved design by @Autowired instance on constructor (constructor
	 * injection)are clearly identified, reusable InjectMocks and dependencies
	 * visible
	 * 
	 * @param userRepository
	 * @param messageUtils
	 * 
	 */
	public UserServiceImpl(UserRepository userRepository, MessageUtils messageUtils) {
		this.userRepository = userRepository;
		this.messageUtils = messageUtils;
	}

	@Override
	public List<UserRequest> findAllUser() {

		List<UserEntity> lUserEntity = userRepository.findAll();
		List<UserRequest> lUser = new ArrayList<>();

		for (UserEntity entity : lUserEntity) {

			UserRequest u = new UserRequest();
			u.setId(entity.getId());
			u.setUsername(entity.getUsername());
			u.setEmail(entity.getEmail());
			u.setRole(entity.getRole());
			u.setBlocked(entity.isBlocked());
			u.setImage(entity.getImage());
			u.setLastLoggedIn(entity.getLastLoggedIn());
			u.setLastUpdated(entity.getLastUpdated());

			lUser.add(u);

		}

		return lUser;

	}

	@Override
	public UserResponse findUserResponseById(Long id) {

		UserEntity entity = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(messageUtils.getMessage(Literals.ERROR_USER_NOT_FOUND)));

		return composeUserPOJO(entity);

	}

	@Override
	public UserEntity findUserEntityById(Long id) {

		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(messageUtils.getMessage(Literals.ERROR_USER_NOT_FOUND)));

	}

	@Override
	public Boolean updateUser(UserRequest user) throws UserNotFoundException {

		UserEntity entity = userRepository.findById(user.getId())
				.orElseThrow(() -> new UserNotFoundException(messageUtils.getMessage(Literals.ERROR_USER_NOT_FOUND)));

		entity.setUsername(user.getUsername());
		entity.setEmail(user.getEmail());
		entity.setRole(user.getRole());
		entity.setBlocked(user.isBlocked());
		entity.setImage(user.getImage());
		entity.setLastLoggedIn(user.getLastLoggedIn());
		entity.setLastUpdated(user.getLastUpdated());

		userRepository.save(entity);

		return Boolean.TRUE;

	}

	@Override
	public void addNewUser(@Valid SignupRequest signUpRequest) {

		Boolean isUserIdTaken = existsByEmail(signUpRequest.getEmail())
				|| existsByUsername(signUpRequest.getUsername());

		if (Boolean.TRUE.equals(isUserIdTaken)) {

			throw new UserException(messageUtils.getMessage(Literals.ERROR_USER_ID_TAKEN));

		}

		UserEntity entity = new UserEntity();

		entity.setUsername(signUpRequest.getUsername());
		entity.setEmail(signUpRequest.getEmail());
		entity.setPassword(signUpRequest.getPassword());
		entity.setRole(signUpRequest.getRole());
		entity.setBlocked(signUpRequest.isBlocked());
		entity.setImage(signUpRequest.getImage());
		entity.setLastLoggedIn(signUpRequest.getLastLoggedIn());
		entity.setLastUpdated(signUpRequest.getLastUpdated());

		userRepository.save(entity);

	}

	@Override
	public UserResponse findUserResponseByUsername(String username) {

		UserEntity entity = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException(messageUtils.getMessage(Literals.ERROR_USER_NOT_FOUND)));

		return composeUserPOJO(entity);

	}

	@Override
	public UserEntity findUserEntityByUsername(String username) {

		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException(messageUtils.getMessage(Literals.ERROR_USER_NOT_FOUND)));

	}

	@Override
	public UserResponse findUserByEmail(String email) {

		UserEntity entity = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException(messageUtils.getMessage(Literals.ERROR_USER_NOT_FOUND)));

		return composeUserPOJO(entity);

	}

	@Override
	public Boolean existsByUsername(String username) {

		return Boolean.TRUE.equals(userRepository.existsByUsername(username));

	}

	@Override
	public Boolean existsByEmail(String email) {

		return Boolean.TRUE.equals(userRepository.findByEmail(email).isPresent());

	}

	@Override
	public Boolean disableAccountById(Long id) {

		UserEntity entity = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(messageUtils.getMessage(Literals.ERROR_USER_NOT_FOUND)));

		entity.setBlocked(Boolean.TRUE);
		entity.setLastUpdated(LocalDateTime.now());
		userRepository.save(entity);

		return Boolean.TRUE;

	}

	@Override
	public Boolean enableAccountById(Long id) {

		UserEntity entity = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(messageUtils.getMessage(Literals.ERROR_USER_NOT_FOUND)));

		entity.setBlocked(Boolean.FALSE);
		entity.setLastUpdated(LocalDateTime.now());
		userRepository.save(entity);

		return Boolean.TRUE;

	}

	private UserResponse composeUserPOJO(UserEntity entity) {

		UserResponse u = new UserResponse();
		u.setId(entity.getId());
		u.setUsername(entity.getUsername());
		u.setEmail(entity.getEmail());
		u.setRole(entity.getRole());
		u.setBlocked(entity.isBlocked());
		u.setImage(entity.getImage());
		u.setLastLoggedIn(entity.getLastLoggedIn());
		u.setLastUpdated(entity.getLastUpdated());

		return u;
	}

}
