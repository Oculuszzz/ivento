package com.web.springboot.ivento.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author mokht
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1520671320725123929L;

	public UserException(String errorMessage) {
		super(errorMessage);
	}

}
