package dk.telenor.javamail.service;

import java.util.List;

import dk.telenor.javamail.dto.FolderDTO;

public interface FolderService {
	
	public List<FolderDTO> getFolders();
	
	public long getFolderIdByName(String name);
}
