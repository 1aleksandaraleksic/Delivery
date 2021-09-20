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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pgciric.entity.Slider;
import com.pgciric.repo.SliderRepo;

@Service
public class SliderServiceImp implements SliderService{
	
	@Autowired
	private SliderRepo sliderRepo;
	
    @Value(value = "${com.cloudinary.CLOUDINARY_URL}")
    private String CONFIG;

	@Override
	public void saveSlider(Slider slider, MultipartFile multipartFile) throws Exception {
		if (multipartFile.getSize() == 0) {
			sliderRepo.save(slider);
       } else if (multipartFile.getSize() != 0 && slider.getImageURL() != null) {

           Cloudinary cloudinary = new Cloudinary(CONFIG);
           String public_id = slider.getImageURL().substring(60, 80);

           cloudinary.api().deleteResources(new ArrayList<>(
                           Arrays.asList(public_id)),
                   ObjectUtils.emptyMap());

           File file = this.convertMultipartFileToFile(multipartFile);

           Map result = cloudinary.uploader().upload(file, ObjectUtils.asMap(
	    		    "public_id", "PGCiric/sliders/"+multipartFile.getOriginalFilename(), 
	    		    "overwrite", true,
	    		    "resource_type", "image"         
	    		));
           String url = (String) result.get("secure_url");

           slider.setImageURL(url);

           sliderRepo.save(slider);
           file.delete();

       } else {
           File file = this.convertMultipartFileToFile(multipartFile);

           Cloudinary cloudinary = new Cloudinary(CONFIG);

           Map result = cloudinary.uploader().upload(file, ObjectUtils.asMap(
	    		    "public_id", "PGCiric/slider/"+multipartFile.getOriginalFilename(), 
	    		    "overwrite", true,
	    		    "resource_type", "image"         
	    		));
           String url = (String) result.get("secure_url");

           slider.setImageURL(url);

           sliderRepo.save(slider);
           file.delete();
       }	
		
	}
	

	@Override
	public List<Slider> findAll() {
		return sliderRepo.findAll();
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

}
