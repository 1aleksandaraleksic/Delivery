package com.pgciric.entity;

public class ProductResult {
	
	private int id;
	
	private String name;
	
	private int price;
	
	private Category category;

	private String unitOfMeasure;

	private double quantity;
	
	private long timeOrder;

	public ProductResult() {
		// TODO Auto-generated constructor stub
	}

	public ProductResult(int id, String name, int price, Category category, String unitOfMeasure, double quantity) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.unitOfMeasure = unitOfMeasure;
		this.quantity = quantity;
	}
	
	public ProductResult(int id, String name, int price, Category category, String unitOfMeasure, double quantity, long timeOrder) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.unitOfMeasure = unitOfMeasure;
		this.quantity = quantity;
		this.timeOrder = timeOrder;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public long getTimeOrder() {
		return timeOrder;
	}

	public void setTimeOrder(long timeOrder) {
		this.timeOrder = timeOrder;
	}
	
	
	
}
