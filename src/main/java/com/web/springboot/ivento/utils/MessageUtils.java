/**
 * 
 */
package com.web.springboot.ivento.utils;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

/**
 * @author mokht
 *
 *         A message utility class to get text from message properties file.
 *
 */
@Component
public class MessageUtils {

	private MessageSource messageSource;

	/**
	 * @param messageSource
	 */
	public MessageUtils(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage(String message) {
		try {

			return messageSource.getMessage(message, null, new Locale("en"));

		} catch (NoSuchMessageException ex) {

			return String.format("No message is found with message key: %s", message);

		}

	}

}
