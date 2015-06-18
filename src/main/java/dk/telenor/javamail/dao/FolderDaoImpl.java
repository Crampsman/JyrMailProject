package dk.telenor.javamail.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import dk.telenor.javamail.dto.FolderDTO;

@Component
public class FolderDaoImpl implements FolderDao {

	private static final Logger LOGGER = Logger.getLogger(FolderDaoImpl.class);

	private JdbcTemplate temp;

	@Autowired
	public void setDataSource(BasicDataSource dataSource) {
		this.temp = new JdbcTemplate(dataSource);
	}

	public void createFolder(FolderDTO folder) {

		LOGGER.info("Start method. Create folder: " + folder.getName());

		String sql = "INSERT INTO Folder (name) VALUES (?)";
		Object[] params = new Object[] { folder.getName() };

		int affectedFolders = temp.update(sql, params);

		LOGGER.info("Folder created: " + (affectedFolders > 0 ? true : false));

	}

	public long getFolderIdByName(String name) {

		LOGGER.info("Start method. Searching folder id with name: " + name);

		String sql = "SELECT directory_id FROM Folder WHERE name = ?";

		Object[] params = new Object[] { name };

		long folderId = temp.queryForObject(sql, params, Long.class);

		LOGGER.info("Folder with name: " + name
				+ (folderId > 0 ? " was found." : " was not found."));

		return folderId;

	}

	public List<FolderDTO> getFolders() {

		LOGGER.info("Start method. Getting folders");

		String sql = "SELECT directory_id, name FROM Folder";

		List<FolderDTO> folders = new ArrayList<FolderDTO>();
		List<Map<String, Object>> messageRows = temp.queryForList(sql);

		for (Map<String, Object> mesRow : messageRows) {

			FolderDTO folder = new FolderDTO();

			folder.setDirectoryId(Long.parseLong(String.valueOf(mesRow
					.get("directory_id"))));
			folder.setName(mesRow.get("name").toString());

			folders.add(folder);
		}

		LOGGER.info("Folders was returned: " + folders.size());

		return folders;
	}

	public void updateFolder(FolderDTO folder) {

		LOGGER.info("Start method. Update folder with neme " + folder.getName());

		String sql = "UPDATE Folder SET name = ? WHERE directory_id = ?";

		Object[] params = new Object[] { folder.getName(),
				folder.getDirectoryId() };

		int affectedFolders = temp.update(sql, params);

		LOGGER.info("Folder updated " + (affectedFolders > 0 ? true : false));

	}

	public void deleteFolder(long id) {

		LOGGER.info("Start method. Delete folder by id: " + id);

		String sql = "DELETE FROM Folder WHERE folder_id = ?";

		Object[] params = new Object[] { id };

		int affectedFolders = temp.update(sql, params);

		LOGGER.info("Folders deleted " + (affectedFolders > 0 ? true : false));

	}

}
