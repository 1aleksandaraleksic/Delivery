package com.pgciric.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pgciric.entity.BlogCategory;
import com.pgciric.entity.CategoryDTO;

public interface BlogCategoryRepo extends JpaRepository<BlogCategory, Integer>{
	
	@Query("select new com.pgciric.entity.CategoryDTO(c.id, c.name, count(b.category)) from Blog b "
			+ "join BlogCategory c on c.id = b.category "
			+ "group by c.name ")
	public List<CategoryDTO> findAllSumByCategory();

}
