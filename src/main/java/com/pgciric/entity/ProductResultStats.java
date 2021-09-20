package com.pgciric.entity;

public class ProductResultStats {

	private int id;
	
	private String name;
	
	private String month;
	
	private int year;
	
	private Category category;

	private String unitOfMeasure;

	private double quantity;
	
	private long timeOrder;

	public ProductResultStats(int id, String name, String month, int year, Category category, String unitOfMeasure, double quantity,
			long timeOrder) {
		super();
		this.id = id;
		this.name = name;
		this.month = month;
		this.category = category;
		this.unitOfMeasure = unitOfMeasure;
		this.quantity = quantity;
		this.timeOrder = timeOrder;
		this.year = year;
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

	public String getMonth() {
		return month;
	}

	public void setMonth(String date) {
		this.month = date;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
}
