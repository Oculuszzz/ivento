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
public class TokenRefreshRequest {

	@NotBlank
	private String refreshToken;

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	public int hashCode() {
		return Objects.hash(refreshToken);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenRefreshRequest other = (TokenRefreshRequest) obj;
		return Objects.equals(refreshToken, other.refreshToken);
	}

	@Override
	public String toString() {
		return "TokenRefreshRequest [refreshToken=" + refreshToken + "]";
	}

}
