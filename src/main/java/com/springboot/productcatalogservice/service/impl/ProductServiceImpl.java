package com.springboot.productcatalogservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.productcatalogservice.entity.Product;
import com.springboot.productcatalogservice.repository.ProductRepository;
import com.springboot.productcatalogservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
	}

	@Override
	public List<Product> findByName(String name) {
		
		return productRepository.findByName(name);
	}

	@Override
	public Optional<Product> findById(long id) {
		
		return productRepository.findById(id);
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteProductById(long id) {
		productRepository.deleteById(id);
	}

	@Override
	public void deleteAllProducts() {
		productRepository.deleteAll();
	}

	@Override
	public List<Product> findByManufacturer(String manufacturer) {
		return productRepository.findByManufacturer(manufacturer);
	}

}
