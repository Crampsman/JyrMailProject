package com.goliev.jyrmail.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.goliev.jyrmail.dto.TextMessageDTO;

@Component
public class MessageDaoImpl implements MessageDao {

	private static final Logger LOGGER = Logger.getLogger(MessageDaoImpl.class);

	private JdbcTemplate temp;

	@Autowired
	public void setDataSource(BasicDataSource dataSource) {
		this.temp = new JdbcTemplate(dataSource);
	}

	public void createMessage(TextMessageDTO message) {

		LOGGER.info("Start method. Create mesage with parameters: "
				+ "[ subject: " + message.getSubject() + " mail from: "
				+ message.getMailFrom() + " mail to " + message.getMailTo()
				+ " ]");

		String sql = "INSERT INTO Message (directory_id, user_id, subject, text, mail_from, mail_to, create_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

		Object[] params = new Object[] { message.getDirectoryId(),
				message.getUserId(), message.getSubject(), message.getText(),
				message.getMailFrom(), message.getMailTo(),
				message.getCreateDate() };

		int affectedMessages = temp.update(sql, params);

		LOGGER.info("Message updated: " + (affectedMessages > 0 ? true : false)
				+ "[ subject: " + message.getSubject() + " mail from: "
				+ message.getMailFrom() + " mail to " + message.getMailTo()
				+ " ] ");

	}

	public void insertMessages(final List<TextMessageDTO> listMessages) {

		LOGGER.info("Start method. Create  list mesage with size: "
				+ listMessages.size());

		String sql = "INSERT INTO Message (directory_id, user_id, subject, text, mail_from, mail_to, create_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

		int[] affectedMessages = temp.batchUpdate(sql,
				new BatchPreparedStatementSetter() {

					public void setValues(PreparedStatement ps, int i)
							throws SQLException {

						TextMessageDTO message = listMessages.get(i);

						ps.setLong(1, message.getDirectoryId());
						ps.setLong(2, message.getUserId());
						ps.setString(3, message.getSubject());
						ps.setString(4, message.getText());
						ps.setString(5, message.getMailFrom());
						ps.setString(6, message.getMailTo());
						ps.setTimestamp(7, message.getCreateDate());
					}

					public int getBatchSize() {

						return listMessages.size();
					}
				});

		LOGGER.info("Messages list created: "
				+ (affectedMessages.length > 0 ? true : false)
				+ listMessages.size() + " was created");

	}

	public TextMessageDTO getMessageById(long id) {

		LOGGER.info("Start method. Getting message by id: " + id);

		String sql = "SELECT message_id, directory_id, subject, text, mail_to, mail_from, create_date FROM Message WHERE message_id = ?";

		TextMessageDTO textMessage = temp.queryForObject(sql,
				new Object[] { id },

				new RowMapper<TextMessageDTO>() {

					public TextMessageDTO mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						TextMessageDTO mess = new TextMessageDTO();
						mess.setDirectoryId(rs.getLong("directory_id"));
						mess.setMessageId(rs.getLong("message_id"));
						mess.setMailTo(rs.getString("mail_to"));
						mess.setMailFrom(rs.getString("mail_from"));
						mess.setCreateDate(rs.getTimestamp("create_date"));
						mess.setSubject(rs.getString("subject"));
						mess.setText(rs.getString("text"));

						return mess;
					}

				});

		LOGGER.info("Retrieved message with id: " + id);

		return textMessage;
	}

	public List<TextMessageDTO> getMessagesByFolderId(long id, String email) {

		LOGGER.info("Start method. Getting messages by folder id: " + id);

		String sql = "SELECT directory_id, mes.user_id, message_id, subject, text, mail_from, mail_to, create_date FROM Message AS mes, User AS us WHERE e_mail = ? AND mes.user_id = us.user_id AND directory_id = ? ORDER BY create_date DESC";

		List<TextMessageDTO> messList = new ArrayList<TextMessageDTO>();

		List<Map<String, Object>> messageRows = temp.queryForList(sql,
				new Object[] { email, id });

		for (Map<String, Object> mesRow : messageRows) {

			TextMessageDTO message = new TextMessageDTO();

			message.setDirectoryId(Long.parseLong(String.valueOf(mesRow
					.get("directory_id"))));
			message.setUserId(Long.parseLong(String.valueOf(mesRow
					.get("user_id"))));
			message.setMessageId(Long.parseLong(String.valueOf(mesRow
					.get("message_id"))));
			message.setMailFrom(String.valueOf(mesRow.get("mail_from")));
			message.setMailTo(String.valueOf(mesRow.get("mail_to")));
			message.setSubject(String.valueOf(mesRow.get("subject")));
			message.setText(String.valueOf(mesRow.get("text")));
			message.setCreateDate((Timestamp) mesRow.get("create_date"));

			messList.add(message);
		}

		LOGGER.info("Messages retrieved: size " + messList.size()
				+ " folder id " + id);

		return messList;
	}

	public void updateMessage(TextMessageDTO message) {

		LOGGER.info("Start method. Update mesage with parameters: "
				+ "[ subject: " + message.getSubject() + " mail from: "
				+ message.getMailFrom() + " mail to " + message.getMailTo()
				+ " ]");

		String sql = "UPDATE Message SET directory_id = ?, subject = ?, text = ?, mail_to = ? WHERE message_id = ?";

		Object[] params = new Object[] { message.getDirectoryId(),
				message.getSubject(), message.getText(), message.getMailTo(),
				message.getMessageId() };

		int affectedMessages = temp.update(sql, params);

		LOGGER.info("Message updated: " + (affectedMessages > 0 ? true : false)
				+ "[ subject: " + message.getSubject() + " mail from: "
				+ message.getMailFrom() + " mail to " + message.getMailTo()
				+ " ] was updated");

	}

	public void transferMessageToFolder(long messageId, int folderId) {

		LOGGER.info("Start method. Move mesage by id: " + messageId + " to directory " + " folderId");

		String sql = "UPDATE Message SET directory_id = ? WHERE message_id = ?";

		Object[] params = new Object[] { folderId, messageId };

		long affectedMessages = temp.update(sql, params);

		LOGGER.info("Message has been move: " + (affectedMessages > 0 ? true : false) + "id: " + messageId);

	}

	public long getMaxDate(String email) {

		LOGGER.info("Start method. Getting max date for all user messages by email: "
				+ email);

		String sql = "SELECT MAX(create_date) FROM Message AS mes, Folder, User AS us WHERE mes.user_id = us.user_id AND e_mail = ?";

		Timestamp result = temp.queryForObject(sql, new Object[] { email },
				Timestamp.class);

		if (result == null) {
			LOGGER.info("No messages for user with email: " + email);
			return -1;
		}

		LOGGER.info("Return max date for user messages with email: " + email);

		return result.getTime();
	}

}
