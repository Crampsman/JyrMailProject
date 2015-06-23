package com.goliev.jyrmail.dao;

import java.util.List;

import com.goliev.jyrmail.dto.TextMessageDTO;

public interface MessageDao {
    
    	public String getRandomSpittles();
	
	public  void createMessage(TextMessageDTO message);
	
	public void insertMessages(List<TextMessageDTO> listMessages);
	
	public  TextMessageDTO getMessageById(long id);
	
	public  List<TextMessageDTO> getMessagesByFolderId(long id, String email, int offset, int noOffRecords);
	
	public int getNoOfRecords();
	
	public  void transferMessageToFolder(long messageId, int folderId);
	
	public void updateMessage(TextMessageDTO message);
	
	public long getMaxDate(String email);
}
