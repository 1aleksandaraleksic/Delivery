package com.pgciric.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgciric.entity.CategoryDTO;
import com.pgciric.repo.BlogCategoryRepo;

@Service
public class BlogCategoryServiceImpl implements BlogCategoryService{

	@Autowired
	private BlogCategoryRepo blogCategoryRepo;

	@Override
	public List<CategoryDTO> findAllSumByCategory() {
		return blogCategoryRepo.findAllSumByCategory();
	}

}
