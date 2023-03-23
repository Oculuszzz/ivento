/**
 * 
 */
package com.web.springboot.ivento.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.web.springboot.ivento.controller.CustomerOrderController;

/**
 * @author mokht
 *
 */
@Component
public class DateUtils {

	private static final Logger logger = LoggerFactory.getLogger(CustomerOrderController.class);

	public LocalDateTime convertIsoDateTimeStringToLocalDateTime(String isoDateTime) {

		LocalDateTime localDateTime = null;

		try {

			localDateTime = LocalDateTime.parse(isoDateTime, DateTimeFormatter.ISO_DATE_TIME);

		} catch (Exception e) {

			logger.error(String.format(
					"convertIsoDateTimeStringToLocalDateTime(): Failed parse isoDateTime string (%s) to LocalDateTime - Error - %s",
					isoDateTime, e.getMessage()));

		}

		return localDateTime;

	}

}
