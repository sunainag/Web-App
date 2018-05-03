package com.portal.springbootapp;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String home(Map<String, Object> model) {
		model.put("message", "Hello from new user");
		return "index";
	} 
	
	 @RequestMapping("/next")
	    public String next(Map<String, Object> model) {
	        model.put("message", "You are in new page !!");
	        return "next";
	    }
}
