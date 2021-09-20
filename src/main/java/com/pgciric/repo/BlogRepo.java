package com.pgciric.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pgciric.entity.Blog;
import com.pgciric.entity.BlogDTO;
import com.pgciric.entity.Product;

public interface BlogRepo extends JpaRepository<Blog, Integer>{

	@Query("select b from Blog b where id=:id")
	public Blog findBlogById(Integer id);
	
	@Query("select new com.pgciric.entity.BlogDTO(b.id, b.title, b.numOfView, b.imageURL, count(c.blog.id)) from Blog b " + 
			"join Comment c on c.blog.id = b.id "+
			"group by c.blog.id " + 
			"order by b.date desc ")
	public List<BlogDTO> getLastBlogsForAsideMenu();

	@Query("select b from Blog b where b.category.id=:id")
	public List<Blog> findAllByCategory(int id);
	
	@Query("select b from Blog b where b.category.id=:categoryId")
	public Page<Blog> findAllByCategory(Pageable paegeable, @Param("categoryId") int categoryId);
	
	@Query("select b from Blog b where concat (b.title, b.category, b.description, b.textArea) like %?1%")
	public Page<Blog> searchAllBlog(@Param("serach") String search,Pageable pageable);
	
	@Query("select count (*) from Blog")
	public Long totalPageCount();
	
	@Query("select count (*) from Blog b where b.category.id=:categoryId")
	public Long totalPageCountByCategory(@Param("categoryId") int categoryId);
	
	@Query("select b from Blog b")
	public List<Blog> findLastThree();

}
