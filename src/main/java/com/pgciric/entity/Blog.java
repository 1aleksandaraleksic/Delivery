package com.pgciric.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;

@Entity
@Table
public class Blog {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String imageURL;
	
	@Valid
	@JoinColumn(name = "categoryId")
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private BlogCategory category;
	
	@Column
	@Size(min = 2, max = 255, message = "min 2 characters, max 255 characters.")
	private String title;
	
	@Valid
	@JoinColumn(name = "creator")
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private User user;
	
	@Column
	private String date;
	
	@OneToMany(mappedBy = "blog",cascade = CascadeType.ALL)
	private List<Comment> commentList;
	
	@Column
	private int numOfView;
	
	@Column
	private String textArea;
	
	@Column
	@Size(max = 500, message = "max 500 characters.")
	private String description;
	
	@Column
	private boolean isImportant;
	
	public Blog() {
		this.date = formatDate();
	}

	public Blog(String image, BlogCategory category, String title, List<Comment> commentList, int numOfView,
			String textArea, User user, String description) {
		super();
		this.imageURL = image;
		this.category = category;
		this.title = title;
		this.commentList = commentList;
		this.numOfView = numOfView;
		this.textArea = textArea;
		this.user = user;
		this.description = description;
		this.isImportant = false;
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

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String image) {
		this.imageURL = image;
	}

	public BlogCategory getCategory() {
		return category;
	}

	public void setCategory(BlogCategory category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public int getNumOfView() {
		return numOfView;
	}

	public void setNumOfView(int numOfView) {
		this.numOfView = numOfView;
	}

	public String getTextArea() {
		return textArea;
	}

	public void setTextArea(String textArea) {
		this.textArea = textArea;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsImportant() {
		return isImportant;
	}

	public void setIsImportant(boolean isImportant) {
		this.isImportant = isImportant;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", image=" + imageURL + ", category=" + category + ", title=" + title + ", date=" + date
				+ ", commentList=" + commentList + ", numOfView=" + numOfView + ", textArea=" + textArea + "]";
	}
	
	

}
