/**
 * 
 */
package com.web.springboot.ivento.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author mokht
 *
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class TokenRefreshException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 506299764622538158L;

	public TokenRefreshException(String token, String message) {
		super(String.format("Failed for [%s]: %s", token, message));
	}

}
