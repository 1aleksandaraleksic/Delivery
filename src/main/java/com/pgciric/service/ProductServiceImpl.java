package com.pgciric.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pgciric.entity.Product;
import com.pgciric.entity.ProductResult;
import com.pgciric.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo productRepo;
	
    @Value(value = "${com.cloudinary.CLOUDINARY_URL}")
    private String CONFIG;

	@Override
	public void saveProduct(Product product, MultipartFile multipartFile) throws Exception {
		
		if (multipartFile.getSize() == 0) {
			 productRepo.save(product);
        } else if (multipartFile.getSize() != 0 && product.getImageURL() != null) {

            Cloudinary cloudinary = new Cloudinary(CONFIG);
            String public_id = product.getImageURL().substring(60, 80);

            cloudinary.api().deleteResources(new ArrayList<>(
                            Arrays.asList(public_id)),
                    ObjectUtils.emptyMap());

            File file = this.convertMultipartFileToFile(multipartFile);

            Map result = cloudinary.uploader().upload(file, ObjectUtils.asMap(
	    		    "public_id", "PGCiric/products/"+multipartFile.getOriginalFilename(), 
	    		    "overwrite", true,
	    		    "resource_type", "image"         
	    		));
            String url = (String) result.get("secure_url");

            product.setImageURL(url);
            
            productRepo.save(product);
            file.delete();

        } else {
            File file = this.convertMultipartFileToFile(multipartFile);

            Cloudinary cloudinary = new Cloudinary(CONFIG);

            Map result = cloudinary.uploader().upload(file, ObjectUtils.asMap(
	    		    "public_id", "PGCiric/products/"+multipartFile.getOriginalFilename(), 
	    		    "overwrite", true,
	    		    "resource_type", "image"         
	    		));
            String url = (String) result.get("secure_url");

            product.setImageURL(url);

            productRepo.save(product);
            file.delete();
        }	
	}

	@Override
	public void updateProduct(Product product) {
		productRepo.save(product);
		
	}

	@Override
	public void deleteProduct(int id) throws Exception {		
        Product product = productRepo.getOne(id);
        if (product.getImageURL() != null) {
            Cloudinary cloudinary = new Cloudinary(CONFIG);
            String public_id = product.getImageURL().substring(60, 80);

            cloudinary.api().deleteResources(new ArrayList<>(
                            Arrays.asList(public_id)),
                    ObjectUtils.emptyMap());
        }
		productRepo.deleteById(id);
	}

	@Override
	public Product getById(int id) {
		return productRepo.getOne(id);
	}

	@Override
	public List<Product> findAll() {
		return productRepo.findAll();
	}

	@Override
	public List<Product> productsByCategory(int categoryId) {
		return productRepo.productsByCategory(categoryId);
	}

	@Override
	public List<Product> searchProduct(String search) {
		return productRepo.searchProduct(search);
	}

	@Override
	public List<Product> searchProductByCategory(String search, int categoryId) {
		return productRepo.searchProductByCategory(search, categoryId);
	}

	@Override
	public List<ProductResult> totalProductsForDelivery(int deliveryId) {
		return productRepo.totalProductsForDelivery(deliveryId);
	}

	@Override
	public List<ProductResult> totalProductsForMonthlyDelivery(int month, int year) {
		return productRepo.totalProductsForMonthlyDelivery(month, year);
	}

	@Override
	public List<ProductResult> totalProductsForAllDelivery() {
		return productRepo.totalProductsForAllDelivery();
	}

	@Override
	public List<ProductResult> totalProductsForAllDeliveryByCategory(int categoryId) {
		return productRepo.totalProductsForAllDeliveryByCategory(categoryId);
	}

	@Override
	public List<ProductResult> findAllProductForClient(int clientId) {
		return productRepo.findAllProductForClient(clientId);
	}

	@Override
	public List<ProductResult> findAllProductForClientAverage(int clientId) {
		return productRepo.findAllProductForClientAverage(clientId);
	}

	@Override
	public List<Product> paginationAndSortProductListByFour(int page) {
		
		Pageable paegeable = PageRequest.of(page, 4);
		Page<Product> productList = productRepo.findAll(paegeable);
		return productList.toList();
	}
	
	@Override
	public List<Product> paginationAndSortProductListByCategoryByFour(int page, int categoryId) {
		
		Pageable paegeable = PageRequest.of(page, 4);
		Page<Product> productList = productRepo.findAllByCategory(paegeable,categoryId);
		return productList.toList();
	}
	
	@Override
	public Long totalPageCount() {
		return productRepo.totalPageCount();
	}
	

	@Override
	public Long totalPageCountByCategory(int categoryId) {
		return productRepo.totalPageCountByCategory(categoryId);
	}

    //Convert MultipartFile in File
	/**
	 * 
	 * @param multipartFile
	 * @return
	 */
    public File convertMultipartFileToFile(MultipartFile multipartFile) {

        File convFile = new File(multipartFile.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(multipartFile.getBytes());
            fos.close();

        } catch (IOException e) {

        }

        return convFile;
    }

	@Override
	public List<Product> searchAllProduct(String search, int page) {
		Pageable paegeable = PageRequest.of(page, 4);
		Page<Product> blogList = productRepo.searchAllProduct(search, paegeable);
		return blogList.toList();
	}

}
