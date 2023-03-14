package com.web.springboot.ivento.service.exception;

/**
 * @author mokht
 *
 */
public class CustomerOrderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2364690355873085184L;

	public CustomerOrderException(String errorMessage) {
		super(errorMessage);
	}

}
