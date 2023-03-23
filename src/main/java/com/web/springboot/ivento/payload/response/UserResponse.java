/**
 * 
 */
package com.web.springboot.ivento.payload.response;

import java.time.LocalDateTime;
import java.util.Objects;

import com.web.springboot.ivento.model.ERole;
import com.web.springboot.ivento.model.UserEntity;

/**
 * @author mokht
 *
 */
public class UserResponse {

	private Long id;

	private String username;

	private String email;

	private Boolean isBlocked;

	private String image;

	private ERole role;

	private LocalDateTime lastLoggedIn;

	private LocalDateTime lastUpdated;

	/**
	 * 
	 */
	public UserResponse() {
		super();
	}

	public UserResponse(UserEntity entity) {

		this.setId(entity.getId());
		this.setUsername(entity.getUsername());
		this.setEmail(entity.getEmail());
		this.setRole(entity.getRole());
		this.setBlocked(entity.isBlocked());
		this.setImage(entity.getImage());
		this.setLastLoggedIn(entity.getLastLoggedIn());
		this.setLastUpdated(entity.getLastUpdated());

	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
		return Objects.hash(email, id, image, isBlocked, lastLoggedIn, lastUpdated, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserResponse other = (UserResponse) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(image, other.image)
				&& Objects.equals(isBlocked, other.isBlocked) && Objects.equals(lastLoggedIn, other.lastLoggedIn)
				&& Objects.equals(lastUpdated, other.lastUpdated) && role == other.role
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", username=" + username + ", email=" + email + ", isBlocked=" + isBlocked
				+ ", image=" + image + ", role=" + role + ", lastLoggedIn=" + lastLoggedIn + ", lastUpdated="
				+ lastUpdated + "]";
	}

}
