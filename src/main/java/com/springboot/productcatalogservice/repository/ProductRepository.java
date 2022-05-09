package com.springboot.productcatalogservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.productcatalogservice.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByManufacturer(String manufacturer);

	List<Product> findByName(String name);

}
