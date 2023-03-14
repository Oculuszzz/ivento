/**
 * 
 */
package com.web.springboot.ivento.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.web.springboot.ivento.model.ProductEntity;

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
class ProductRepositoryTest {

//	@SpringBootTest loads full application context, exactly like how you start a Spring container when you run your Spring Boot application.
//	@WebMvcTest loads only the web layer, which includes security, filter, interceptors, etc for handling request/response. Typically you would write tests for methods under @Controller or @RestController.
//	@DataJpaTest loads only configuration for JPA. It uses an embedded in-memory h2 if not specified otherwise.

	private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryTest.class);

	@Autowired
	private ProductRepository productRepository;

	private ProductEntity product1;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		logger.info("@BeforeEach - executes before each test method in this class");

		product1 = new ProductEntity("E_PRODUCT_CODE1", "PRODUCT_NAME_!", "BRAND_PRODUCT", 50L, 500L,
				LocalDateTime.now());
		productRepository.save(product1);

	}

	@Test
	void test_Search_ExistProduct_FilterBy_Brand_Name_ProductCode() {

		String search = "PRODUCT_CODE1";

		assertThat(productRepository.searchProducts(search)).isPresent();

		search = "PRODUCT_NAME_";

		assertThat(productRepository.searchProducts(search)).isPresent();

		search = "BRAND_PRODUCT";

		assertThat(productRepository.searchProducts(search)).isPresent();

	}

	@Test
	void test_Search_NonExistProduct_FilterBy_Brand_Name_ProductCode() {

		String search = "FAKED_CODE1";

		assertThat(productRepository.searchProducts(search).get()).isEmpty();

		search = "FAKED_NAME_";

		assertThat(productRepository.searchProducts(search).get()).isEmpty();

		search = "FAKED_PRODUCT";

		assertThat(productRepository.searchProducts(search).get()).isEmpty();

	}

}
