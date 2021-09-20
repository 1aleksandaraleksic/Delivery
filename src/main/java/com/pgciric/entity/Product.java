package com.pgciric.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "product")
@Table
public class Product {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String name;
	
	@Column
	private int price;
	
	@JoinColumn(name = "category_id")
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Category category;
	
	@Column
	private String unitOfMeasure;
	
	@Column
	private String imageURL;
	
	@Column
	private String description;
	
	@Column
	private boolean isOnMainPage;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public Product(Category category) {
		this.category = category;
	}

	public Product(String name, int price, Category category, String unitOfMeasure, 
				   String description, String imageURL, boolean isOnMainPage) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
		this.unitOfMeasure = unitOfMeasure;
		this.imageURL = imageURL;
		this.description = description;
		this.isOnMainPage = isOnMainPage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsOnMainPage() {
		return isOnMainPage;
	}

	public void setIsOnMainPage(boolean isOnMainPage) {
		this.isOnMainPage = isOnMainPage;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category
				+ ", unitOfMeasure=" + unitOfMeasure + "]";
	}


	
	
}
