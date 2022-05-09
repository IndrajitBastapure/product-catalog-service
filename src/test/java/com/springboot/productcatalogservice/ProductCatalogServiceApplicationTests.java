package com.springboot.productcatalogservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.springboot.productcatalogservice.controller.ProductController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductCatalogServiceApplicationTests {

	@Autowired
	ProductController productController;
	
	@Test
	void contextLoads() {
		Assertions.assertThat(productController).isNot(null);
	}

}
