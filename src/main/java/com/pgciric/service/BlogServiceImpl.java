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
import com.pgciric.entity.Blog;
import com.pgciric.entity.BlogDTO;
import com.pgciric.repo.BlogRepo;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private BlogRepo blogRepo;
	
	 @Value(value = "${com.cloudinary.CLOUDINARY_URL}")
	    private String CONFIG;

	@Override
	public List<Blog> findAll() {
		return blogRepo.findAll();
	}

	/**
	 * 
	 */
	@Override
	public Blog getById(int id) {
		return blogRepo.findBlogById(id);
	}

	
	@Override
	public List<BlogDTO> getBlogsForAsideMenu() {
		List<BlogDTO> list = blogRepo.getLastBlogsForAsideMenu();
	
		for(int i=0;i<list.size();i++) {
			if(i>2) {
				list.remove(i);
			}
		}
		
		return list;
	}

	@Override
	public List<Blog> findAllByCategory(int id) {
		return blogRepo.findAllByCategory(id);
	}

	@Override
	public void saveBlog(Blog blog, MultipartFile multipartFile) throws Exception {
		if (multipartFile.getSize() == 0) {
			 blogRepo.save(blog);
       } else if (multipartFile.getSize() != 0 && blog.getImageURL() != null) {

           Cloudinary cloudinary = new Cloudinary(CONFIG);
           String public_id = blog.getImageURL().substring(60, 80);

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

           blog.setImageURL(url);
           
           blogRepo.save(blog);
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

           blog.setImageURL(url);

           blogRepo.save(blog);
           file.delete();
       }
		
	}
	
    //Convert MultipartFile in File
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
	public void saveBlog(Blog blog) {
		blogRepo.save(blog);		
	}

	@Override
	public List<Blog> paginationAndSortBlogListByFour(int page) {
		Pageable paegeable = PageRequest.of(page, 4);
		Page<Blog> blogList = blogRepo.findAll(paegeable);
		return blogList.toList();
	}

	@Override
	public List<Blog> paginationAndSortBlogListByCategoryByFour(int page, int categoryId) {
		Pageable paegeable = PageRequest.of(page, 4);
		Page<Blog> blogList = blogRepo.findAllByCategory(paegeable, categoryId);
		return blogList.toList();
	}

	@Override
	public Long totalPageCount() {
		return blogRepo.totalPageCount();
	}

	@Override
	public Long totalPageCountByCategory(int categoryId) {
		return blogRepo.totalPageCountByCategory(categoryId);
	}

	@Override
	public List<Blog> findLastThree() {
		return blogRepo.findLastThree();
	}

	@Override
	public List<Blog> searchAllBlog(int page, String search) {
		Pageable paegeable = PageRequest.of(page, 4);
		Page<Blog> blogList = blogRepo.searchAllBlog(search, paegeable);
		return blogList.toList();
	}

}
