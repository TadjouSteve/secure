package com.ste.secure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VueController {
	
	@GetMapping("/public/index")
	public String getIndexPage() {
		return "index";
	}
}
