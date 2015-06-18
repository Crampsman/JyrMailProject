package dk.telenor.javamail.dao;

import java.util.List;

import dk.telenor.javamail.dto.FolderDTO;

public interface FolderDao {

	public  void createFolder(FolderDTO folder);
	
	public  long getFolderIdByName(String name);
	
	public  List<FolderDTO>  getFolders();
	
	public void updateFolder(FolderDTO folder);
	
	public void deleteFolder(long id);
	
}
