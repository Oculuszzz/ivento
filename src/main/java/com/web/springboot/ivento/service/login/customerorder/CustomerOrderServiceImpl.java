/**
 * 
 */
package com.web.springboot.ivento.service.login.customerorder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.web.springboot.ivento.model.CustomerOrderEntity;
import com.web.springboot.ivento.model.OrderedProductEntity;
import com.web.springboot.ivento.payload.request.CustomerOrderRequest;
import com.web.springboot.ivento.payload.request.OrderedProductRequest;
import com.web.springboot.ivento.payload.response.CustomerOrderResponse;
import com.web.springboot.ivento.payload.response.ProductResponse;
import com.web.springboot.ivento.properties.Literals;
import com.web.springboot.ivento.repository.CustomerOrderRepository;
import com.web.springboot.ivento.service.exception.CustomerOrderNotFound;
import com.web.springboot.ivento.service.exception.ProductNotFoundException;
import com.web.springboot.ivento.service.product.ProductServiceImpl;
import com.web.springboot.ivento.utils.MessageUtils;

/**
 * @author mokht
 *
 */
@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

	private final ProductServiceImpl productService;

	private final CustomerOrderRepository customerOrderRepository;

	private final MessageUtils messageUtils;

	/**
	 * Improved design by @Autowired instance on constructor (constructor
	 * injection)are clearly identified, reusable InjectMocks and dependencies
	 * visible
	 * 
	 * @param productService
	 * @param customerOrderRepository
	 * @param messageUtils
	 */
	public CustomerOrderServiceImpl(ProductServiceImpl productService, CustomerOrderRepository customerOrderRepository,
			MessageUtils messageUtils) {
		this.productService = productService;
		this.customerOrderRepository = customerOrderRepository;
		this.messageUtils = messageUtils;
	}

	@Override
	public List<CustomerOrderResponse> findAllOrder() {

		return customerOrderRepository.findAll().stream().map(CustomerOrderResponse::new).toList();
	}

	@Override
	public CustomerOrderResponse findOrderById(Long id) {

		return customerOrderRepository.findById(id).map(CustomerOrderResponse::new)
				.orElseThrow(() -> new CustomerOrderNotFound(String.format("Id - %d, %s", id,
						messageUtils.getMessage(Literals.ERROR_CUSTOMER_ORDERED_NOT_FOUND))));

	}

	@Override
	public Boolean updateCustomerOrder(CustomerOrderRequest customerOrderRequest) {

		// TODO : Implement update customer order

		return Boolean.FALSE;

	}

	@Override
	public void addCustomerOrder(CustomerOrderRequest customerOrderRequest) {

		CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();
		List<OrderedProductEntity> orderedProductEntities = new ArrayList<>();

		for (OrderedProductRequest orderedProductRequest : customerOrderRequest.getProducts()) {

			ProductResponse productResponse = productService.findProductById(orderedProductRequest.getProductId());

			// Check requested product is exists or not in DB. If not throw exception
			if (productResponse == null) {

				throw new ProductNotFoundException(
						String.format("Product id - %d, %s", orderedProductRequest.getProductId(),
								messageUtils.getMessage(Literals.ERROR_PRODUCT_NOT_FOUND)));

			}

			OrderedProductEntity entity = new OrderedProductEntity(productResponse.getId(),
					productResponse.getProductCode(), productResponse.getName(), productResponse.getBrand(),
					orderedProductRequest.getQuantity(), orderedProductRequest.getPrice(),
					orderedProductRequest.getTotalPrice());

			orderedProductEntities.add(entity);

		}

		customerOrderEntity.setName(customerOrderRequest.getName());
		customerOrderEntity.setPhoneNumber(customerOrderRequest.getPhoneNumber());
		customerOrderEntity.setCompanyName(customerOrderRequest.getCompanyName());
		customerOrderEntity.setCompanyAddress(customerOrderRequest.getCompanyAddress());
		customerOrderEntity.setAddress(customerOrderRequest.getAddress());
		customerOrderEntity.setOrderedProducts(orderedProductEntities);
		customerOrderEntity.setStatus(customerOrderRequest.getStatus());
		customerOrderEntity.setTotalPrice(customerOrderRequest.getTotalPrice());
		customerOrderEntity.setLastUpdate(customerOrderRequest.getLastUpdate());

		customerOrderRepository.save(customerOrderEntity);

	}

	@Override
	public List<CustomerOrderResponse> searchByCustomerName(String search) {

		List<CustomerOrderEntity> entities = customerOrderRepository.searchByCustomerName(search)
				.orElse(Collections.emptyList());

		return entities.stream().map(CustomerOrderResponse::new).toList();

	}

	@Override
	public List<CustomerOrderResponse> findByPlaceOrderStartDateTimeAndDateTime(LocalDateTime startDate,
			LocalDateTime endDate) {

		List<CustomerOrderEntity> entities = customerOrderRepository
				.findByPlaceOrderStartDateTimeAndDateTime(startDate, endDate).orElse(Collections.emptyList());

		return entities.stream().map(CustomerOrderResponse::new).toList();

	}

}
