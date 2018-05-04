package com.portal.springbootapp.service;

import org.springframework.stereotype.Service;

import com.portal.springbootapp.model.Login;
import com.portal.springbootapp.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public void register(User user) {
		System.out.println("User registered");
	}

	@Override
	public User validateUser(Login login) {
		System.out.println("User validated");
		User user = new User();
		user.setFirstname(login.getUsername());
		return user;
	}

}
