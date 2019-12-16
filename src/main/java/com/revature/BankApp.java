package com.revature;

import java.util.Scanner;

import com.revature.models.User;
import com.revature.services.BankAccountService;
import com.revature.services.UserService;

public class BankApp {

	private static UserService us = new UserService();
	private static BankAccountService bs = new BankAccountService();

	private static Scanner sc = new Scanner(System.in);

	public static void start() {

		findUser();
	}

	private static void findUser() {
		boolean find = true;
		User u = null;
		while (find) {
			options();
			int input = Integer.parseInt(sc.nextLine()); 
			switch (input) {
			case 1:
				u = forNewUser();
				us.addUser(u);
				break;
			case 2:
				User u1 = findUserInDB();
				if (checkUserInDB(u1)) {

					logIn(u1);
				}
				find = false;
				break;
			case 3:
				System.out.println("exit");
				System.exit(0);
			default:
				System.out.println(" Please try again");
				break;

			}

		}

	}

	private static void logIn(User u1) {

		System.out.println("====================");
		System.out.println("Enter your username");
		System.out.println("enter your password ");
		System.out.println("======================");

		int option = Integer.parseInt(sc.nextLine()); ;
		switch (option) {
		case 1:
			System.out.println("");
			System.exit(0);
			break;
		case 2:
			deposit(u1);
			break;
		case 3:
			withdraw(u1);
			break;
		case 4:
			transfer(u1);
			break;
		default:
			System.out.println("\"Invalid option!! please enter again\"");
			break;
		}
	}

	private static void transfer(User u1) {
		// TODO Auto-generated method stub
		

	}

	private static void withdraw(User u1) {
		// TODO Auto-generated method stub

	}

	private static void deposit(User u1) {
		// TODO Auto-generated method stub
	}

	private static boolean checkUserInDB(User et) {
		boolean flag = false;

		if (et != null) {

			flag = true;

		} else {
			System.out.println("no user exists. Please try again");
			flag = false;
		}
		return flag;
	}

 private static User findUserInDB() {
	    String fname;
		String password;
		User u;
		do {
			System.out.println("Enter your first name: ");
			 fname = sc.nextLine();
			System.out.println("Enter your password: ");
			 password = sc.nextLine();
			System.out.println("Checking user in DataBase");
			u = us.getUserByFnameAndPassword(fname, password);
			
		}while(u == null);
		return u;
	}
	
	private static User forNewUser() {
		System.out.println(" Your First Name: ");
		String firstname = sc.nextLine();

		System.out.println(" YourLast Name: ");
		String lastname = sc.nextLine();

		System.out.println("Password:");
		String password1 = sc.nextLine();
		System.out.println("");

		System.out.println("Re-enter Password:");
		String password2 = sc.nextLine();
		
		
		System.out.println("Are you an Employee? Please Enter Yes or No");
		String input = sc.nextLine();
		boolean isemployee = false;
		if(input.equalsIgnoreCase("Yes")){
			isemployee = true;
		} 
		System.out.println("Are you an Admin? Please Enter Yes or No");
		String input1 = sc.nextLine();
		boolean isadmin = false;
		if(input1.equalsIgnoreCase("Yes")){
			isadmin= true;
		}
		
		return new User(0,firstname,lastname,password1, isemployee, isadmin);
	}

	private static void options() {
		System.out.println("Options: ");
		System.out.println("Please Enter 1 for New User");
		System.out.println("Enter 2 for the existing User");
		System.out.println("And Enter 3 for exit");
	}
}
