package dk.telenor.javamail.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import dk.telenor.javamail.dao.FolderDao;
import dk.telenor.javamail.dao.MessageDao;
import dk.telenor.javamail.dao.UserDao;
import dk.telenor.javamail.dto.TextMessageDTO;
import dk.telenor.javamail.smtp.SmtpMessageTransfer;
import dk.telenor.javamail.util.MessagesAddressParser;
import dk.telenor.javamail.util.TextHtmlParser;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private FolderDao folderDao;

	@Autowired
	private UserDao userDao;
	
	
	private SmtpMessageTransfer messageTransfer = new SmtpMessageTransfer();
		
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	public void setFolderDao(FolderDao folderDao) {
		this.folderDao = folderDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setMessageTransfer(SmtpMessageTransfer messageTransfer) {
		this.messageTransfer = messageTransfer;
	}

	public void sendMessage(TextMessageDTO textMessage, String password) throws MessagingException, IOException {
		
		textMessage.setCreateDate(new Date().getTime());
		
		messageTransfer.sendMessageToSmtp(textMessage.getText(), textMessage.getSubject(), textMessage.getMailTo(), textMessage.getMailFrom(), password);

		messageDao.createMessage(textMessage);
	}

	public void createMessages(String email, String password)
			throws MessagingException, IOException {

		List<TextMessageDTO> listMessages = new ArrayList<TextMessageDTO>();

		Message[] messages = messageTransfer.getMessagesFromSmtp(email,
				password);
		
		long folderId = folderDao.getFolderIdByName("INBOX");


		for (Message message : messages) {

			TextMessageDTO textMessage = new TextMessageDTO();
			
		
			if (message.getSubject() == null) {
				textMessage.setSubject("No subject");
			} else {
				textMessage.setSubject(message.getSubject().toString());
			}

			textMessage.setDirectoryId(folderId);
			textMessage.setUserId(userDao.getUserIdByEmail(email));
			textMessage.setMailFrom(MessagesAddressParser
					.getFromEmailAddress(message));
			textMessage.setMailTo(MessagesAddressParser
					.getToEmailAddress(message));
			textMessage.setText(TextHtmlParser.getHtmlText(messageTransfer
					.getMessageText((Part) message)));
			textMessage.setCreateDate(message.getSentDate().getTime());

			if (textMessage.getCreateDate().getTime() > messageDao.getMaxDate(email) || messageDao.getMaxDate(email) == -1) {
				listMessages.add(textMessage);
			}
		}

		messageDao.insertMessages(listMessages);

	}

	public TextMessageDTO getMessageById(long messageId) {

		return messageDao.getMessageById(messageId);
	}

	public void updateMessage(TextMessageDTO textMessage) {
		// TODO Auto-generated method stub

	}

	public void transferMessageToFolder(long messageId, int folderId) {

		messageDao.transferMessageToFolder(messageId, folderId);
	}

	public List<TextMessageDTO> getMessages(long folderId, String email) {

		return messageDao.getMessagesByFolderId(folderId, email);
	}

}