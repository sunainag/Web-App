package com.portal.springbootapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.portal.springbootapp.model.Login;
import com.portal.springbootapp.model.User;
import com.portal.springbootapp.service.UserService;

@Controller
@ControllerAdvice
public class LoginController {

	@Autowired
	UserService userService;

	@ModelAttribute
	public void genericMessage(Model model) {
		model.addAttribute("message", "I love programming");
	}

	@RequestMapping(value = "/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("login", new Login());
		return mv;
	}

	@PostMapping("/loginProcess")
	public ModelAndView loginProcess(@ModelAttribute("login") Login login) {
		User user = userService.validateUser(login);
		if(user!=null) {
			userService.getUserDetails(user);
			return new ModelAndView("welcome", "firstName", user.getFirstname());
		}else {
			return new ModelAndView("login", "message", "Username or Password is wrong!! Try again :)");
		}
		
	}
}
