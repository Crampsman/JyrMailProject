package dk.telenor.javamail.dao;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import dk.telenor.javamail.dto.Role;
import dk.telenor.javamail.util.MySqlDataSource;

@Component
public class RoleDaoImpl implements RoleDao {
	
	private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class);
	
	private JdbcTemplate temp;

	@Autowired
	public void setDataSource(BasicDataSource dataSource) {
		this.temp = new JdbcTemplate(dataSource);
	}
	
	public void createRole(Role role) {
		
		LOGGER.info("Start method. Create role with name: " + role.getRole());
		
		String sql = "INSERT INTO Userrole (user_id, role) VALUES (?, ?)";
		
		Object[] params = new Object[]{role.getUserId(), role.getRole()};
		
		int affectedRoles = temp.update(sql, params);
		
		LOGGER.info("Role created: " + (affectedRoles > 0 ? true : false) + role.getRole());
		
	}

}
