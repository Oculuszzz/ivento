/**
 * 
 */
package com.web.springboot.ivento.payload.request;

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

}
