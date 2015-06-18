package dk.telenor.javamail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dk.telenor.javamail.dao.FolderDao;
import dk.telenor.javamail.dto.FolderDTO;

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
