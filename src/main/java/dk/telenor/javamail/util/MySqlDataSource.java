package dk.telenor.javamail.util;

import org.apache.commons.dbcp.BasicDataSource;

public class MySqlDataSource {

	public static BasicDataSource getDataSource() {
		
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/jyr");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		
		return dataSource;
	}
}
