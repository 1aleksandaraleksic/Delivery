package com.pgciric.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Table
@Entity
public class Authorities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	@JoinColumn(name = "FKhjuy9y4fd8v5m3klig05ktofg")
	private String username;
	
	@Column
	@JoinColumn(name= "FK7njdse381jigfb09pdqspvg1i")
	private String authority;

	public String getAuthority() {
		return authority;
	}

	public Authorities() {
		// TODO Auto-generated constructor stub
	}

	public Authorities(String username, String authority) {
		super();
		this.username = username;
		this.authority = authority;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return authority;
	}

}
