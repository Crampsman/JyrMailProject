package dk.telenor.javamail.maintest;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import dk.telenor.javamail.dao.FolderDao;
import dk.telenor.javamail.dao.FolderDaoImpl;
import dk.telenor.javamail.dao.MessageDaoImpl;
import dk.telenor.javamail.dao.UserDao;
import dk.telenor.javamail.dao.UserDaoImpl;
import dk.telenor.javamail.dto.TextMessageDTO;
import dk.telenor.javamail.dto.UserDTO;
import dk.telenor.javamail.service.MessageService;
import dk.telenor.javamail.service.MessageServiceImpl;
import dk.telenor.javamail.smtp.SmtpMessageTransfer;
import dk.telenor.javamail.smtp.SmtpMessageTransfer;
import dk.telenor.javamail.util.MySqlDataSource;
import dk.telenor.javamail.util.AesScramblerPassword;
import dk.telenor.javamail.util.SmtpMessageSender;

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
