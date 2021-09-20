package com.pgciric.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "roles")
@Table
public class Roles {
	
	@Id
	@Column
	private String authority;
	
	public Roles() {
		// TODO Auto-generated constructor stub
	}

	public Roles(String authority) {
		super();
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		
		return authority.substring(5);
	}
	
	
	
}
