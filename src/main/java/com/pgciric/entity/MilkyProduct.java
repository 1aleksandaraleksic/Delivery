package com.pgciric.entity;

public class MilkyProduct {
	
	private int id;
	
	private String name;
	
	private double quantity;
	
	private long total;
	
	private Category category;
	
	public MilkyProduct() {
		// TODO Auto-generated constructor stub
	}

	public MilkyProduct(int id, String name, double quantity, long total, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.total = total;
		this.category = category;
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

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	

}
