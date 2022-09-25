package com.hs.pravin.mainpro.ecom.hardware;

public class Test {

	public static void main(String[] args) {
		
		UserAuth_DB uad = new UserAuth_DB();
		uad.createTable();
		uad.createProductDatabase();
		uad.authentication();

	}

}
