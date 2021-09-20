package com.pgciric.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity(name = "delivery")
public class Delivery {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String date;
	
	@Column(name = "is_it_over")
	private boolean isItOver;


	public Delivery() {
		
	}
	
	public Delivery(String date, boolean isItOver) {
		super();
		this.date = date;
		this.isItOver = isItOver;
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

	public boolean getIsItOver() {
		return isItOver;
	}

	public void setIsItOver(boolean isItOver) {
		this.isItOver = isItOver;
	}

	@Override
	public String toString() {
		return "Delivery [id=" + id + ", date=" + date  + "]";
	}
	
	
	
}
