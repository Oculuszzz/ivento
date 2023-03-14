/**
 * 
 */
package com.web.springboot.ivento.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.springboot.ivento.payload.request.ProductRequest;
import com.web.springboot.ivento.payload.response.MessageResponse;
import com.web.springboot.ivento.payload.response.ProductResponse;
import com.web.springboot.ivento.properties.Literals;
import com.web.springboot.ivento.service.product.ProductServiceImpl;

import jakarta.validation.Valid;

/**
 * @author mokht
 *
 */
//@CrossOrigin(origins = "http://localhost:8091") // Already define in application properties
@CrossOrigin
@RestController
@RequestMapping("/api/auth/products")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductServiceImpl productService;

	@GetMapping(value = "")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<ProductResponse>> findAll() {

		return new ResponseEntity<>(productService.findAllProduct(), HttpStatus.OK);

	}

	@GetMapping(value = "/find-product")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<ProductResponse> findById(@RequestParam Long id) {

		return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);

	}

	@GetMapping(value = "search/")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<ProductResponse>> search(@RequestParam String search) {

		return new ResponseEntity<>(productService.searchProducts(search), HttpStatus.OK);

	}

	@PutMapping(value = "/update-product")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<MessageResponse> update(@Valid @RequestBody ProductRequest productRequest) {

		productService.updateProduct(productRequest);

		return ResponseEntity.ok(new MessageResponse(Literals.UPDATE_PRODUCT_OK));

	}

	@PostMapping(value = "/add-new-product")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Long> addNew(@Valid @RequestBody ProductRequest productRequest) {

		Long id = productService.addNewProduct(productRequest);

		return new ResponseEntity<>(id, HttpStatus.CREATED); // Return id product that successful created

	}

	@DeleteMapping(value = "/delete-product")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<MessageResponse> delete(@RequestParam Long id) {

		productService.deleteProductById(id);

		return ResponseEntity.ok(new MessageResponse(Literals.DELETE_PRODUCT_OK));

	}

}
