/**
 * 
 */
package com.web.springboot.ivento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.web.springboot.ivento.model.JwtTokenEntity;
import com.web.springboot.ivento.model.UserEntity;

/**
 * @author mokht
 *
 */
public interface JwtTokenRepository extends JpaRepository<JwtTokenEntity, Long> {

	public Optional<JwtTokenEntity> findByRefreshToken(String refreshToken);

	@Modifying
	int deleteByUser(UserEntity user);

}
