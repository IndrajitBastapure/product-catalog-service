package com.springboot.productcatalogservice.service;

import java.util.List;
import java.util.Optional;

import com.springboot.productcatalogservice.entity.Product;

public interface ProductService {

	List<Product> getAllProducts();
	Optional findById(long id);
	Product saveProduct(Product product);
	void deleteProductById(long id);
	void deleteAllProducts();
	List<Product> findByManufacturer(String manufacturer);
	List<Product> findByName(String name);
	
}
