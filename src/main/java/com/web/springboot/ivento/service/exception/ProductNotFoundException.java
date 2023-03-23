package com.web.springboot.ivento.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author mokht
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1520671320725123929L;

	public ProductNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
