/**
 * 
 */
package com.web.springboot.ivento.payload.response;

import java.util.Objects;

/**
 * @author mokht
 *
 */
public class TokenRefreshResponse {

	private String tokenType = "Bearer";
	private String accessToken;
	private String refreshToken;

	/**
	 * @param accessToken
	 * @param refreshToken
	 */
	public TokenRefreshResponse(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
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

	@Override
	public int hashCode() {
		return Objects.hash(accessToken, refreshToken, tokenType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenRefreshResponse other = (TokenRefreshResponse) obj;
		return Objects.equals(accessToken, other.accessToken) && Objects.equals(refreshToken, other.refreshToken)
				&& Objects.equals(tokenType, other.tokenType);
	}

	@Override
	public String toString() {
		return "TokenRefreshResponse [tokenType=" + tokenType + ", accessToken=" + accessToken + ", refreshToken="
				+ refreshToken + "]";
	}

}
