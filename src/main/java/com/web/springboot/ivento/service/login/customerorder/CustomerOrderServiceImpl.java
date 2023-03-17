/**
 * 
 */
package com.web.springboot.ivento.service.login.customerorder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.springboot.ivento.component.utils.MessageUtils;
import com.web.springboot.ivento.model.CustomerOrderEntity;
import com.web.springboot.ivento.model.OrderedProductEntity;
import com.web.springboot.ivento.payload.request.CustomerOrderRequest;
import com.web.springboot.ivento.payload.request.OrderedProductRequest;
import com.web.springboot.ivento.payload.response.CustomerOrderResponse;
import com.web.springboot.ivento.payload.response.OrderedProductResponse;
import com.web.springboot.ivento.payload.response.ProductResponse;
import com.web.springboot.ivento.properties.Literals;
import com.web.springboot.ivento.repository.CustomerOrderRepository;
import com.web.springboot.ivento.service.exception.CustomerOrderException;
import com.web.springboot.ivento.service.exception.ProductNotFoundException;
import com.web.springboot.ivento.service.product.ProductServiceImpl;

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
		super();
		this.productService = productService;
		this.customerOrderRepository = customerOrderRepository;
		this.messageUtils = messageUtils;
	}

	@Override
	public List<CustomerOrderResponse> findAllOrder() {

		List<CustomerOrderEntity> entities = customerOrderRepository.findAll();
		List<CustomerOrderResponse> lCustomerOrderRequests = new ArrayList<>();

		for (CustomerOrderEntity entity : entities) {

			lCustomerOrderRequests.add(composeCustomerOrderResponse(entity));

		}

		return lCustomerOrderRequests;
	}

	@Override
	public CustomerOrderResponse findOrderById(Long id) {

		CustomerOrderEntity entity = customerOrderRepository.findById(id).orElseThrow(
				() -> new ProductNotFoundException(messageUtils.getMessage(Literals.ERROR_PRODUCT_NOT_FOUND)));

		return composeCustomerOrderResponse(entity);

	}

	@Override
	public Boolean updateCustomerOrder(CustomerOrderRequest customerOrderRequest)
			throws CustomerOrderException, ProductNotFoundException {

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

				throw new ProductNotFoundException(messageUtils.getMessage(Literals.ERROR_PRODUCT_NOT_FOUND));

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

		List<CustomerOrderResponse> customerOrderResponses = new ArrayList<>();
		List<CustomerOrderEntity> entities = customerOrderRepository.searchByCustomerName(search)
				.orElse(Collections.emptyList());

		for (CustomerOrderEntity entity : entities) {

			customerOrderResponses.add(composeCustomerOrderResponse(entity));
		}

		return customerOrderResponses;
	}

	private CustomerOrderResponse composeCustomerOrderResponse(CustomerOrderEntity entity) {
		CustomerOrderResponse customerOrderResponse = new CustomerOrderResponse();
		customerOrderResponse.setId(entity.getId());
		customerOrderResponse.setName(entity.getName());
		customerOrderResponse.setPhoneNumber(entity.getPhoneNumber());
		customerOrderResponse.setAddress(entity.getAddress());
		customerOrderResponse.setCompanyName(entity.getCompanyName());
		customerOrderResponse.setCompanyAddress(entity.getCompanyAddress());
		customerOrderResponse.setStatus(entity.getStatus());
		customerOrderResponse.setTotalPrice(entity.getTotalPrice());
		customerOrderResponse.setLastUpdate(entity.getLastUpdate());
		customerOrderResponse.setProducts(composeOrderedProductRequest(entity.getOrderedProducts()));

		return customerOrderResponse;
	}

	private List<OrderedProductResponse> composeOrderedProductRequest(List<OrderedProductEntity> entities) {

		List<OrderedProductResponse> lOrderedProductResponses = new ArrayList<>();

		for (OrderedProductEntity productEntity : entities) {

			OrderedProductResponse orderedProductResponse = new OrderedProductResponse();
			orderedProductResponse.setId(productEntity.getId());
			orderedProductResponse.setProductId(productEntity.getProductId());
			orderedProductResponse.setProductCode(productEntity.getProductCode());
			orderedProductResponse.setName(productEntity.getName());
			orderedProductResponse.setBrand(productEntity.getBrand());
			orderedProductResponse.setQuantity(productEntity.getQuantity());
			orderedProductResponse.setPrice(productEntity.getQuantity());
			orderedProductResponse.setTotalPrice(productEntity.getTotalPrice());

			lOrderedProductResponses.add(orderedProductResponse);

		}

		return lOrderedProductResponses;
	}

	@Override
	public List<CustomerOrderResponse> findByPlaceOrderStartDateTimeAndDateTime(LocalDateTime startDate,
			LocalDateTime endDate) {

		Optional<List<CustomerOrderEntity>> entities = customerOrderRepository
				.findByPlaceOrderStartDateTimeAndDateTime(startDate, endDate);
		List<CustomerOrderResponse> lCustomerOrderResponse = new ArrayList<>();

		if (entities.isPresent()) {

			for (CustomerOrderEntity entity : entities.get()) {

				CustomerOrderResponse customerOrderResponse = new CustomerOrderResponse();
				customerOrderResponse.setId(entity.getId());
				customerOrderResponse.setName(entity.getName());
				customerOrderResponse.setPhoneNumber(entity.getPhoneNumber());
				customerOrderResponse.setAddress(entity.getAddress());
				customerOrderResponse.setCompanyName(entity.getCompanyName());
				customerOrderResponse.setCompanyAddress(entity.getCompanyAddress());
				customerOrderResponse.setStatus(entity.getStatus());
				customerOrderResponse.setTotalPrice(entity.getTotalPrice());
				customerOrderResponse.setLastUpdate(entity.getLastUpdate());
				customerOrderResponse.setProducts(composeOrderedProductRequest(entity.getOrderedProducts()));

				lCustomerOrderResponse.add(customerOrderResponse);

			}

		}

		return lCustomerOrderResponse;
	}

}
