package com.web.springboot.ivento.properties;

/**
 * @author mokht
 *
 */
public class Literals {

	private Literals() {

		throw new IllegalStateException("Utility class");

	}

	public static final String OK_AUTH = "User registered successfully!";

	public static final String ERROR_AUTH_1 = "Error: Username is already taken!";

	public static final String ERROR_AUTH_2 = "Error: Email is already in use!";

	public static final String ERROR_AUTH_3 = "Error: Unrecognized role.";

	public static final String ADD_NEW_USER_OK = "Add new user OK!";

	public static final String UPDATE_USER_OK = "Update user OK!";

	public static final String ADD_NEW_PRODUCT_OK = "Add new product OK!";

	public static final String UPDATE_PRODUCT_OK = "Update product OK!";

	public static final String DELETE_PRODUCT_OK = "Delete product OK!";

	////////////////////////////////////////

	public static final String ADD_CUSTOMER_ORDER_OK = "ADD_CUSTOMER_ORDER_OK";

	public static final String UPDATE_CUSTOMER_ORDER_OK = "UPDATE_CUSTOMER_ORDER_OK";

	public static final String DELETE_CUSTOMER_ORDER_OK = "DELETE_CUSTOMER_ORDER_OK";

	public static final String ERROR_USER_NOT_FOUND = "USER_NOT_FOUND_EXCEPTION";

	public static final String ERROR_USER_ID_TAKEN = "USER_ID_TAKE_EXCEPTION";

	public static final String ERROR_PRODUCT_NOT_FOUND = "PRODUCT_NOT_FOUND_EXCEPTION";

	public static final String ERROR_INVALID_REFRESH_TOKEN = "INVALID_REFRESH_TOKEN_EXCEPTION";

	public static final String ERROR_CUSTOMER_ORDERED_NOT_FOUND = "CUSTOMER_ORDERED_NOT_FOUND_EXCEPTION";

}
