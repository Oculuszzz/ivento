/**
 * 
 */
package com.web.springboot.ivento.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.web.springboot.ivento.model.CustomerOrderEntity;
import com.web.springboot.ivento.model.EOrderStatus;
import com.web.springboot.ivento.model.OrderedProductEntity;

/**
 * @author mokht
 * 
 *         Unit test using @DataJpaTest that focuses only on JPA components.
 *         Using this annotation will disable full auto-configuration and
 *         instead apply only configuration relevant to JPA tests.
 *
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
class CustomerOrderRepositoryTest {

//	@SpringBootTest loads full application context, exactly like how you start a Spring container when you run your Spring Boot application.
//	@WebMvcTest loads only the web layer, which includes security, filter, interceptors, etc for handling request/response. Typically you would write tests for methods under @Controller or @RestController.
//	@DataJpaTest loads only configuration for JPA. It uses an embedded in-memory h2 if not specified otherwise.

	private static final Logger logger = LoggerFactory.getLogger(CustomerOrderRepositoryTest.class);

	@Autowired
	private CustomerOrderRepository customerOrderRepository;

	private CustomerOrderEntity customerOrderEntity1;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		logger.info("@BeforeEach - executes before each test method in this class");

		List<OrderedProductEntity> lOrderedProductEntities = new ArrayList<>();
		lOrderedProductEntities
				.add(new OrderedProductEntity(123L, "T_P_1", "TEST_PRODUCT_1", "TPRODUCT", 1L, 50L, 50L));
		lOrderedProductEntities
				.add(new OrderedProductEntity(321L, "T_P_2", "TEST_PRODUCT_2", "TPRODUCT", 1L, 50L, 50L));

		customerOrderEntity1 = new CustomerOrderEntity(123L, "Customer Test", "0111234567", "Skyridge, Malaysia",
				"Customer Test Sdn Bhd", "Customer Test Sdn Bhd, Malaysia", lOrderedProductEntities, 100L,
				EOrderStatus.NEW_ORDER, LocalDateTime.now());

		customerOrderRepository.save(customerOrderEntity1);

	}

	@Test
	void test_Search_ExistCustomerOrder_FilterBy_CustomerName() {

		String search = "Customer Test";

		List<CustomerOrderEntity> entities = customerOrderRepository.searchByCustomerName(search).get();

		assertThat(entities).isNotEmpty(); // Check results is not empty
		assertThat(!entities.get(0).getOrderedProducts().isEmpty() && entities.get(0).getOrderedProducts().size() == 2)
				.isTrue(); // Check ordered products is not empty and got 2 product listed

	}

}
