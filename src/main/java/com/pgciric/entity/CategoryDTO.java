package com.pgciric.entity;

public class CategoryDTO {

	public int id;
	public String name;
	public long listSize;
	
	public CategoryDTO(int id, String name, long listSize) {
		this.id = id;
		this.name = name;
		this.listSize = listSize;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public long getListSize() {
		return listSize;
	}

}
