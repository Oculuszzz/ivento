/**
 * 
 */
package com.web.springboot.ivento.service.login.customerorder;

import java.time.LocalDateTime;
import java.util.List;

import com.web.springboot.ivento.payload.request.CustomerOrderRequest;
import com.web.springboot.ivento.payload.response.CustomerOrderResponse;

/**
 * @author mokht
 *
 */
public interface CustomerOrderService {

	public List<CustomerOrderResponse> findAllOrder();

	public CustomerOrderResponse findOrderById(Long id);

	public Boolean updateCustomerOrder(CustomerOrderRequest customerOrderRequest);

	public void addCustomerOrder(CustomerOrderRequest customerOrderRequest);

	public List<CustomerOrderResponse> searchByCustomerName(String search);

	public List<CustomerOrderResponse> findByPlaceOrderStartDateTimeAndDateTime(LocalDateTime startDate,
			LocalDateTime endDate);

}
