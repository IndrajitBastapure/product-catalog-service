package com.springboot.productcatalogservice;

import org.springframework.boot.SpringApplication;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2 
public class ProductCatalogServiceApplication{
	
	static final Logger logger = LoggerFactory.getLogger(ProductCatalogServiceApplication.class);

	public static void main(String[] args) {
		
		logger.info("Starting ProductCatalogService application");
		SpringApplication.run(ProductCatalogServiceApplication.class, args);
		logger.debug("Starting ProductCatalogService application in debug with {} arguments", args.length);
	}
	
	@Bean
	   public Docket productApi() {
	      return new Docket(DocumentationType.SWAGGER_2).select()
	         .apis(RequestHandlerSelectors.basePackage("com.springboot.productcatalogservice")).build();
	   }

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
