package com.web.springboot.ivento.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.springboot.ivento.model.UserEntity;
import com.web.springboot.ivento.service.user.UserServiceImpl;

/**
 * @author mokht
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserServiceImpl userServiceImpl;

	/**
	 * Improved design by @Autowired instance on constructor (constructor
	 * injection)are clearly identified, reusable InjectMocks and dependencies
	 * visible
	 * 
	 * @param userRepository
	 * @param messageUtils
	 */
	public UserDetailsServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity user = userServiceImpl.findUserEntityByUsername(username);

		return UserDetailsImpl.build(user);

	}

}
