/**
 * 
 */
package com.web.springboot.ivento.service.product;

import java.util.List;

import com.web.springboot.ivento.payload.request.ProductRequest;
import com.web.springboot.ivento.payload.response.ProductResponse;

/**
 * @author mokht
 *
 */
public interface ProductService {

	public List<ProductResponse> findAllProduct();

	public ProductResponse findProductById(Long id);

	public Boolean updateProduct(ProductRequest productRequest);

	public Long addNewProduct(ProductRequest productRequest);

	public List<ProductResponse> searchProducts(String searchRequest);

	public void deleteProductById(Long id);

}
