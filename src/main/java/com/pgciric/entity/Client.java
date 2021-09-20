package com.pgciric.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Client {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@Column
	private String adress;
	@Column
	private String municipality;
	@Column
	private String detailed;
	@Column
	private String number;
	
	@OneToMany(mappedBy = "client" ,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	private List<Order> orderList;

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public Client(String name, String adress, String municipality, String detalied, String number, List<Order> orderList) {
		super();
		this.name = name;
		this.adress = adress;
		this.municipality = municipality;
		this.detailed = detalied;
		this.number = number;
		this.orderList = orderList;
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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDetailed() {
		return detailed;
	}

	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", adress=" + adress + ", municipality=" + municipality + "]";
	}
	
	
	
}
