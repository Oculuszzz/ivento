/**
 * 
 */
package com.web.springboot.ivento.service.product;

import java.util.List;

import com.web.springboot.ivento.payload.request.ProductRequest;
import com.web.springboot.ivento.service.exception.ProductException;

/**
 * @author mokht
 *
 */
public interface ProductService {

	public List<ProductRequest> findAllProduct();

	public ProductRequest findProductById(Long id);

	public Boolean updateProduct(ProductRequest productRequest) throws ProductException;

	public Long addNewProduct(ProductRequest productRequest) throws ProductException;

	public List<ProductRequest> searchProducts(String searchRequest);

	public void deleteProductById(Long id);

}
