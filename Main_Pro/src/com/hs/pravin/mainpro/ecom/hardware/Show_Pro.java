package com.hs.pravin.mainpro.ecom.hardware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Show_Pro implements ShowPro_Kart {

	Connection con = null;
	PreparedStatement ps = null;

	@Override
	public void showProducts() {
	try {
	ConnectionTest ct = new ConnectionTest();
	con = ct.getConnectionDetails();
	PreparedStatement ps = con.prepareStatement("SELECT * FROM diy_tools.hardware order by name asc");
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
	System.out.println("Name of product        : " + rs.getString(2));
	System.out.println("Description of product : " + rs.getString(3));
	System.out.println("Price of product (INR) : " + rs.getString(4));
	System.out.println("===========================================================================");
	
	}
	} catch (SQLException e) {

	e.printStackTrace();
	}

	}
	
	

//	public static void main(String[] args) {
//	Show_Pro sp = new Show_Pro();
//	UserAuth_DB  db=new UserAuth_DB();
//	

	//sp.showProducts();
	}

	

