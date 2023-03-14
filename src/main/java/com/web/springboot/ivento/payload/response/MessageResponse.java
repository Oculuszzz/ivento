/**
 * 
 */
package com.web.springboot.ivento.payload.response;

/**
 * @author mokht
 *
 *         HTTP request object contains message response.
 *
 */
public class MessageResponse {

	private String message;

	/**
	 * @param message
	 */
	public MessageResponse(String message) {
		super();
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
