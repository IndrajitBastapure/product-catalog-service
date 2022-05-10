package com.springboot.productcatalogservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.productcatalogservice.dto.ProductDTO;
import com.springboot.productcatalogservice.entity.Product;
import com.springboot.productcatalogservice.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductService productService;

	// Handler Methods

	@GetMapping("/products")
	public ResponseEntity<List<ProductDTO>> getAllProducts() {

		List<ProductDTO> productList = new ArrayList<>();

		productList = productService.getAllProducts().stream()
				.map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());

		if (productList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(productList, HttpStatus.OK);

	}

	@GetMapping("/products/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") long id) {
		Product product = productService.findById(id);
		ProductDTO productData = modelMapper.map(product, ProductDTO.class);

		return new ResponseEntity<>(productData, HttpStatus.OK);

	}

	@PostMapping("/products")
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product) {
		try {
			Product productEntity = modelMapper.map(product, Product.class);
			Product productCreated = productService.saveProduct(
					new Product(productEntity.getName(), productEntity.getQuantity(), productEntity.getManufacturer()));
			ProductDTO productResponse = modelMapper.map(productCreated, ProductDTO.class);
			
			return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") long id, @RequestBody ProductDTO productDto) {

		Product productRequest = modelMapper.map(productDto, Product.class);
		Product productEntity = productService.findById(id);

		productEntity.setName(productRequest.getName());
		productEntity.setQuantity(productRequest.getQuantity());
		productEntity.setManufacturer(productRequest.getManufacturer());
		
		Product productSaved = productService.saveProduct(productEntity);
		
		ProductDTO productResponse = modelMapper.map(productSaved, ProductDTO.class);
		
		return new ResponseEntity<>(productResponse, HttpStatus.OK);

	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
		try {
			productService.deleteProductById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/products")
	public ResponseEntity<HttpStatus> deleteAllProducts() {
		try {
			productService.deleteAllProducts();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/products/name")
	public ResponseEntity<List<ProductDTO>> findProductByName(@RequestParam String name) {
		try {
			
			List<ProductDTO> productList = productService.findByName(name).stream().map(product-> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());

			if (productList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(productList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/products/manufacturer")
	public ResponseEntity<List<ProductDTO>> findProductByManufacturer(@RequestParam String manufacturer) {
		try {
			List<ProductDTO> productList = productService.findByManufacturer(manufacturer).stream().map(product-> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());

			if (productList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(productList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
