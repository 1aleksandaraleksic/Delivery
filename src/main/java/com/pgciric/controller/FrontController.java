package com.pgciric.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FrontController {
	
	@GetMapping({"/", "/index"})
	public String getIndexPage(Model model) {
		
		return "index";
	}

}
