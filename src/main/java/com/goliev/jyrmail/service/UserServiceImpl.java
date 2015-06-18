package com.goliev.jyrmail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.goliev.jyrmail.dao.UserDao;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDaoImpl;
	
	public long getUserIdByEmail(String email) {
		
		return userDaoImpl.getUserIdByEmail(email);
	}

}
