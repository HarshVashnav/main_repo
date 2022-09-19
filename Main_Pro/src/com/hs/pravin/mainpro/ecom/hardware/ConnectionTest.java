package com.hs.pravin.mainpro.ecom.hardware;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTest {

	

	public Connection getConnectionDetails() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diy_tools", "root","Harsh@565859");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;

	}
	
}
