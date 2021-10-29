/**
 * 
 */
package com.example.postgresdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.Product;

/**
 * @author Cybertech1
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
