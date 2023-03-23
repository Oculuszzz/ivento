package com.web.springboot.ivento.service.product;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.web.springboot.ivento.model.ProductEntity;
import com.web.springboot.ivento.payload.request.ProductRequest;
import com.web.springboot.ivento.payload.response.ProductResponse;
import com.web.springboot.ivento.properties.Literals;
import com.web.springboot.ivento.repository.ProductRepository;
import com.web.springboot.ivento.service.exception.ProductNotFoundException;
import com.web.springboot.ivento.utils.MessageUtils;

/**
 * @author mokht
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	private final MessageUtils messageUtils;

	/**
	 * Improved design by @Autowired instance on constructor (constructor
	 * injection)are clearly identified, reusable InjectMocks and dependencies
	 * visible
	 * 
	 * @param productRepository
	 * 
	 */
	public ProductServiceImpl(ProductRepository productRepository, MessageUtils messageUtils) {
		this.productRepository = productRepository;
		this.messageUtils = messageUtils;
	}

	@Override
	public List<ProductResponse> findAllProduct() {

		List<ProductEntity> lProductEntities = productRepository.findAll();

		return lProductEntities.stream().map(ProductResponse::new).toList();

	}

	@Override
	public ProductResponse findProductById(Long id) {

		return productRepository.findById(id).map(ProductResponse::new).orElseThrow(() -> new ProductNotFoundException(
				String.format("Product id - %d, %s", id, messageUtils.getMessage(Literals.ERROR_PRODUCT_NOT_FOUND))));

	}

	@Override
	public Boolean updateProduct(ProductRequest productRequest) {

		ProductEntity entity = productRepository.findById(productRequest.getId())
				.orElseThrow(() -> new ProductNotFoundException(String.format("Product id - %d, %s",
						productRequest.getId(), messageUtils.getMessage(Literals.ERROR_PRODUCT_NOT_FOUND))));

		entity.setName(productRequest.getName());
		entity.setProductCode(productRequest.getProductCode());
		entity.setBrand(productRequest.getBrand());
		entity.setPrice(productRequest.getPrice());
		entity.setQuantity(productRequest.getQuantity());
		entity.setLastUpdate(productRequest.getLastUpdate());

		productRepository.save(entity);

		return Boolean.TRUE;

	}

	@Override
	public Long addNewProduct(ProductRequest productRequest) {

		ProductEntity entity = new ProductEntity(productRequest.getProductCode(), productRequest.getName(),
				productRequest.getBrand(), productRequest.getQuantity(), productRequest.getPrice(),
				productRequest.getLastUpdate());

		return productRepository.save(entity).getId();

	}

	@Override
	public List<ProductResponse> searchProducts(String searchRequest) {

		List<ProductEntity> entities = productRepository.searchProducts(searchRequest).orElse(Collections.emptyList());

		return entities.stream().map(ProductResponse::new).toList();

	}

	@Override
	public void deleteProductById(Long id) {

		productRepository.deleteById(id);

	}

}
