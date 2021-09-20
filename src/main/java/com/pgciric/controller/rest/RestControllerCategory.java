package com.pgciric.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgciric.entity.CategoryDTO;
import com.pgciric.service.CategoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/categories")
public class RestControllerCategory {
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping({"","/"})
	public ResponseEntity<List<CategoryDTO>> getCategoryList(){	
		List<CategoryDTO> list = categoryService.findAllSumByCategory();

		return new ResponseEntity<List<CategoryDTO>>(list,HttpStatus.OK);
	}

}
