package com.pgciric.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Comment {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(max = 45, message = "max 45 charachter")
	@Column
	private String name;
	
	@Size(max = 45, message = "max 45 charachter")
	@Column
	private String email;
	
	@Size(max = 1500, message = "max 1500 charachter")
	@Column
	private String content;
	
	@Column
	private String date;
	
	@JoinColumn(name = "blogId")
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JsonIgnore
	private Blog blog;
	
	@Column
	private boolean isEnabled;
	
	public Comment() {
		this.date = formatDate();
		this.isEnabled = true;
	}

	public Comment(String name, String email, String content, String date) {
		super();
		this.name = name;
		this.email = email;
		this.content = content;
		this.date = formatDate();
		this.isEnabled = true;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", email=" + email + ", content=" + content + "]";
	}
	
	

}
