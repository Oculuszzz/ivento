/**
 * 
 */
package com.web.springboot.ivento.service.login.customerorder;

import java.util.List;

import com.web.springboot.ivento.payload.request.CustomerOrderRequest;
import com.web.springboot.ivento.service.exception.CustomerOrderException;
import com.web.springboot.ivento.service.exception.ProductNotFoundException;

/**
 * @author mokht
 *
 */
public interface CustomerOrderService {

	public List<CustomerOrderRequest> findAllOrder();

	public CustomerOrderRequest findOrderById(Long id);

	public Boolean updateCustomerOrder(CustomerOrderRequest customerOrderRequest)
			throws CustomerOrderException, ProductNotFoundException;

	public void addCustomerOrder(CustomerOrderRequest customerOrderRequest);

	public List<CustomerOrderRequest> searchByCustomerName(String search);

}
