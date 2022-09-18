package com.hs.pravin.mainpro.ecom.hardware;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class UserAuth_DB implements User_Product_DB {
	Connection con = null;
	Scanner in = new Scanner(System.in);

	@Override
	public void userVerification() {
		System.out.println("Enter first name");
		String fname = in.next();
		System.out.println("Enter last name");
		String lname = in.next();
		System.out.println("Enter user name");
		String uname = in.next();
		System.out.println("set password ");
		String password = in.next();
		if ((uname == "harsh" && password == "11111") || (uname == "sohel" && password == "22222")
				|| (uname == "sushil" && password == "33333") || (uname == "bhushan" && password == "44444")) {
			System.out.println("Hello");
		}
	}

	@Override
	public void createProductDatabase() {
		try {
			ConnectionTest test = new ConnectionTest();
			con = test.getConnectionDetails();
			Statement stmt = con.createStatement();
			String Insert_SQL = "insert into hardware(id,name,description,price,quantity)values('1','Hammer','Claw Hammer','400','1'),('2','Nut','Square Nuts','1','1500'),('3','Screw','Flat Head','1','1500'),('4','Pliers','Needle-nose Pliers','300','10'),('5','Pliers','Needle-nose Pliers','300','10'),('6','Hinge','Butterfly Hinge','100','5'),('7','Pipes','Pvc Pipes','80','100'),('8','Rope','Asbestose rope','20','100'),('9','Ladder','Telescopic ladder','8000','5'),('10','Tool box','Carpenter tool box','3000','3')";

			int numberOfRows = stmt.executeUpdate(Insert_SQL);
			System.out.println("Records Updated " + numberOfRows);
			// con.close();
			// ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createTable() {
		try {
			String sql = "create table hardware (id int ,name varchar(255),description 			varchar	(255),price varchar(255),quantity varchar(255))";
			ConnectionTest test = new ConnectionTest();
			con = test.getConnectionDetails();
			Statement statement = con.createStatement();
			statement.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		UserAuth_DB auth_DB = new UserAuth_DB();
		auth_DB.userVerification();
		auth_DB.createTable();
		auth_DB.createProductDatabase();

	}

}
