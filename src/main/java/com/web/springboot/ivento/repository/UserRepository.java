package com.web.springboot.ivento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.springboot.ivento.model.UserEntity;

/**
 * @author mokht
 *
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public Optional<UserEntity> findByUsername(String username);

	public Optional<UserEntity> findByEmail(String email);

	public Boolean existsByUsername(String username);

	public Boolean existsByEmail(String email);

}
