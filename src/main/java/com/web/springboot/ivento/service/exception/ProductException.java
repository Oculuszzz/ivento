package com.web.springboot.ivento.service.exception;

/**
 * @author mokht
 *
 */
public class ProductException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2364690355873085184L;

	public ProductException(String errorMessage) {
		super(errorMessage);
	}

}
