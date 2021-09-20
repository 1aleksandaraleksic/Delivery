package com.pgciric.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pgciric.entity.Blog;
import com.pgciric.entity.BlogDTO;

public interface BlogService {

	public void saveBlog(Blog blog);
	public void saveBlog(Blog blog, MultipartFile multipartFile) throws Exception;
	public List<Blog> findAll();
	public List<Blog> findLastThree();
	public List<Blog> paginationAndSortBlogListByFour(int page);
	public List<Blog> paginationAndSortBlogListByCategoryByFour(int page, int cateogoryId);
	public Long totalPageCount();
	public Long totalPageCountByCategory(int categoryId);
	public List<Blog> findAllByCategory(int id);
	public List<BlogDTO> getBlogsForAsideMenu();
	public Blog getById(int id);
	public List<Blog> searchAllBlog(int page, String search);
}
