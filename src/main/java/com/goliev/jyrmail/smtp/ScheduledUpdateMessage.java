package com.goliev.jyrmail.smtp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.goliev.jyrmail.dao.MessageDao;
import com.goliev.jyrmail.dao.UserDao;
import com.goliev.jyrmail.dto.UserDTO;
import com.goliev.jyrmail.service.MessageService;
import com.goliev.jyrmail.util.AesScramblerPassword;

@Component
public class ScheduledUpdateMessage {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private MessageService messageService;
	
	@Scheduled(fixedDelay = 20000)
	public void updateMessageInDataBase() throws Exception {

		List<UserDTO> users = new ArrayList<UserDTO>();
		users = userDao.getUsers();

		for (UserDTO user : users) {
			messageService.createMessages(user.getEmail(), AesScramblerPassword.decryptPassword(user.getPassword()));
		}
	}
}
