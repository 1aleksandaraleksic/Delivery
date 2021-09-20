package com.pgciric.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgciric.entity.Slider;
import com.pgciric.service.SliderService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class RestControllerSlider {

	@Autowired
	SliderService sliderService;
	
	@GetMapping("/sliders")
	public ResponseEntity<List<Slider>> getSlidersForIndex(){
		List<Slider> sliderList = sliderService.findAll();
		return new ResponseEntity<List<Slider>>(sliderList, HttpStatus.OK);
	}
}
