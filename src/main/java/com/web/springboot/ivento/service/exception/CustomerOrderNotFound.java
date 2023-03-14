package com.web.springboot.ivento.service.exception;

/**
 * @author mokht
 *
 */
public class CustomerOrderNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2364690355873085184L;

	public CustomerOrderNotFound(String errorMessage) {
		super(errorMessage);
	}

}
