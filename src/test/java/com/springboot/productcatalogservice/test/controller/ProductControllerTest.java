package com.springboot.productcatalogservice.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import java.util.Arrays;
import java.util.List;
 
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.springboot.productcatalogservice.controller.ProductController;
import com.springboot.productcatalogservice.entity.Product;
import com.springboot.productcatalogservice.service.ProductService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
	
	@MockBean
	ProductService productService;
	 
	@Autowired
	MockMvc mockMvc;

	@Test
	public void testgetAll() throws Exception {
	   Product product = new Product("ELITEBOOK", 10, "HP");
	   List<Product> productList = Arrays.asList(product);
	 
	   Mockito.when(productService.getAllProducts()).thenReturn(productList);
   
	   mockMvc.perform(get("/api/products"))
	       .andExpect(status().isOk())
	       .andExpect(jsonPath("$", Matchers.hasSize(1)))
	       .andExpect(jsonPath("$[0].name", Matchers.is("ELITEBOOK")));
	  }
	
}
