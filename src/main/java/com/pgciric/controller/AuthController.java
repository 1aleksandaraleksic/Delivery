package com.pgciric.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	@GetMapping("/loginPage")
	public String getLoginPage() {
		
		return"loginPage";
	}
	

}
