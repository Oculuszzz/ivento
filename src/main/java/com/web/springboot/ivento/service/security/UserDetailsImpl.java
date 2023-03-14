package com.web.springboot.ivento.service.security;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.springboot.ivento.model.UserEntity;

/**
 * @author mokht
 *
 *         Implementation of UserDetails interface class which return User's
 *         information from Authentication object return after authentication
 *         process is successful.
 *
 */
public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3875864584658864111L;

	/**
	 * User Id
	 */
	private Long id;

	/**
	 * User username
	 */
	private String username;

	/**
	 * User email
	 */
	private String email;

	/**
	 * User password
	 */
	@JsonIgnore
	private String password;

	/**
	 * is user blocked
	 */
	private Boolean isBlocked;

	/**
	 * User password
	 */
	private String image;

	/**
	 * Last logged in
	 */
	private LocalDateTime lastLoggedIn;

	/**
	 * Last user update
	 */
	private LocalDateTime lastUpdate;

	/**
	 * USer roles
	 */
	private Collection<? extends GrantedAuthority> authorities;

	/**
	 * @param id
	 * @param username
	 * @param email
	 * @param password
	 * @param isBlocked
	 * @param image
	 * @param lastLoggedIn
	 * @param lastUpdate
	 * @param authorities
	 */
	public UserDetailsImpl(Long id, String username, String email, String password, Boolean isBlocked, String image,
			LocalDateTime lastLoggedIn, LocalDateTime lastUpdate, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.isBlocked = isBlocked;
		this.image = image;
		this.lastLoggedIn = lastLoggedIn;
		this.lastUpdate = lastUpdate;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		return !this.isBlocked;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @return the lastLoggedIn
	 */
	public LocalDateTime getLastLoggedIn() {
		return lastLoggedIn;
	}

	/**
	 * @return the lastUpdate
	 */
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public static UserDetailsImpl build(UserEntity user) {

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
				user.isBlocked(), user.getImage(), user.getLastLoggedIn(), user.getLastUpdated(), authorities);
	}

}
