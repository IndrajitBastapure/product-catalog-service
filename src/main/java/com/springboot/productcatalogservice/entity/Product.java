package com.springboot.productcatalogservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="quantity")
	private int quantity;
	@Column(name="manufacturer")
	private String manufacturer;
	
	public Product() {}
	
	public Product(String name, int quantity, String manufacturer) {
		this.name = name;
		this.quantity = quantity;
		this.manufacturer = manufacturer;
	}
	
	public Product(Long id, String name, int quantity, String manufacturer) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.manufacturer = manufacturer;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	

}
