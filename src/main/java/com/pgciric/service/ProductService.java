package com.pgciric.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pgciric.entity.Product;
import com.pgciric.entity.ProductResult;

public interface ProductService {

	public void saveProduct(Product product, MultipartFile multipartFile) throws Exception;
	public Product getById(int id);
	public void updateProduct(Product product);
	public void deleteProduct(int id) throws Exception;
	public List<Product> findAll();
	public List<Product> productsByCategory(int categoryId);
	public List<Product> searchProduct(String search);
	public List<Product> searchAllProduct(String search, int page);
	public List<Product> searchProductByCategory(String search, int categoryId);
	public List<ProductResult> totalProductsForDelivery(int deliveryId);
	public List<ProductResult> totalProductsForMonthlyDelivery(int month, int year);
	public List<ProductResult> totalProductsForAllDelivery();
	public List<ProductResult> totalProductsForAllDeliveryByCategory(int categoryId);
	public List<ProductResult> findAllProductForClient(int clientId);
	public List<ProductResult> findAllProductForClientAverage(int clientId);
	public List<Product> paginationAndSortProductListByFour(int page);
	public List<Product> paginationAndSortProductListByCategoryByFour(int page, int categoryId);
	public Long totalPageCount();
	public Long totalPageCountByCategory(int categoryId);
	
}
