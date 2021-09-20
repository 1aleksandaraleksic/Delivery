package com.pgciric.service;

import org.springframework.web.multipart.MultipartFile;

import com.pgciric.entity.User;

public interface UserService {
	
	public void saveUser(User user, MultipartFile multipartFile) throws Exception;

}
