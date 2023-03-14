/**
 * 
 */
package com.web.springboot.ivento.service.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.web.springboot.ivento.service.exception.TokenRefreshException;
import com.web.springboot.ivento.service.exception.UserNotFoundException;

/**
 * @author mokht
 *
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = TokenRefreshException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorMessage handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
		return new ErrorMessage(HttpStatus.FORBIDDEN.value(), ex.getMessage(), request.getDescription(false),
				LocalDateTime.now());
	}

	@ExceptionHandler(value = UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false),
				LocalDateTime.now());
	}

}
