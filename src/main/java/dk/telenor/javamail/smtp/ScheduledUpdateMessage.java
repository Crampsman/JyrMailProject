package dk.telenor.javamail.smtp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import dk.telenor.javamail.dao.MessageDao;
import dk.telenor.javamail.dao.UserDao;
import dk.telenor.javamail.dto.UserDTO;
import dk.telenor.javamail.service.MessageService;
import dk.telenor.javamail.util.AesScramblerPassword;

@Component
public class ScheduledUpdateMessage {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private MessageService messageService;
	
	@Scheduled(fixedDelay = 600000)
	public void updateMessageInDataBase() throws Exception {

		List<UserDTO> users = new ArrayList<UserDTO>();
		users = userDao.getUsers();

		for (UserDTO user : users) {
			messageService.createMessages(user.getEmail(), AesScramblerPassword.decryptPassword(user.getPassword()));
		}
	}
}
