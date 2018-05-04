package com.portal.springbootapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.portal.springbootapp.model.User;
import com.portal.springbootapp.service.UserService;

@Controller
public class RegisterationController {
	
	@Autowired
	UserService userService;

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/registerProcess")
	public ModelAndView registerProcess(@ModelAttribute("user") User user, BindingResult result) {
		if(!result.hasErrors())
			userService.register(user);
		return new ModelAndView("welcome", "firstName", user.getFirstname());
	}
}
