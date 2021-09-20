package com.pgciric.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgciric.entity.Category;
import com.pgciric.entity.CategoryDTO;
import com.pgciric.repo.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public List<Category> findAll() {
		return categoryRepo.findAll();
	}

	@Override
	public Category getById(int id) {
		return categoryRepo.getOne(id);
	}

	@Override
	public List<CategoryDTO> findAllSumByCategory() {
		return categoryRepo.findAllSumByCategory();
	}

}
