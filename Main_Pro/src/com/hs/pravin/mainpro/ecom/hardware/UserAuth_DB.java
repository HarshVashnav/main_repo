package com.hs.pravin.mainpro.ecom.hardware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class UserAuth_DB implements User_Product_DB {
	Connection con = null;
	PreparedStatement ps;
	String firstName;
	String lastName;
	String city;
	private String mobileNumber;
	String emailId;
	static int count = 0;
	private String newPassWord;
	private String userName;
	private String ansOfSecurityQue;
	private String securityQue;

	
	Show_Pro sp = new Show_Pro();

	static Scanner in = new Scanner(System.in);

	@Override
	public void userVerification() throws InterruptedException {
		System.out.println("\t\t\t\t\t\t******* Welcome To Hardware Store *******");

		System.out.println("\nFirst name ---> ");
		firstName = in.next();
		System.out.println("Last name ---> ");
		lastName = in.next();
		System.out.println("City ---> ");
		city = in.next();
		System.out.println("Mobile number ---> ");
		mobileNumber = in.next();
		System.out.println("Email Id ---> ");
		emailId = in.next();
		setSecurity();
		signUp();
		formatting();

		System.out.println("\nYour account has been created successfully.");
		formatting();
	}

	public void setSecurity() {
		in.nextLine();
		System.out.println("Enter your security question: ");

		securityQue = in.nextLine();
		System.out.println("Enter your answer: ");
		ansOfSecurityQue = in.nextLine();
		formatting();
	}

	public void signUp() throws InterruptedException {

		System.out.println("Enter user name ---> ");
		userName = in.next();
		setPassword();
	}

	public static void formatting() {
		System.out.println(
				"===========================================================================================================================================");
	}

	public static void formatting1() {
		System.out.println(
				"*******************************************************************************************************************************************");
	}

	public void setPassword() throws InterruptedException {

		if (count < 3) {
			System.out.println("\nEnter new password");
			String passWord1 = in.next();
			System.out.println("Confirm your password");
			String passWord2 = in.next();
			if (passWord1.equals(passWord2)) {
				newPassWord = passWord1;

			} else {
				System.out.println("\nIncorrect password. Please try again.");
				count++;
				setPassword();
			}
		} else {
			System.out.println("Too many failed attempts. Please try after 10 sec");
			Thread.sleep(10000);
			count = 0;
			setPassword();
		}
	}

	public boolean login() {
		System.out.println("Username: ");
		String uname = in.next();
		System.out.println("Password: ");
		String pass = in.next();
		if (uname.equals(userName) && pass.equals(newPassWord)) {
			return true;
		} else {
			return false;
		}

	}

	public void authentication() {

		int flag = 0;
		UserAuth_DB cust = new UserAuth_DB();

		char opt = 'n';
		while (opt == 'n') {

			System.out.println("\nOptions:\n1.Signup \n2.Login");
			formatting();
			System.out.println("\nEnter your option: ");
			char c = in.next().charAt(0);
			formatting1();
			if (c == '1') {
				try {
					cust.userVerification();
					flag = 1;
				} catch (Exception e) {

					System.out.println("Something went wrong. Please try after sometime.");
				}
			} else {
				if (cust.login()) {

					formatting1();
					System.out.println("Press 1 to Show Product List");
					formatting1();
					char choice = in.next().charAt(0);
					switch (choice) {
					case '1':

						UserAuth_DB uad1 = new UserAuth_DB();

						sp.showProducts();

						Proceed_to_ConfirmOrder ptc = new Proceed_to_ConfirmOrder();
						ptc.addedToCart();
						ptc.checkOutDetails();
						ptc.purchase();
						User_Registry usr = new User_Registry();
						usr.history();
						uad1.userReg();
						uad1.insertRegisteredUser();
						usr.show_Registory();
						break;

					}
				}
			}
		}

	}

	@Override
	public void createProductDatabase() {
		try {
			ConnectionTest test = new ConnectionTest();
			con = test.getConnectionDetails();
			Statement stmt = con.createStatement();
			String Insert_SQL = "insert into hardware(id,name,description,price,quantity)values('1','hammer','Claw Hammer','400','1'),('2','nut','Square Nuts','1','1500'),('3','screw','Flat Head','1','1500'),('4','pliers','Needle-nose Pliers','300','10'),('5','lock','Door lock','300','10'),('6','hinge','Butterfly Hinge','100','5'),('7','pipes','Pvc Pipes','80','100'),('8','rope','Asbestose rope','20','100'),('9','ladder','Telescopic ladder','8000','5'),('10','toolbox','Carpenter tool box','3000','3');";

			int numberOfRows = stmt.executeUpdate(Insert_SQL);
			System.out.println("Records Updated " + numberOfRows);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createTable() {
		try {
			String sql = "create table hardware (id int ,name varchar(255),description 	varchar	(255),price int,quantity int);";
			ConnectionTest test = new ConnectionTest();
			con = test.getConnectionDetails();
			Statement statement = con.createStatement();
			statement.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void userReg() {

		try {

			String sql = "create table diy_tools.registeredUser (Username varchar(255) ,City varchar(255) ,Emailid varchar(255));";
			ConnectionTest test = new ConnectionTest();
			con = test.getConnectionDetails();
			Statement statement = con.createStatement();
			statement.execute(sql);

			

		} catch (Exception e) {
			System.out.println(" ");
		}
	}
	
	public void insertRegisteredUser() {
		try {
			ps = con.prepareStatement("insert into registeredUser(Username,City ,Emailid)values(?,?,?);");

			ps.setString(1, "firstName");
			ps.setString(2, "city");
			ps.setString(3, "emailId");

			int iny = ps.executeUpdate();
			System.out.println("Records updated " + iny);
		} catch (Exception e) {
			
		}
		
	}

}
