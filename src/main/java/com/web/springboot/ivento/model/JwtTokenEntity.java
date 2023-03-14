/**
 * 
 */
package com.web.springboot.ivento.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 * @author mokht
 *
 */
@Entity(name = "JWT_TOKEN")
public class JwtTokenEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "accessToken", unique = true)
	private String accessToken;

	@Column(name = "refreshToken", nullable = false, unique = true)
	private String refreshToken;

	@Column(name = "lastUpdate", nullable = false)
	private LocalDateTime lastUpdate;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private UserEntity user;

	/**
	 * 
	 */
	public JwtTokenEntity() {
		super();
	}

	/**
	 * @param accessToken
	 * @param refreshToken
	 * @param lastUpdate
	 * @param user
	 */
	public JwtTokenEntity(String accessToken, String refreshToken, LocalDateTime lastUpdate, UserEntity user) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.lastUpdate = lastUpdate;
		this.user = user;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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

	/**
	 * @return the lastUpdate
	 */
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * @return the user
	 */
	public UserEntity getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserEntity user) {
		this.user = user;
	}

}
