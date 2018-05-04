package com.portal.springbootapp.service;

import com.portal.springbootapp.model.Login;
import com.portal.springbootapp.model.User;

public interface UserService {

	void register(User user);

	User validateUser(Login login);
	
	default void getUserDetails(User user) {
		System.out.println(user.toString());
	}
}
