package com.pgciric.service;

import java.util.List;

import com.pgciric.entity.CategoryDTO;

public interface BlogCategoryService {
	
	public List<CategoryDTO> findAllSumByCategory();

}
