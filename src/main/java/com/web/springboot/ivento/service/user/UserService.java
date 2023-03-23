/**
 * 
 */
package com.web.springboot.ivento.service.user;

import java.util.List;

import com.web.springboot.ivento.model.UserEntity;
import com.web.springboot.ivento.payload.request.SignupRequest;
import com.web.springboot.ivento.payload.request.UserRequest;
import com.web.springboot.ivento.payload.response.UserResponse;

/**
 * @author mokht
 *
 */
public interface UserService {

	public List<UserResponse> findAllUser();

	public UserResponse findUserResponseById(Long id);

	public UserEntity findUserEntityById(Long id);

	public Boolean updateUser(UserRequest user);

	public void addNewUser(SignupRequest user);

	public UserResponse findUserResponseByUsername(String username);

	public UserEntity findUserEntityByUsername(String username);

	public UserResponse findUserByEmail(String email);

	public Boolean existsByUsername(String username);

	public Boolean existsByEmail(String email);

	public Boolean disableAccountById(Long id);

	public Boolean enableAccountById(Long id);

}
