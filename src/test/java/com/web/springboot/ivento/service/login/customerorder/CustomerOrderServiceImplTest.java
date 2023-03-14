/**
 * 
 */
package com.web.springboot.ivento.service.login.customerorder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.web.springboot.ivento.component.utils.MessageUtils;
import com.web.springboot.ivento.model.CustomerOrderEntity;
import com.web.springboot.ivento.model.EOrderStatus;
import com.web.springboot.ivento.model.OrderedProductEntity;
import com.web.springboot.ivento.payload.request.CustomerOrderRequest;
import com.web.springboot.ivento.payload.request.OrderedProductRequest;
import com.web.springboot.ivento.payload.response.CustomerOrderResponse;
import com.web.springboot.ivento.payload.response.ProductResponse;
import com.web.springboot.ivento.properties.Literals;
import com.web.springboot.ivento.repository.CustomerOrderRepository;
import com.web.springboot.ivento.service.exception.ProductNotFoundException;
import com.web.springboot.ivento.service.product.ProductServiceImpl;

/**
 * @author mokht
 *
 */
@ExtendWith(MockitoExtension.class) // for JUnit 5
class CustomerOrderServiceImplTest {

	private static final Logger logger = LoggerFactory.getLogger(CustomerOrderServiceImplTest.class);

	private CustomerOrderServiceImpl underTest;

	@Mock
	private ProductServiceImpl productServiceTest;

	@Mock
	private CustomerOrderRepository customerOrderRepositoryTest;

