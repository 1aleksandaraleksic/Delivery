package com.pgciric.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pgciric.entity.Category;
import com.pgciric.entity.CategoryDTO;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

	@Query("select new com.pgciric.entity.CategoryDTO(c.id, c.name, count(p.category)) from product p "
			+ "join Category c on c.id = p.category "
			+ "group by c.name ")
	public List<CategoryDTO> findAllSumByCategory();

}
