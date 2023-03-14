package com.web.springboot.ivento.service.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.web.springboot.ivento.model.ProductEntity;
import com.web.springboot.ivento.payload.request.ProductRequest;
import com.web.springboot.ivento.payload.response.ProductResponse;
import com.web.springboot.ivento.repository.ProductRepository;
import com.web.springboot.ivento.service.exception.ProductException;
import com.web.springboot.ivento.service.exception.ProductNotFoundException;

/**
 * @author mokht
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

//	@Autowired
//	ProductRepository productRepository;

	private final ProductRepository productRepository;

	/**
	 * Improved design by @Autowired instance on constructor (constructor
	 * injection)are clearly identified, reusable InjectMocks and dependencies
	 * visible
	 * 
	 * @param productRepository
	 * 
	 */
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<ProductResponse> findAllProduct() {

		List<ProductEntity> lProductEntities = productRepository.findAll();
		List<ProductResponse> lProduct = new ArrayList<>();

		for (ProductEntity entity : lProductEntities) {

			ProductResponse p = new ProductResponse();
			p.setId(entity.getId());
			p.setProductCode(entity.getProductCode());
			p.setName(entity.getName());
			p.setBrand(entity.getBrand());
			p.setPrice(entity.getPrice());
			p.setQuantity(entity.getQuantity());
			p.setLastUpdate(entity.getLastUpdate());

			lProduct.add(p);
		}

		return lProduct;
	}

	@Override
	public ProductResponse findProductById(Long id) {

		ProductEntity entity = productRepository.findById(id).orElse(null);

		if (entity != null) {

			return composeProductPojo(entity);

		} else {

			return null;

		}

	}

	@Override
	public Boolean updateProduct(ProductRequest productRequest) throws ProductException {

		try {

			ProductEntity entity = productRepository.findById(productRequest.getId()).orElse(null);

			if (entity != null) {

				entity.setName(productRequest.getName());
				entity.setProductCode(productRequest.getProductCode());
				entity.setBrand(productRequest.getBrand());
				entity.setPrice(productRequest.getPrice());
				entity.setQuantity(productRequest.getQuantity());
				entity.setLastUpdate(productRequest.getLastUpdate());

				productRepository.save(entity);

				return Boolean.TRUE;

			} else {

				throw new ProductNotFoundException("Product not found!");

			}

		} catch (Exception e) {

			throw new ProductException(e.getMessage());

		}

	}

	@Override
	public Long addNewProduct(ProductRequest productRequest) throws ProductException {

		try {

			ProductEntity entity = new ProductEntity();

			entity.setName(productRequest.getName());
			entity.setProductCode(productRequest.getProductCode());
			entity.setBrand(productRequest.getBrand());
			entity.setPrice(productRequest.getPrice());
			entity.setQuantity(productRequest.getQuantity());
			entity.setLastUpdate(productRequest.getLastUpdate());

			return productRepository.save(entity).getId();

		} catch (Exception e) {

			throw new ProductException(e.getMessage());

		}

	}

	@Override
	public List<ProductResponse> searchProducts(String searchRequest) {

		List<ProductResponse> lProducts = new ArrayList<>();
		List<ProductEntity> entities = productRepository.searchProducts(searchRequest).orElse(Collections.emptyList());

		for (ProductEntity entity : entities) {

			lProducts.add(composeProductPojo(entity));

		}

		return lProducts;
	}

	@Override
	public void deleteProductById(Long id) {

		productRepository.deleteById(id);

	}

	private ProductResponse composeProductPojo(ProductEntity entity) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setId(entity.getId());
		productResponse.setProductCode(entity.getProductCode());
		productResponse.setName(entity.getName());
		productResponse.setBrand(entity.getBrand());
		productResponse.setPrice(entity.getPrice());
		productResponse.setQuantity(entity.getQuantity());
		productResponse.setLastUpdate(entity.getLastUpdate());

		return productResponse;
	}

}