	@Mock
	private MessageUtils messageUtilsTest;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		underTest = new CustomerOrderServiceImpl(productServiceTest, customerOrderRepositoryTest, messageUtilsTest);

	}

	/**
	 * Test method for
	 * {@link com.web.springboot.ivento.service.login.customerorder.CustomerOrderServiceImpl#findAllOrder()}.
	 */
	@Test
	void testFindAllOrder() {
		// given
		OrderedProductEntity productEntity = new OrderedProductEntity();
		productEntity.setId(123L);
		productEntity.setProductId(321L);
		productEntity.setProductCode("E1");
		productEntity.setName("E_PRODUCT_1");
		productEntity.setBrand("E_BRAND");
		productEntity.setQuantity(5L);
		productEntity.setPrice(1000L);
		productEntity.setTotalPrice(5000L);

		CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();
		customerOrderEntity.setId(980L);
		customerOrderEntity.setName("CUSTOMER_A");
		customerOrderEntity.setPhoneNumber("0119874564");
		customerOrderEntity.setAddress("BLUE HOUSE, 57000 KUALA LUMPUR");
		customerOrderEntity.setOrderedProducts(new ArrayList<>());
		customerOrderEntity.getOrderedProducts().add(productEntity);
		customerOrderEntity.setStatus(EOrderStatus.NEW_ORDER);
		customerOrderEntity.setTotalPrice(5000L);
		customerOrderEntity.setLastUpdate(LocalDateTime.now());

		List<CustomerOrderEntity> orders = new ArrayList<>();
		orders.add(customerOrderEntity);

		given(customerOrderRepositoryTest.findAll()).willReturn(orders);

		// when
		List<CustomerOrderResponse> results = underTest.findAllOrder();

		// then
		assertThat(results).isNotEmpty();
	}

	/**
	 * Test method for
	 * {@link com.web.springboot.ivento.service.login.customerorder.CustomerOrderServiceImpl#findOrderById(java.lang.Long)}.
	 */
	@Test
	void testFindOrderById() {

		// given
		Long searchedId = 123L;

		OrderedProductEntity productEntity = new OrderedProductEntity();
		productEntity.setId(123L);
		productEntity.setProductId(321L);
		productEntity.setProductCode("E1");
		productEntity.setName("E_PRODUCT_1");
		productEntity.setBrand("E_BRAND");
		productEntity.setQuantity(5L);
		productEntity.setPrice(1000L);
		productEntity.setTotalPrice(5000L);

		CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();
		customerOrderEntity.setId(980L);
		customerOrderEntity.setName("CUSTOMER_A");
		customerOrderEntity.setPhoneNumber("0119874564");
		customerOrderEntity.setAddress("BLUE HOUSE, 57000 KUALA LUMPUR");
		customerOrderEntity.setOrderedProducts(new ArrayList<>());
		customerOrderEntity.getOrderedProducts().add(productEntity);
		customerOrderEntity.setStatus(EOrderStatus.NEW_ORDER);
		customerOrderEntity.setTotalPrice(5000L);
		customerOrderEntity.setLastUpdate(LocalDateTime.now());

		given(customerOrderRepositoryTest.findById(123L)).willReturn(Optional.of(customerOrderEntity));

		// when
		CustomerOrderResponse result = underTest.findOrderById(searchedId);

		// then
		assertThat(result).isNotNull();

	}

	@Test
	void testFindOrderById_ThrowException() {

		// given
		Long searchedId = 876L;

		// when
		// then
		assertThatThrownBy(() -> underTest.findOrderById(searchedId)).isInstanceOf(ProductNotFoundException.class)
				.hasMessage(messageUtilsTest.getMessage(Literals.ERROR_PRODUCT_NOT_FOUND));

	}

	/**
	 * Test method for
	 * {@link com.web.springboot.ivento.service.login.customerorder.CustomerOrderServiceImpl#updateCustomerOrder(com.web.springboot.ivento.payload.request.CustomerOrderRequest)}.
	 */
	@Test
	@Disabled
	void testUpdateCustomerOrder() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.web.springboot.ivento.service.login.customerorder.CustomerOrderServiceImpl#addCustomerOrder(com.web.springboot.ivento.payload.request.CustomerOrderRequest)}.
	 */
	@Test
	void testAddCustomerOrder() {

		// given
		OrderedProductRequest orderedProduct = new OrderedProductRequest();
		orderedProduct.setId(123L);
		orderedProduct.setProductId(321L);
		orderedProduct.setProductCode("E1");
		orderedProduct.setName("E_PRODUCT_1");
		orderedProduct.setBrand("E_BRAND");
		orderedProduct.setQuantity(5L);
		orderedProduct.setPrice(1000L);
		orderedProduct.setTotalPrice(5000L);

		CustomerOrderRequest customerOrder = new CustomerOrderRequest();
		customerOrder.setId(980L);
		customerOrder.setName("CUSTOMER_A");
		customerOrder.setPhoneNumber("0119874564");
		customerOrder.setAddress("BLUE HOUSE, 57000 KUALA LUMPUR");
		customerOrder.setProducts(new ArrayList<>());
		customerOrder.getProducts().add(orderedProduct);
		customerOrder.setStatus(EOrderStatus.NEW_ORDER);
		customerOrder.setTotalPrice(5000L);
		customerOrder.setLastUpdate(LocalDateTime.now());

		ProductResponse existsProduct = new ProductResponse();
		existsProduct.setId(orderedProduct.getProductId());
		existsProduct.setProductCode(orderedProduct.getProductCode());
		existsProduct.setName(orderedProduct.getName());
		existsProduct.setBrand(orderedProduct.getBrand());
		existsProduct.setQuantity(orderedProduct.getQuantity());
		existsProduct.setPrice(orderedProduct.getTotalPrice());

		OrderedProductEntity expectedProduct = new OrderedProductEntity();
		expectedProduct.setId(orderedProduct.getId());
		expectedProduct.setProductId(orderedProduct.getProductId());
		expectedProduct.setProductCode(orderedProduct.getProductCode());
		expectedProduct.setName(orderedProduct.getName());
		expectedProduct.setBrand(orderedProduct.getBrand());
		expectedProduct.setQuantity(orderedProduct.getQuantity());
		expectedProduct.setPrice(orderedProduct.getPrice());
		expectedProduct.setTotalPrice(orderedProduct.getTotalPrice());

		CustomerOrderEntity expectedOrder = new CustomerOrderEntity();
		expectedOrder.setId(customerOrder.getId());
		expectedOrder.setName(customerOrder.getName());
		expectedOrder.setPhoneNumber(customerOrder.getPhoneNumber());
		expectedOrder.setAddress(customerOrder.getAddress());
		expectedOrder.setOrderedProducts(new ArrayList<>());
		expectedOrder.getOrderedProducts().add(expectedProduct);
		expectedOrder.setStatus(customerOrder.getStatus());
		expectedOrder.setTotalPrice(customerOrder.getTotalPrice());
		expectedOrder.setLastUpdate(customerOrder.getLastUpdate());

		given(productServiceTest.findProductById(orderedProduct.getProductId())).willReturn(existsProduct);

		// when
		underTest.addCustomerOrder(customerOrder);

		// then
		ArgumentCaptor<CustomerOrderEntity> orderArgumentCaptor = ArgumentCaptor.forClass(CustomerOrderEntity.class);
		verify(customerOrderRepositoryTest).save(orderArgumentCaptor.capture()); // Capture argument created by mockito
																					// after save
		// value in repository
		CustomerOrderEntity orderSaved = orderArgumentCaptor.getValue();

//		assertThat(orderSaved.getId()).isEqualTo(expectedOrder.getId()); // NOT POSSIBLE TO CHECK BECAUSE ID ARE AUTOMATICALL GENERATED BY DB
		assertThat(orderSaved.getName()).isEqualTo(expectedOrder.getName());
		assertThat(orderSaved.getPhoneNumber()).isEqualTo(expectedOrder.getPhoneNumber());
		assertThat(orderSaved.getAddress()).isEqualTo(expectedOrder.getAddress());
		assertThat(orderSaved.getStatus()).isEqualTo(expectedOrder.getStatus());
		assertThat(orderSaved.getTotalPrice()).isEqualTo(expectedOrder.getTotalPrice());
		assertThat(orderSaved.getLastUpdate()).isEqualTo(expectedOrder.getLastUpdate());

	}

	@Test
	final void testAddCustomerOrder_ThrowException() {

		// given
		OrderedProductRequest orderedProduct = new OrderedProductRequest();
		orderedProduct.setId(123L);
		orderedProduct.setProductId(321L);
		orderedProduct.setProductCode("E1");
		orderedProduct.setName("E_PRODUCT_1");
		orderedProduct.setBrand("E_BRAND");
		orderedProduct.setQuantity(5L);
		orderedProduct.setPrice(1000L);
		orderedProduct.setTotalPrice(5000L);

		CustomerOrderRequest customerOrder = new CustomerOrderRequest();
		customerOrder.setId(980L);
		customerOrder.setName("CUSTOMER_A");
		customerOrder.setPhoneNumber("0119874564");
		customerOrder.setAddress("BLUE HOUSE, 57000 KUALA LUMPUR");
		customerOrder.setProducts(new ArrayList<>());
		customerOrder.getProducts().add(orderedProduct);
		customerOrder.setStatus(EOrderStatus.NEW_ORDER);
		customerOrder.setTotalPrice(5000L);
		customerOrder.setLastUpdate(LocalDateTime.now());

		// when
		// then
		assertThatThrownBy(() -> underTest.addCustomerOrder(customerOrder)).isInstanceOf(ProductNotFoundException.class)
				.hasMessage(messageUtilsTest.getMessage(Literals.ERROR_PRODUCT_NOT_FOUND));

	}

	/**
	 * Test method for
	 * {@link com.web.springboot.ivento.service.login.customerorder.CustomerOrderServiceImpl#searchByCustomerName(java.lang.String)}.
	 */
	@Test
	void testSearchByCustomerName() {

		// give
		String nameCustomerLike = "omer";

		OrderedProductEntity expectedProduct = new OrderedProductEntity();
		expectedProduct.setId(123L);
		expectedProduct.setProductId(321L);
		expectedProduct.setProductCode("E1");
		expectedProduct.setName("E_PRODUCT_1");
		expectedProduct.setBrand("E_BRAND");
		expectedProduct.setQuantity(5L);
		expectedProduct.setPrice(1000L);
		expectedProduct.setTotalPrice(5000L);

		CustomerOrderEntity expectedOrder = new CustomerOrderEntity();
		expectedOrder.setId(980L);
		expectedOrder.setName("CUSTOMER_A");
		expectedOrder.setPhoneNumber("0119874564");
		expectedOrder.setAddress("BLUE HOUSE, 57000 KUALA LUMPUR");
		expectedOrder.setOrderedProducts(new ArrayList<>());
		expectedOrder.getOrderedProducts().add(expectedProduct);
		expectedOrder.setStatus(EOrderStatus.NEW_ORDER);
		expectedOrder.setTotalPrice(5000L);
		expectedOrder.setLastUpdate(LocalDateTime.now());

		List<CustomerOrderEntity> orders = new ArrayList<>();
		orders.add(expectedOrder);

		given(customerOrderRepositoryTest.searchByCustomerName(nameCustomerLike)).willReturn(Optional.of(orders));

		// when
		List<CustomerOrderResponse> results = underTest.searchByCustomerName(nameCustomerLike);

		assertThat(results).isNotEmpty();
		assertThat(results.get(0).getName()).isEqualTo(expectedOrder.getName());

	}

}
