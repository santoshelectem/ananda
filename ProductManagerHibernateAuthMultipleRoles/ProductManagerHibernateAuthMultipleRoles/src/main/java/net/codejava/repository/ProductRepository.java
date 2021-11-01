package net.codejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
