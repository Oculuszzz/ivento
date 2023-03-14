package com.web.springboot.ivento.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 * @author mokht
 *
 */
@Entity
@Table(name = "USERS")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@NotBlank
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@NotBlank
	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "isBlocked", nullable = false)
	private Boolean isBlocked;

	@Column(name = "image", nullable = false)
	private String image;

	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private ERole role;

	@Column(name = "lastLoggedIn")
	private LocalDateTime lastLoggedIn;

	@Column(name = "lastUpdated", nullable = false)
	private LocalDateTime lastUpdated;

	/**
	 * 
	 */
	public UserEntity() {
		super();
	}

	/**
	 * @param username
	 * @param email
	 * @param password
	 * @param isBlocked
	 * @param image
	 * @param role
	 * @param lastLoggedIn
	 * @param lastUpdated
	 */
	public UserEntity(@NotBlank String username, @NotBlank String email, @NotBlank String password,
			@NotBlank Boolean isBlocked, @NotBlank String image, @NotBlank ERole role,
			@NotBlank LocalDateTime lastLoggedIn, @NotBlank LocalDateTime lastUpdated) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.isBlocked = isBlocked;
		this.image = image;
		this.role = role;
		this.lastLoggedIn = lastLoggedIn;
		this.lastUpdated = lastUpdated;
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
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, image, isBlocked, lastLoggedIn, lastUpdated, password, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(image, other.image)
				&& Objects.equals(isBlocked, other.isBlocked) && Objects.equals(lastLoggedIn, other.lastLoggedIn)
				&& Objects.equals(lastUpdated, other.lastUpdated) && Objects.equals(password, other.password)
				&& role == other.role && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", isBlocked=" + isBlocked + ", image=" + image + ", role=" + role + ", lastLoggedIn=" + lastLoggedIn
				+ ", lastUpdated=" + lastUpdated + "]";
	}

}
