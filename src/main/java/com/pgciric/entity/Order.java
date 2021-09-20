package com.pgciric.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String date;
	
	@JoinColumn(name = "id_client")
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"client","orderList"})
	private Client client;
	
	@JoinTable(name = "order_orderitem", joinColumns =  @JoinColumn(name="order_id"), inverseJoinColumns = @JoinColumn(name="order_item_id"))
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	private List<OrderItem> orderItemList;
	
	@JoinColumn(name = "id_delivery")
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	private Delivery delivery;
	
	@Column
	private String description;
	
	public Order() {
		this.date = formatDate();
	}
	
	public Order (Delivery delivery) {
		this.delivery=delivery;
		this.date = formatDate();
	}

	public Order(String date, List<OrderItem> orderItemList, Delivery delivery, Client client, String description) {
		super();
		this.date = formatDate();
		this.orderItemList = orderItemList;
		this.delivery = delivery;
		this.client = client;
		this.description = description;
	}
	
	public String formatDate() {
		SimpleDateFormat mdyFormat = new SimpleDateFormat("dd-MM-yyyy");
		String mdy = mdyFormat.format(new Date());
		return mdy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", client=" + client + ", orderItemList=" + orderItemList + ", delivery="
				+ delivery + "]";
	}

	
	
}
