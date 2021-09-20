package com.pgciric.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pgciric.entity.Slider;

public interface SliderService {

	public void saveSlider(Slider slider, MultipartFile multipartFile) throws Exception;

	public List<Slider> findAll();

}
