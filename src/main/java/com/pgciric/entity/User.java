package com.pgciric.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "users")
public class User{
	
	@Id
	@Column
	@NotEmpty(message = "Morate popuniti polje!")
	@Size(max = 30, message = "Maksimalan broj karaktera je 30!")
	private String username;
	
	@Column
	@NotEmpty(message = "Morate popuniti polje!")
	@Size(max = 68, message = "Maksimalan broj karaktera je 68!")
	private String password;
	
	@Column
	@NotEmpty(message = "Morate popuniti polje!")
	@Size(max = 30, message = "Maksimalan broj karaktera je 30!")
	private String name;
	
	@Column
	@NotEmpty(message = "Morate popuniti polje!")
	@Size(max = 30, message = "Maksimalan broj karaktera je 30!")
	private String surname;

	@Column
	private boolean enabled;
	
	@JoinTable(name = "authorities", joinColumns =  @JoinColumn(name="username"), inverseJoinColumns = @JoinColumn(name="authority"))
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	private List<Roles> authorities;
	
//	@Transient
//	private String authority;
	
	@Column(name="imageurl")
	private String imageURL;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String name, String surname, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Roles> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Roles> authorities) {
		this.authorities = authorities;
	}

//	public String getAuthority() {
//		return authority;
//	}
//
//	public void setAuthority(String authority) {
//		this.authority = authority;
//	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", name=" + name + ", surname=" + surname + ", enabled=" + enabled
				+ "]";
	}

	public List<GrantedAuthority> getGrantedAuth(){
		List<GrantedAuthority> list = new ArrayList<>();
		
		for(Roles r: authorities){
			GrantedAuthority ga = new GrantedAuthority() {
				
				@Override
				public String getAuthority() {
					return r.toString();
				}
			};
			list.add(ga);
		}
		
		return list;
	}

}
