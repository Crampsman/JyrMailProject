package com.goliev.jyrmail.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goliev.jyrmail.dao.FolderDao;
import com.goliev.jyrmail.dao.MessageDao;
import com.goliev.jyrmail.dao.UserDao;
import com.goliev.jyrmail.dto.TextMessageDTO;
import com.goliev.jyrmail.smtp.SmtpMessageTransfer;
import com.goliev.jyrmail.util.ImapMessageReader;
import com.goliev.jyrmail.util.MessagesAddressParser;
import com.goliev.jyrmail.util.TextHtmlParser;

@Service
public class MessageServiceImpl implements MessageService {

    private static final String STORE_TYPE = "imaps";

    private static final String IMAP_HOST = "imap.gmail.com";

    private ImapMessageReader messageReader = new ImapMessageReader();

    private SmtpMessageTransfer messageTransfer = new SmtpMessageTransfer();

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private FolderDao folderDao;

    @Autowired
    private UserDao userDao;

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

	messageTransfer.sendMessageToSmtp(textMessage.getText(), textMessage.getSubject(), textMessage.getMailTo(), textMessage.getMailFrom(),
		password);

	messageDao.createMessage(textMessage);
    }

    public void createMessages(String email, String password) throws MessagingException, IOException {

	Store store = messageReader.createStore(IMAP_HOST, STORE_TYPE, email, password);

	List<TextMessageDTO> listMessages = new ArrayList<TextMessageDTO>();

	Message[] messages = messageTransfer.getMessagesFromSmtp(email, password, store);

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
	    textMessage.setMailFrom(MessagesAddressParser.getFromEmailAddress(message));
	    textMessage.setMailTo(MessagesAddressParser.getToEmailAddress(message));
	    textMessage.setText(TextHtmlParser.getHtmlText(messageTransfer.getMessageText((Part) message)));
	    textMessage.setCreateDate(message.getSentDate().getTime());

	    if (textMessage.getCreateDate().getTime() > messageDao.getMaxDate(email) || messageDao.getMaxDate(email) == -1) {
		listMessages.add(textMessage);
	    }
	}

	store.close();

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

    public List<TextMessageDTO> getMessages(long folderId, String email, int offset, int noOffRecords) {

	return messageDao.getMessagesByFolderId(folderId, email, offset, noOffRecords);
    }

    public String getRandomSpittles() {

	return messageDao.getRandomSpittles();
    }

    public int getNoOfRecords() {

	return messageDao.getNoOfRecords();
    }

}