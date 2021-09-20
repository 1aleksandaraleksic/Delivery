package com.pgciric.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pgciric.entity.User;
import com.pgciric.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
    @Value(value = "${com.cloudinary.CLOUDINARY_URL}")
    private String CONFIG;

	@Override
	public void saveUser(User user, MultipartFile multipartFile) throws Exception {

		if (multipartFile.getSize() == 0) {
			 userRepo.save(user);
        } else if (multipartFile.getSize() != 0 && user.getImageURL() != null) {

            Cloudinary cloudinary = new Cloudinary(CONFIG);
            String public_id = user.getImageURL().substring(60, 80);

            cloudinary.api().deleteResources(new ArrayList<>(
                            Arrays.asList(public_id)),
                    ObjectUtils.emptyMap());

            File file = this.convertMultipartFileToFile(multipartFile);

            Map result = cloudinary.uploader().upload(file, ObjectUtils.asMap(
	    		    "public_id", "PGCiric/users/"+multipartFile.getOriginalFilename(), 
	    		    "overwrite", true,
	    		    "resource_type", "image"         
	    		));
            String url = (String) result.get("secure_url");

            user.setImageURL(url);
            
            userRepo.save(user);
            file.delete();

        } else {
            File file = this.convertMultipartFileToFile(multipartFile);

            Cloudinary cloudinary = new Cloudinary(CONFIG);

            Map result = cloudinary.uploader().upload(file, ObjectUtils.asMap(
	    		    "public_id", "PGCiric/users/"+multipartFile.getOriginalFilename(), 
	    		    "overwrite", true,
	    		    "resource_type", "image"         
	    		));
            String url = (String) result.get("secure_url");

            user.setImageURL(url);

            userRepo.save(user);
            file.delete();
        }	
		
	}
	
	/**
	 * Convert MultipartFile in File
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

}
