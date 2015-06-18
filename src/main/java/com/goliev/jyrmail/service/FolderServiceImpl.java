package com.goliev.jyrmail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.goliev.jyrmail.dao.FolderDao;
import com.goliev.jyrmail.dto.FolderDTO;

@Service
public class FolderServiceImpl implements FolderService {

	@Autowired()
	private FolderDao folderDao;
	
	public List<FolderDTO> getFolders() {
		
		return folderDao.getFolders();
	}

	public long getFolderIdByName(String name) {
		
		return folderDao.getFolderIdByName(name);
	}

}
