/**
 * 
 */
package com.web.springboot.ivento.service.login.customerorder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.web.springboot.ivento.component.utils.MessageUtils;
import com.web.springboot.ivento.model.CustomerOrderEntity;
import com.web.springboot.ivento.model.OrderedProductEntity;
import com.web.springboot.ivento.payload.request.CustomerOrderRequest;
import com.web.springboot.ivento.payload.request.OrderedProductRequest;
import com.web.springboot.ivento.payload.request.ProductRequest;
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
	public List<CustomerOrderRequest> findAllOrder() {

		List<CustomerOrderEntity> entities = customerOrderRepository.findAll();
		List<CustomerOrderRequest> lCustomerOrderRequests = new ArrayList<>();

		for (CustomerOrderEntity entity : entities) {

			lCustomerOrderRequests.add(composeCustomerOrderRequest(entity));

		}

		return lCustomerOrderRequests;
	}

	@Override
	public CustomerOrderRequest findOrderById(Long id) {

		CustomerOrderEntity entity = customerOrderRepository.findById(id).orElseThrow(
				() -> new ProductNotFoundException(messageUtils.getMessage(Literals.ERROR_PRODUCT_NOT_FOUND)));

		return composeCustomerOrderRequest(entity);

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

			ProductRequest productRequest = productService.findProductById(orderedProductRequest.getProductId());

			// Check requested product is exists or not in DB. If not throw exception
			if (productRequest == null) {

				throw new ProductNotFoundException(messageUtils.getMessage(Literals.ERROR_PRODUCT_NOT_FOUND));

			}

			OrderedProductEntity entity = new OrderedProductEntity(productRequest.getId(),
					productRequest.getProductCode(), productRequest.getName(), productRequest.getBrand(),
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
	public List<CustomerOrderRequest> searchByCustomerName(String search) {

		List<CustomerOrderRequest> customerOrderRequests = new ArrayList<>();
		List<CustomerOrderEntity> entities = customerOrderRepository.searchByCustomerName(search)
				.orElse(Collections.emptyList());

		for (CustomerOrderEntity entity : entities) {

			customerOrderRequests.add(composeCustomerOrderRequest(entity));
		}

		return customerOrderRequests;
	}

	private CustomerOrderRequest composeCustomerOrderRequest(CustomerOrderEntity entity) {
		CustomerOrderRequest orderRequest = new CustomerOrderRequest();
		orderRequest.setId(entity.getId());
		orderRequest.setName(entity.getName());
		orderRequest.setPhoneNumber(entity.getPhoneNumber());
		orderRequest.setAddress(entity.getAddress());
		orderRequest.setCompanyName(entity.getCompanyName());
		orderRequest.setCompanyAddress(entity.getCompanyAddress());
		orderRequest.setStatus(entity.getStatus());
		orderRequest.setTotalPrice(entity.getTotalPrice());
		orderRequest.setLastUpdate(entity.getLastUpdate());
		orderRequest.setProducts(composeOrderedProductRequest(entity.getOrderedProducts()));

		return orderRequest;
	}

	private List<OrderedProductRequest> composeOrderedProductRequest(List<OrderedProductEntity> entities) {

		List<OrderedProductRequest> lOrderedProductRequests = new ArrayList<>();

		for (OrderedProductEntity productEntity : entities) {

			OrderedProductRequest orderedProductRequest = new OrderedProductRequest();
			orderedProductRequest.setId(productEntity.getId());
			orderedProductRequest.setProductId(productEntity.getProductId());
			orderedProductRequest.setProductCode(productEntity.getProductCode());
			orderedProductRequest.setName(productEntity.getName());
			orderedProductRequest.setBrand(productEntity.getBrand());
			orderedProductRequest.setQuantity(productEntity.getQuantity());
			orderedProductRequest.setPrice(productEntity.getQuantity());
			orderedProductRequest.setTotalPrice(productEntity.getTotalPrice());

			lOrderedProductRequests.add(orderedProductRequest);

		}

		return lOrderedProductRequests;
	}

}
