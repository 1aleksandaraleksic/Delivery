package com.pgciric.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Order_orderitem {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	@Column(name = "order_id")
	public int orderId;
	
	@Column(name = "order_item_id")
	public int orderItemId;

	public Order_orderitem() {
		// TODO Auto-generated constructor stub
	}

	public Order_orderitem(int orderId, int orderItemId) {
		super();
		this.orderId = orderId;
		this.orderItemId = orderItemId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	
	
}
