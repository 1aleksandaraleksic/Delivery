package com.pgciric.entity;

public class ClientDTO {
	
	private int id;
	private String name;
	private String adress;
	private String municipality;
	private String detailed;
	private String number;
	private long orderList;
	
	public ClientDTO(int id, String name, String adress, String municipality, String detailed, String number,
			long orderList) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.municipality = municipality;
		this.detailed = detailed;
		this.number = number;
		this.orderList = orderList;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAdress() {
		return adress;
	}

	public String getMunicipality() {
		return municipality;
	}

	public String getDetailed() {
		return detailed;
	}

	public String getNumber() {
		return number;
	}

	public long getOrderList() {
		return orderList;
	}
	
	

}
