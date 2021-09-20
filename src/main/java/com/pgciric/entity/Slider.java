package com.pgciric.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table
public class Slider {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String imageURL;
	
	@Size(max = 100, message = "max 100 characther" )
	@Column
	private String title;
	
	@Size(max = 100, message = "max 100 characther" )
	@Column
	private String linkPath;
	
	@Column
	private int position;
	
	@Size(max = 45, message = "max 45 characther" )
	@Column
	private String linkName;
	
	@Column
	private boolean isVisible;
	
	public Slider() {
		
	}

	public Slider(String image, String title, String linkPath, int position, String linkName, boolean isVisible) {
		super();
		this.imageURL = image;
		this.title = title;
		this.linkPath = linkPath;
		this.position = position;
		this.isVisible = isVisible;
		this.linkName = linkName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLinkPath() {
		return linkPath;
	}

	public void setLinkPath(String linkPath) {
		this.linkPath = linkPath;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	@Override
	public String toString() {
		return "SlideDAO [id=" + id + ", image=" + imageURL + ", title=" + title + ", linkPath=" + linkPath + ", position="
				+ position + ", isVisible=" + isVisible + "]";
	}

}
