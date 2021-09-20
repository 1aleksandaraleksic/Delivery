package com.pgciric.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgciric.entity.Blog;
import com.pgciric.entity.BlogDTO;
import com.pgciric.entity.CategoryDTO;
import com.pgciric.service.BlogCategoryService;
import com.pgciric.service.BlogService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/blogs")
public class RestControllerBlog {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogCategoryService blogCategoryService;
	
	@GetMapping("/index")
	public ResponseEntity<List<Blog>> getBlogsForIndex(){

		return new ResponseEntity<List<Blog>>(blogService.findLastThree(),HttpStatus.OK);
	}
	
	@GetMapping({"/search/{search}/page/{page}"})
	public ResponseEntity<List<Blog>> getBlogs(@PathVariable("search") String search, @PathVariable("page") int page){

		List<Blog> list = blogService.searchAllBlog(page, search);
		return new ResponseEntity<List<Blog>>(list,HttpStatus.OK);
	}
	
	@GetMapping({"/category/{categoryId}/page/{page}"})
	public ResponseEntity<List<Blog>> getBlogs(@PathVariable("categoryId") int categoryId, @PathVariable("page") int page){
		if(categoryId==0) {
			List<Blog> list = blogService.paginationAndSortBlogListByFour(page);
			return new ResponseEntity<List<Blog>>(list,HttpStatus.OK);
		}
		List<Blog> list = blogService.paginationAndSortBlogListByCategoryByFour(page,categoryId);
		return new ResponseEntity<List<Blog>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/total-page")
	public ResponseEntity<Long> totalPageCountByCategory(@PathVariable("categoryId") int categoryId){

		Long totalPage = 0L;
		Long pages = 0L;

		if(categoryId == 0) {
			totalPage = blogService.totalPageCount();
		}else {
			totalPage = blogService.totalPageCountByCategory(categoryId);
		}
		
		if(totalPage%4 == 0) {
			pages = totalPage/4;
		}else {
			pages = (totalPage/4)+1;
		}

		return new ResponseEntity<Long>(pages,HttpStatus.OK);
	}
	
	@GetMapping("/last")
	public ResponseEntity<List<BlogDTO>> getLastThreeBlogs(){
		return new ResponseEntity<List<BlogDTO>>(blogService.getBlogsForAsideMenu(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Blog> getBlogById(@PathVariable int id){
		Blog blog = blogService.getById(id);
		blog.setNumOfView(blog.getNumOfView()+1);
		blogService.saveBlog(blog);
		
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDTO>> getCategoryList(){	
		List<CategoryDTO> list = blogCategoryService.findAllSumByCategory();

		return new ResponseEntity<List<CategoryDTO>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<List<Blog>> getBlogsByCategory(@PathVariable int id){	
		return new ResponseEntity<List<Blog>>(blogService.findAllByCategory(id),HttpStatus.OK);
	}

}
