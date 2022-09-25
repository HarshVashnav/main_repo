package com.hs.pravin.mainpro.ecom.hardware;

import java.util.Scanner;

public class UserAuthentication {

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

	static Scanner in = new Scanner(System.in);

	public static void welcome() {
		System.out.println("\t\t\t\t\t\t******* Welcome To Hardware Store *******");
	}

	public void createAcc() throws InterruptedException {
		// formatting();

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
}


