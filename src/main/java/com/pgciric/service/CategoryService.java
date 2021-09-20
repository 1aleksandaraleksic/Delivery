package com.pgciric.service;

import java.util.List;

import com.pgciric.entity.Category;
import com.pgciric.entity.CategoryDTO;

public interface CategoryService {

	public List<Category> findAll();
	public Category getById(int id);
	public List<CategoryDTO> findAllSumByCategory();
}
