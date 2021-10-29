/**
 * 
 */
package com.example.postgresdemo.controller;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Product;
import com.example.postgresdemo.repository.ProductRepository;
import com.example.postgresdemo.service.ProductService;

/**
 * @author Cybertech1
 *
 */
@RestController
@RequestMapping("/api")
public class ProductController {
	/**
	 * Logger
	 */
	static private Logger log = LoggerFactory.getLogger(ProductController.class);
	/**
	 * productService
	 */
	@Autowired
	private ProductService productService;

	/**
	 * productRepository
	 */
	@Autowired
	private ProductRepository productRepository;

	/**
	 * @param product
	 * @return
	 */
	@PostMapping("/product/create")
	public Product createProduct(final @Valid @RequestBody Product product) {
		log.info("start ProductController createProduct :");
		Product saveUpdate = null;
		try {
			if (product != null) {
				saveUpdate = productService.saveUpdate(product);
			}

		} catch (Exception e) {
			// TODO: handle exception
			log.error("error ProductController createProduct :" + e.getMessage());
		}
		log.info("end ProductController createProduct :");
		return saveUpdate;
	}

	/**
	 * getProductById
	 * @param productid
	 * @return
	 */
	@GetMapping("/product/{id}")
	public Product getProductById(final @PathVariable(value = "id") Long productId) {
		log.info("start ProductController getProductById :");
		try {
			if (productId != null) {
				return productService.fetchById(productId);
			}
		} catch (Exception e) {
			log.error("error ProductController getProductById :" + e.getMessage());

		}
		log.info("end ProductController getProductById :");
		return null;
	}

	/**
	 * updateProduct
	 * @param productid
	 * @param productDetails
	 * @return
	 */
	@PutMapping("/product/{productid}")
	public ResponseEntity<Product> updateProduct(final @PathVariable long productId,final @RequestBody Product productDetails) {
		log.info("start ProductController updateProduct :");
		final Product updateProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product not exist with id: " + productId));
		updateProduct.setName(productDetails.getName());
		updateProduct.setType(productDetails.getType());
		productRepository.save(updateProduct);
		log.info("end ProductController updateProduct :");
		return ResponseEntity.ok(updateProduct);
	}
}
