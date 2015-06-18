package dk.telenor.javamail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dk.telenor.javamail.dao.UserDao;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDaoImpl;
	
	public long getUserIdByEmail(String email) {
		
		return userDaoImpl.getUserIdByEmail(email);
	}

}
