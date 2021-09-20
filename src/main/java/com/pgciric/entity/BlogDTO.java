package com.pgciric.entity;

public class BlogDTO {
	
	private int id;
	private String title;
	private int numOfView;
	private String imageURL;
	private long commentListSize;
	
	public BlogDTO(int id, String title, int numOfView, String imageURL, long commentListSize) {
		super();
		this.id = id;
		this.title = title;
		this.numOfView = numOfView;
		this.imageURL = imageURL;
		this.commentListSize = commentListSize;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getNumOfView() {
		return numOfView;
	}

	public String getImageURL() {
		return imageURL;
	}

	public long getCommentListSize() {
		return commentListSize;
	}
	
	
}
