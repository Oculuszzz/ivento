package com.web.springboot.ivento.payload.response;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author mokht
 *
 *         HTTP request object class which stored information of Authenticate
 *         response.
 *
 * 
 */
public class AuthenticationResponse {

	private String accessToken;

	private String refreshToken;

	/**
	 * HTTP response type - Bearer authentication/token authentication
	 */
	private String tokenType = "Bearer";

	private Long id;

	private String username;

	private String email;

	private Boolean isBlocked;

	private String image;

	private String role;

	private LocalDateTime lastLoggedIn;

	private LocalDateTime lastUpdated;

	/**
	 * @param accessToken
	 * @param refreshToken
	 * @param id
	 * @param username
	 * @param email
	 * @param isBlocked
	 * @param image
	 * @param role
	 * @param lastLoggedIn
	 * @param lastUpdated
	 */
	public AuthenticationResponse(String accessToken, String refreshToken, Long id, String username, String email,
			Boolean isBlocked, String image, String role, LocalDateTime lastLoggedIn, LocalDateTime lastUpdated) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.isBlocked = isBlocked;
		this.image = image;
		this.role = role;
		this.lastLoggedIn = lastLoggedIn;
		this.lastUpdated = lastUpdated;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the tokenType
	 */
	public String getTokenType() {
		return tokenType;
	}

	/**
	 * @param tokenType the tokenType to set
	 */
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
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
	public Boolean getIsBlocked() {
		return isBlocked;
	}

	/**
	 * @param isBlocked the isBlocked to set
	 */
	public void setIsBlocked(Boolean isBlocked) {
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
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
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

	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * @param refreshToken the refreshToken to set
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accessToken, email, id, image, isBlocked, lastLoggedIn, lastUpdated, refreshToken, role,
				tokenType, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthenticationResponse other = (AuthenticationResponse) obj;
		return Objects.equals(accessToken, other.accessToken) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(image, other.image)
				&& Objects.equals(isBlocked, other.isBlocked) && Objects.equals(lastLoggedIn, other.lastLoggedIn)
				&& Objects.equals(lastUpdated, other.lastUpdated) && Objects.equals(refreshToken, other.refreshToken)
				&& Objects.equals(role, other.role) && Objects.equals(tokenType, other.tokenType)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "AuthenticationResponse [accessToken=" + accessToken + ", refreshToken=" + refreshToken + ", tokenType="
				+ tokenType + ", id=" + id + ", username=" + username + ", email=" + email + ", isBlocked=" + isBlocked
				+ ", image=" + image + ", role=" + role + ", lastLoggedIn=" + lastLoggedIn + ", lastUpdated="
				+ lastUpdated + "]";
	}

}
