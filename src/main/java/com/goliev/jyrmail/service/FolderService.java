package com.goliev.jyrmail.service;

import java.util.List;

import com.goliev.jyrmail.dto.FolderDTO;

public interface FolderService {
	
	public List<FolderDTO> getFolders();
	
	public long getFolderIdByName(String name);
}
