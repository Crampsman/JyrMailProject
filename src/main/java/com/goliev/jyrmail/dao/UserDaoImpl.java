package com.goliev.jyrmail.dao;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.goliev.jyrmail.dto.Role;
import com.goliev.jyrmail.dto.UserDTO;
import com.goliev.jyrmail.util.AesScramblerPassword;
import com.goliev.jyrmail.util.MySqlDataSource;

@Component
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private JdbcTemplate temp;

	@Autowired
	public void setDataSource(BasicDataSource dataSource) {
		this.temp = new JdbcTemplate(dataSource);
	}

    public boolean createUser(UserDTO user) throws Exception {

	LOGGER.info("Start method. Create user with email: " + user.getEmail());

	String existsSql = "SELECT EXISTS (SELECT * FROM User WHERE e_mail = ?)";
	String insertSql = "INSERT INTO User (user_name, e_mail, password) VALUES (?, ?, ?)";

	Object[] existsParams = new Object[] { user.getEmail() };
	Object[] insertParams = new Object[] { user.getName(), user.getEmail(),
		AesScramblerPassword.encryptPassword(user.getPassword()) };

	if (temp.queryForInt(existsSql, existsParams) == 1) {
	    LOGGER.info("User with email: " + user.getEmail()
		    + " already exists");
	    return false;
	} else {
	    LOGGER.info("User with email: " + user.getEmail() + " was created");
	    temp.update(insertSql, insertParams);

	    return true;
	}

    }

    public UserDTO getUserById(long id) {

	LOGGER.info("Start method. Getting user by id: " + id);

	String sql = "SELECT user_id, user_name, e_mail, password FROM User WHERE user_id = ?";

	Object[] params = new Object[] { id };

	UserDTO user = temp.queryForObject(sql, params,
		new RowMapper<UserDTO>() {

		    public UserDTO mapRow(ResultSet rs, int rowNum)
			    throws SQLException {

			UserDTO user = new UserDTO();

			user.setUserId(rs.getLong("user_id"));
			user.setEmail(rs.getString("e_mail"));
			user.setPassword(rs.getString("password"));
			user.setName(rs.getString("user_name"));

			return user;
		    }

		});

	LOGGER.info("User with id: " + id + " was retrieved");

	return user;
    }

    public UserDTO getUserByEmail(final String email) {

	LOGGER.info("Start method. Getting user by email: " + email);

	String sql = "SELECT us.user_id, role.user_id, role_id, user_name, password, role FROM User AS us, Userrole AS role WHERE e_mail = ? AND us.user_id = role.user_id";

	Object[] params = new Object[] { email };

	UserDTO user = null;

	try {

	    user = (UserDTO) temp.queryForObject(sql, params,
		    new RowMapper<UserDTO>() {

			public UserDTO mapRow(ResultSet rs, int rowNum)
				throws SQLException {

			    UserDTO user = new UserDTO();
			    List<Role> roles = new ArrayList<Role>();
			    Role role = new Role();

			    role.setRole(rs.getString("role"));
			    role.setUserId(rs.getLong("role.user_id"));
			    role.setRoleId(rs.getLong("role_id"));
			    roles.add(role);

			    user.setAuthorities(roles);
			    user.setEmail(email);
			    user.setUserId(rs.getLong("user_id"));
			    user.setPassword(rs.getString("password"));
			    user.setName(rs.getString("user_name"));

			    return user;
			}

		    });
	} catch (EmptyResultDataAccessException e) {
	    e.printStackTrace();
	    return null;
	}

	LOGGER.info("User with email: " + email + " was retrieved");

	return user;
    }

    public long getUserIdByEmail(String eMail) {

	LOGGER.info("Start method. Getting user id by email: " + eMail);

	String sql = "SELECT user_id FROM User WHERE e_mail = ?";

	Object[] params = new Object[] { eMail };

	long userId = temp.queryForLong(sql, params);

	LOGGER.info("User id with email: " + eMail + " was retrieved");

	return userId;
    }

    public List<UserDTO> getUsers() {

	LOGGER.info("Start method. Getting list users");

	String sql = "SELECT user_id, user_name, e_mail, password FROM User";

	List<UserDTO> users = new ArrayList<UserDTO>();
	List<Map<String, Object>> messageRows = temp.queryForList(sql);

	for (Map<String, Object> mesRow : messageRows) {

	    UserDTO user = new UserDTO();

	    user.setUserId(Long.parseLong(String.valueOf(mesRow.get("user_id"))));
	    user.setName(mesRow.get("user_name").toString());
	    user.setEmail(mesRow.get("e_mail").toString());
	    user.setPassword(mesRow.get("password").toString());

	    users.add(user);
	}

	LOGGER.info("Users list with size: " + users.size() + " was retrieved");

	return users;

    }

    public void updateUser(UserDTO user) {

	String sql = "UPDATE User SET user_name = ?, e_mail = ?, password = ? WHERE user_id = ?";

	Object[] params = new Object[] { user.getName(), user.getEmail(),
		user.getPassword(), user.getUserId() };

	temp.update(sql, params);

    }

    public void deleteUser(long id) {

	LOGGER.info("Start method. Delete user by id: " + id);

	String sql = "DELETE FROM User WHERE user_id = ?";

	Object[] params = new Object[] { id };

	int affectedUsers = temp.update(sql, params);

	LOGGER.info("Users affected by delete query: "
		+ (affectedUsers > 0 ? true : false));
    }

}
