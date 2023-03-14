package com.web.springboot.ivento.service.exception;

/**
 * @author mokht
 *
 */
public class UserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1520671320725123929L;

	public UserException(String errorMessage) {
		super(errorMessage);
	}

}
