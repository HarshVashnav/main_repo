package com.hs.pravin.mainpro.ecom.hardware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Proceed_to_ConfirmOrder implements ConfirmOrder {
	
	Connection con;
    PreparedStatement ps;

	Scanner in = new Scanner(System.in);
	int pId;
	int quantity;
	int price;
	String choice;
	int paidamount;
	String pname1;
	@Override
	public void addedToCart() {
		char choice = 'y';
		while (choice == 'y') {
			System.out.println("Please Select product id to buy");
			pId = in.nextInt();
			System.out.println("Please Select name of product");
			pname1 = in.next();
			System.out.println("Please Select quantity of product");
			quantity = in.nextInt();
			System.out.println("Do you want buy anythig else y/n ?");
			choice = in.next().charAt(0);
		}
	}

	@Override
	public void checkOutDetails() {

		System.out.println("Your order bifurcation is :");
		System.out.println("Product Name:" + pId);
		System.out.println("Quantity" + quantity);

		try {
			ConnectionTest ct = new ConnectionTest();
			con = ct.getConnectionDetails();
			ps = con.prepareStatement("select price from hardware where id=" + pId + ";");
			ResultSet executeQuery = ps.executeQuery();
			executeQuery.next();
			price = executeQuery.getInt(1);

			if (executeQuery.next()) {
				price = executeQuery.getInt(1);
			}
		  paidamount = quantity * price;
			System.out.println("Amount to be paid for " + pId + " x " + quantity + " Nos. " + "=" + (quantity * price));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void purchase() {

		String txn = "ABC0000";
		Random obj = new Random();
		txn += obj.nextInt(1000);
		System.out.println("txn :" + txn);
		System.out.println("Amount to be paid for" + (quantity * price));
		System.out.println("Please Re-Enter your UserName");
		String username1 = in.next();
		
		
		try {
			String sql = "CREATE table UserReg (username varchar(255), txnid varchar(255),namem varchar(255), quantity int, paidamount int) ";
			ConnectionTest test = new ConnectionTest();
			con = test.getConnectionDetails();
			Statement statement = con.createStatement();
			//statement.execute(sql);
			
			ps = con.prepareStatement("insert into userreg (username,txnid,namem,quantity,paidamount)values(?,?,?,?,?);");

			{
			
			ps.setString(1,username1);
			ps.setString(2,txn);
			ps.setString(3,pname1);
			ps.setInt(4,quantity);
			ps.setInt(5,paidamount);
			}
			
			int iny =ps.executeUpdate();
			System.out.println("Records updated "+ iny);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Proceed_to_ConfirmOrder obj = new Proceed_to_ConfirmOrder();
		
		obj.addedToCart();
		obj.checkOutDetails();
		 obj.purchase();

	}

}
