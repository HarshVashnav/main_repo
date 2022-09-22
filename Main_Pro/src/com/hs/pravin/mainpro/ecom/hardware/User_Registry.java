package com.hs.pravin.mainpro.ecom.hardware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User_Registry implements Registry_History {
	Connection con;
	PreparedStatement ps;

	@Override
	public void history() {

		ConnectionTest ct = new ConnectionTest();
		con = ct.getConnectionDetails();

		try {
			ps = con.prepareStatement("select * from diy_tools.history ;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("User name          : " + rs.getString(1));
				System.out.println("Transaction Id     : " + rs.getString(2));
				System.out.println("Product name       : " + rs.getString(3));
				System.out.println("Quantity Purchased : " + rs.getString(4));
				System.out.println("Paid Amount        : " + rs.getString(5));
				System.out.println("=================================================");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void show_Registory() {
		ConnectionTest ct = new ConnectionTest();
		con = ct.getConnectionDetails();

		try {
			ps = con.prepareStatement("select * from diy_tools.registeredUser ;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Username          : " + rs.getString(1));
				System.out.println("City              : " + rs.getString(2));
				System.out.println("Email id          : " + rs.getString(3));

				System.out.println("=================================================");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
