package dk.telenor.javamail.dao;

import java.util.List;

import dk.telenor.javamail.dto.TextMessageDTO;

public interface MessageDao {
	
	public  void createMessage(TextMessageDTO message);
	
	public void insertMessages(List<TextMessageDTO> listMessages);
	
	public  TextMessageDTO getMessageById(long id);
	
	public  List<TextMessageDTO> getMessagesByFolderId(long id, String email);
	
	public  void transferMessageToFolder(long messageId, int folderId);
	
	public void updateMessage(TextMessageDTO message);
	
	public long getMaxDate(String email);
}
