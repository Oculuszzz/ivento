/**
 * 
 */
package com.web.springboot.ivento.payload.request;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

/**
 * @author mokht
 *
 */
public class AuthenticationRequest {

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	/**
	 * 
	 */
	public AuthenticationRequest() {
		super();
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthenticationRequest other = (AuthenticationRequest) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "SigninRequest [username=" + username + ", password=" + password + "]";
	}

}
