package com.goliev.jyrmail.maintest;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;

import com.goliev.jyrmail.dao.FolderDao;
import com.goliev.jyrmail.dao.FolderDaoImpl;
import com.goliev.jyrmail.dao.MessageDaoImpl;
import com.goliev.jyrmail.dao.UserDao;
import com.goliev.jyrmail.dao.UserDaoImpl;
import com.goliev.jyrmail.dto.TextMessageDTO;
import com.goliev.jyrmail.dto.UserDTO;
import com.goliev.jyrmail.service.MessageService;
import com.goliev.jyrmail.service.MessageServiceImpl;
import com.goliev.jyrmail.smtp.SmtpMessageTransfer;
import com.goliev.jyrmail.util.AesScramblerPassword;
import com.goliev.jyrmail.util.MySqlDataSource;
import com.goliev.jyrmail.util.SmtpMessageSender;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MessageServiceTest {

	public static void main(String[] args) throws Exception {
		
		TextMessageDTO textMessages = new TextMessageDTO();
		textMessages.setMailFrom("golievalexey@gmail.com");
		textMessages.setMailTo("golievalexey@gmail.com");
		textMessages.setText("Test send 1");
		textMessages.setSubject("Test");
		textMessages.setDirectoryId(2);
		textMessages.setUserId(3);
		
		MessageServiceImpl service = new MessageServiceImpl();
		FolderDaoImpl folder = new FolderDaoImpl();
		folder.setDataSource(MySqlDataSource.getDataSource());
		MessageDaoImpl messageDao = new MessageDaoImpl();
		messageDao.setDataSource(MySqlDataSource.getDataSource());
		UserDaoImpl userDao = new UserDaoImpl();
		userDao.setDataSource(MySqlDataSource.getDataSource());
		
		service.setFolderDao(folder);
		service.setMessageDao(messageDao);
		service.setUserDao(userDao);
		
		service.setMessageTransfer(new SmtpMessageTransfer());
		
		service.sendMessage(textMessages, "Niggerriver86");
		
	
		
		
	
		
       // String s = AESScramblerPassword.encryptPassword("2564hegfrfrfrfh");
       // System.out.println(s);
		
		//UserDaoImpl userDao = new UserDaoImpl();
		//userDao.setDataSource(MySqlDataSource.getDataSource());
		//UserDTO user = userDao.getUserByEmail("golievalexey@gmail.com");
		//System.out.println(user);
		
	}

}
