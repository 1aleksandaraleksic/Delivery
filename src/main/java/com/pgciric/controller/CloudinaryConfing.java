package com.pgciric.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryConfing extends Cloudinary {
	
	private static Cloudinary CONFIG = null;
	
	private CloudinaryConfing() {
		CONFIG = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "cikin5",
				"api_key", "912287697684818",
				"api_secret", "5HsXUsKXiyT-j0z0ERuNx9_Ng5E"));
	}
	
	public static Cloudinary getCloudinary() {
		if(CONFIG == null) {
			return CONFIG = new CloudinaryConfing();
		}else {
			return CONFIG;
		}
	}	

}
