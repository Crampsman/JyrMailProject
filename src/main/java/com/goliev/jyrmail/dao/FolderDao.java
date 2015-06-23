package com.goliev.jyrmail.dao;

import java.util.List;

import com.goliev.jyrmail.dto.FolderDTO;

public interface FolderDao {

	public  void createFolder(FolderDTO folder);
	
	public  long getFolderIdByName(String name);
	
	public FolderDTO getFolderById(long id);
	
	public  List<FolderDTO>  getFolders();
	
	public void updateFolder(FolderDTO folder);
	
	public void deleteFolder(long id);
	
}
