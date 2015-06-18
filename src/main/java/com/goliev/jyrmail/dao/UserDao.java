package com.goliev.jyrmail.dao;


import java.util.List;

import com.goliev.jyrmail.dto.UserDTO;

public interface UserDao {
	
	public boolean createUser(UserDTO user) throws Exception;
	
	public UserDTO getUserById(long id);
	
	public UserDTO getUserByEmail(String email);
	
	public long getUserIdByEmail(String eMail);
	
	public List<UserDTO> getUsers();
	
	public void updateUser(UserDTO user);
	
	public void deleteUser(long id);
}
