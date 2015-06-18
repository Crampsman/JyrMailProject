package dk.telenor.javamail.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import dk.telenor.javamail.dto.TextMessageDTO;

public interface MessageService {

	public void sendMessage(TextMessageDTO textMessage, String password) throws MessagingException, IOException;
	
	public void createMessages(String email, String password) throws MessagingException, IOException;
	
	public List<TextMessageDTO> getMessages(long folderId, String email);
	
	public TextMessageDTO getMessageById(long messageId);
	
	public void updateMessage(TextMessageDTO textMessage);
	
	public void transferMessageToFolder(long messageId, int folderId);
}
