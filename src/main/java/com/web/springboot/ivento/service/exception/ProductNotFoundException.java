package com.web.springboot.ivento.service.exception;

/**
 * @author mokht
 *
 */
public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1520671320725123929L;

	public ProductNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
