package com.hs.pravin.mainpro.ecom.hardware;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTest {

	Connection connection ;

	public Connection getConnectionDetails() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diy_tools", "root", "S@HeL786");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;

	}
	
}
