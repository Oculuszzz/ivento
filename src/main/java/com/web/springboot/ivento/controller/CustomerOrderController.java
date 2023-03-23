/**
 * 
 */
package com.web.springboot.ivento.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.springboot.ivento.payload.request.CustomerOrderRequest;
import com.web.springboot.ivento.payload.response.CustomerOrderResponse;
import com.web.springboot.ivento.payload.response.MessageResponse;
import com.web.springboot.ivento.properties.Literals;
import com.web.springboot.ivento.service.login.customerorder.CustomerOrderServiceImpl;
import com.web.springboot.ivento.utils.DateUtils;
import com.web.springboot.ivento.utils.MessageUtils;

import jakarta.validation.Valid;

/**
 * @author mokht
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api/auth/customer-orders")
public class CustomerOrderController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerOrderController.class);

	private final CustomerOrderServiceImpl customerOrderService;

	private final DateUtils dateUtils;

	private final MessageUtils messageUtils;

	/**
	 * @param customerOrderService
	 * @param dateUtils
	 * @param messageUtils
	 */
	public CustomerOrderController(CustomerOrderServiceImpl customerOrderService, DateUtils dateUtils,
			MessageUtils messageUtils) {
		this.customerOrderService = customerOrderService;
		this.dateUtils = dateUtils;
		this.messageUtils = messageUtils;
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<CustomerOrderResponse>> findAll() {

		return new ResponseEntity<>(customerOrderService.findAllOrder(), HttpStatus.OK);

	}

	@GetMapping(value = "/find-customer-order")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<CustomerOrderResponse> findById(@RequestParam Long id) {

		return new ResponseEntity<>(customerOrderService.findOrderById(id), HttpStatus.OK);

	}

	@GetMapping(value = "/find-customer-order-by-date")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<CustomerOrderResponse>> findByStartDateTimeAndEndDateTime(
			@RequestParam String startDateTime, @RequestParam String endDateTime) {

		if (logger.isDebugEnabled()) {
			logger.debug(
					String.format("findByStartDateTimeAndEndDateTime(): Received startDateTime - %s, endDateTime - %s ",
							startDateTime, endDateTime));
		}

		return new ResponseEntity<>(customerOrderService.findByPlaceOrderStartDateTimeAndDateTime(
				dateUtils.convertIsoDateTimeStringToLocalDateTime(startDateTime),
				dateUtils.convertIsoDateTimeStringToLocalDateTime(endDateTime)), HttpStatus.OK);

	}

	@GetMapping(value = "search/")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<CustomerOrderResponse>> search(@RequestParam String search) {

		return new ResponseEntity<>(customerOrderService.searchByCustomerName(search), HttpStatus.OK);

	}

	@PutMapping(value = "/update-customer-order")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<MessageResponse> update(@Valid @RequestBody CustomerOrderRequest customerOrderRequest) {

		customerOrderService.updateCustomerOrder(customerOrderRequest);

		return ResponseEntity.ok(new MessageResponse(messageUtils.getMessage(Literals.UPDATE_CUSTOMER_ORDER_OK)));

	}

	@PostMapping(value = "/add-new-customer-order")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<MessageResponse> addNew(@Valid @RequestBody CustomerOrderRequest customerOrderRequest) {

		customerOrderService.addCustomerOrder(customerOrderRequest);

		return ResponseEntity.ok(new MessageResponse(messageUtils.getMessage(Literals.ADD_CUSTOMER_ORDER_OK)));

	}

	@DeleteMapping(value = "/customer-order")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<MessageResponse> delete(@RequestParam Long id) {

		// TODO : Implement delete order

		return ResponseEntity.ok(new MessageResponse(messageUtils.getMessage(Literals.DELETE_CUSTOMER_ORDER_OK)));

	}

}
