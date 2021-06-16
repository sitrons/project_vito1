package org.admin.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTest {

	@Test
	public void testConnection() throws Exception {
		 
		Class.forName("com.mysql.cj.jdbc.Driver");
		log.info("jdbc connected.............................");
		
		String url = "jdbc:mysql://localhost:3306/dclass?serverTimezone=UTC";
		String username = "springuser";
		String password = "springuser";
		
		Connection con = DriverManager.getConnection(url, username, password);
		
		log.info(con);
		
		con.close();
	}
	
} //end class
