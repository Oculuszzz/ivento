/**
 * 
 */
package com.web.springboot.ivento.payload.request;

import java.time.LocalDateTime;
import java.util.Objects;

import com.web.springboot.ivento.model.ERole;

/**
 * @author mokht
 *
 */
public class SignupRequest {

	private String username;

	private String email;

	private String password;

	private Boolean isBlocked = Boolean.FALSE; // Default false

	private String image;

	private ERole role;

	private LocalDateTime lastLoggedIn;

	private LocalDateTime lastUpdated;

	/**
	 * 
	 */
	public SignupRequest() {
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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

	/**
	 * @return the isBlocked
	 */
	public Boolean isBlocked() {
		return isBlocked;
	}

	/**
	 * @param isBlocked the isBlocked to set
	 */
	public void setBlocked(Boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the role
	 */
	public ERole getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(ERole role) {
		this.role = role;
	}

	/**
	 * @return the lastLoggedIn
	 */
	public LocalDateTime getLastLoggedIn() {
		return lastLoggedIn;
	}

	/**
	 * @param lastLoggedIn the lastLoggedIn to set
	 */
	public void setLastLoggedIn(LocalDateTime lastLoggedIn) {
		this.lastLoggedIn = lastLoggedIn;
	}

	/**
	 * @return the lastUpdated
	 */
	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, image, isBlocked, lastLoggedIn, lastUpdated, password, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SignupRequest other = (SignupRequest) obj;
		return Objects.equals(email, other.email) && Objects.equals(image, other.image)
				&& Objects.equals(isBlocked, other.isBlocked) && Objects.equals(lastLoggedIn, other.lastLoggedIn)
				&& Objects.equals(lastUpdated, other.lastUpdated) && Objects.equals(password, other.password)
				&& role == other.role && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "SignupRequest [username=" + username + ", email=" + email + ", password=" + password + ", isBlocked="
				+ isBlocked + ", image=" + image + ", role=" + role + ", lastLoggedIn=" + lastLoggedIn
				+ ", lastUpdated=" + lastUpdated + "]";
	}

}
