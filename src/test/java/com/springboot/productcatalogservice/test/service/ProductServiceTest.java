package com.springboot.productcatalogservice.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
import java.util.List;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.springboot.productcatalogservice.repository.ProductRepository;
import com.springboot.productcatalogservice.service.impl.ProductServiceImpl;
import com.springboot.productcatalogservice.entity.Product;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@InjectMocks
    ProductServiceImpl productService;
      
    @Mock
    ProductRepository productRepository;
    
    @Test
    public void testGetAllProducts(){
        List<Product> testProductList = new ArrayList<Product>();
        Product hpProduct = new Product("ELITEBOOK", 10, "HP");
        Product lenevoProduct = new Product("IDEAPAD", 20, "LENEVO");
        Product dellProduct = new Product("INSPIRON", 50, "DELL");
          
        testProductList.add(hpProduct);
        testProductList.add(lenevoProduct);
        testProductList.add(dellProduct);
          
        when(productRepository.findAll()).thenReturn(testProductList);
          
        List<Product> actualProductList = productService.getAllProducts();
          
        assertEquals(3, actualProductList.size());
        verify(productRepository, times(1)).findAll();
    }
    
    @Test
    public void testCreateProduct(){
    	
       Product product = new Product("ELITEBOOK", 10, "HP");
       productService.saveProduct(product);
          
       verify(productRepository, times(1)).save(product);
    }
	
}
