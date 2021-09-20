package com.pgciric.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table
public class Contact {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(max = 70, message = "max 70 characther")
	@Column
	private String name;
	
	@Size(max = 70, message = "max 70 characther")
	@Column
	private String email;
	
	@Size(max = 1000, message = "max 1000 characther")
	@Column
	private String message;
	
	@Column
	private Date date;
	
	@Column
	private boolean isSeen;
	
	public Contact() {
		// TODO Auto-generated constructor stub
	}

	public Contact(@Size(max = 70, message = "max 70 characther") String name,
			@Size(max = 70, message = "max 70 characther") String email,
			@Size(max = 1000, message = "max 1000 characther") String message, Date date, boolean isSeen) {
		super();
		this.name = name;
		this.email = email;
		this.message = message;
		this.date = date;
		this.isSeen = isSeen;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isSeen() {
		return isSeen;
	}

	public void setSeen(boolean isSeen) {
		this.isSeen = isSeen;
	}
	
	
}
