/**
 * 
 */
package com.example.postgresdemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.model.Products;
import com.example.postgresdemo.service.ProductsService;

/**
 * @author Cybertech1
 *
 */
@RestController
@RequestMapping("/api/products")
public class ProductsController {
	
	/**
	 * 
	 */
	@Autowired
	private ProductsService productsSerevice;
	
	/**
	 * saveProducts
	 * @param products
	 * @return
	 */
	@PostMapping("/save")
	public Products saveProducts(final @Valid @RequestBody Products products)
	{
		return productsSerevice.saveProduc(products);
		
	}
	/**
	 * @param pId
	 */
	@DeleteMapping("/delete/{pId}")
	public void deleteProduct(final @PathVariable(value = "pId") Integer pId) 
	{
		productsSerevice.deleteProduc(pId);
	}


}
