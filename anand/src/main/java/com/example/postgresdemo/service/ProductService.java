/**
 * 
 */
package com.example.postgresdemo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Employee;
import com.example.postgresdemo.model.Product;
import com.example.postgresdemo.repository.ProductRepository;

/**
 * @author Cybertech1
 *
 */
@Service
public class ProductService {
	
	/**
	 * productRepository
	 */
	@Autowired()
	final private ProductRepository productRepository;

	/**
	 * @param product
	 * @return
	 */
	public Product saveUpdate(final @Valid Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	
	/**
	 * @param productid
	 * @return
	 */
	public Product fetchById(final Long productid) {

		return productRepository.findById(productid)
				.orElseThrow(() -> new ResourceNotFoundException("Product"));
	}
	/**
	 * @param productdetails
	 * @param productid
	 * @return
	 */
	public Product updateProduct(final Product productdetails,final Long productid) {

		final Product product = productRepository.findById(productid)
				.orElseThrow(() -> new ResourceNotFoundException("Product"));
		product.setName(productdetails.getName());
		productRepository.save(product);
		return productdetails;

	}


	/**
	 * @param productRepository
	 */
	public ProductService(final ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	/*public Product updateProduct(@Valid Product productdetails, Long productid) {
		// TODO Auto-generated method stub
		return null;
	}*/
	

}
