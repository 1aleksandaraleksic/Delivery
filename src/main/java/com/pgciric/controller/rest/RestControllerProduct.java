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

import com.pgciric.entity.Product;
import com.pgciric.repo.ProductRepo;
import com.pgciric.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class RestControllerProduct {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepo productRepo;
	
	
//	@PostMapping("/products")
//	public ResponseEntity<Product> createProduct(@RequestBody Product product){
//		
//		product = productService.saveProduct(product);
//		
//		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
//	}	
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProductList(){	
		System.out.println("ussao u /products ");
		return new ResponseEntity<List<Product>>(productService.findAll(),HttpStatus.OK);
	}
//	
//	@GetMapping("/products/page/{page}")
//	public ResponseEntity<List<Product>> getFourProductInProductList(@PathVariable("page")int page){	
//		return new ResponseEntity<List<Product>>(productService.paginationAndSortProductListByFour(page),HttpStatus.OK);
//	}
	
	@GetMapping({"/product/search/{search}/page/{page}"})
	public ResponseEntity<List<Product>> getSearchProduct(@PathVariable("search") String search, @PathVariable("page") int page){

		List<Product> list = productService.searchAllProduct(search, page);
		return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
	}
	

	@GetMapping("/products/category/{categoryId}/page/{page}")
	public ResponseEntity<List<Product>> getFourProductByCategory(@PathVariable("categoryId") int categoryId, @PathVariable("page")int page){	
		if(categoryId == 0) {
			return new ResponseEntity<List<Product>>(productService.paginationAndSortProductListByFour(page),HttpStatus.OK);
		}
		return new ResponseEntity<List<Product>>(productService.paginationAndSortProductListByCategoryByFour(page, categoryId),HttpStatus.OK);
	}
	
	
	@GetMapping("/products/category/{categoryId}/total-page")
	public ResponseEntity<Long> totalPageCountByCategory(@PathVariable("categoryId") int categoryId){

		Long totalPage = 0L;
		Long pages = 0L;

		if(categoryId == 0) {
			totalPage = productService.totalPageCount();
		}else {
			totalPage = productService.totalPageCountByCategory(categoryId);
		}
		
		if(totalPage%4 == 0) {
			pages = totalPage/4;
		}else {
			pages = (totalPage/4)+1;
		}

		return new ResponseEntity<Long>(pages,HttpStatus.OK);
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") int id){
		Product p = productService.getById(id);
		
		if(p == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Product>(p ,HttpStatus.OK);
		}

	}
	
//	@PutMapping("/products/{id}")
//	public ResponseEntity<Product> updateProduct(@PathVariable("id") int id){
//		
//		Product p = productService.getById(id);
//		
//		if(p == null) {
//			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
//		} else {
//			productService.updateProduct(p);
//			return new ResponseEntity<Product>(p ,HttpStatus.OK);
//		}
//
//	}
//	
//	@DeleteMapping("/products/{id}")
//	public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id) throws Exception{
//		
//		Product p = productService.getById(id);
//		
//		if(p == null) {
//			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
//		}
//		
//		productService.deleteProduct(id);		
//		return new ResponseEntity<Product>(HttpStatus.OK);
//	}
	
//	@GetMapping("/products/category/{categoryId}")
//	public ResponseEntity<List<Product>> getProductListByCategory(@PathVariable("categoryId") int categoryId){	
//		return new ResponseEntity<List<Product>>(productService.productsByCategory(categoryId), HttpStatus.OK);
//	}
//	
//	@GetMapping("/products/pageable")
//	public Page<Product> retriveProductWithPaging(@Param(value = "size") int size, @Param(value="page") int page){
//		
//		Pageable requestPage = PageRequest.of(page, size);
//		Page<Product> products = productRepo.findAll(requestPage);
//		return products;
//		
//	}
	

}
