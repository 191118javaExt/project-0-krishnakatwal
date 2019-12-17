package com.revature;

import java.util.Scanner;

import com.revature.models.BankAccount;
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

		int option = Integer.parseInt(sc.nextLine());
		;
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

//----------------------------------------------------------------------------------------------------------//
	private static void transfer(User u1) {
		// TODO Auto-generated method stub
		BankAccount a = us.getUserBankAccount(u1);
		if (a.getStatus() == 1) {
			System.out.println(
					"Your Account was canceled.Contact Admin or Employee for more Information.");
		} else if (a.getStatus() == 2) {
			System.out.println(
					"Your Account is still in pending.Contact Admin or Employee for more Information.");
		} else {

			System.out.println("transfer money you want?");
			double amount = ensureDoubleInput();
			if (amount <= 0) {
				System.out.println("You can't transfer negative or Zero amount to transfer");
			} else {

				System.out.println("Enter the pin number of the account that you would like to transfer.");
				int pinNumber = ensureIntegerInput();
				BankAccount anotherAccount = bs.getAccountBYPinNumber(pinNumber);

				if (a.getBalance() < amount) {
					System.out.println("You can't transfer the amount more than your balance.");
				} else if (anotherAccount.getStatus() < 3) {
					System.out.println("The account you want to transfer is not yet approved. So you can't transfer.");
				} else {
					bs.updateBalanceOfAccount(a, (-1 * amount));
					bs.updateBalanceOfAccount(anotherAccount, amount);
				}
				BankAccount a1 = us.getUserBankAccount(u1);
				System.out.println("Congratulation you successfully transfered $" + amount
						+ " to another account with account number " + anotherAccount.getAccountNumber());
				System.out.println("And your current balance is $" + a1.getBalance());
			}
		}

	}

//------------------------------------------------------------------------------------------
	private static int ensureIntegerInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	// -----------------------------------------------------------------------------------------------------------//
	private static void withdraw(User u1) {
		// TODO Auto-generated method stub
		BankAccount a = us.getUserBankAccount(u1);
		if (a.getStatus() == 1) {
			System.out.println(
					"Your Account was canceled. You can't withdraw. Contact Admin or Employee for more Information.");
		} else if (a.getStatus() == 2) {
			System.out.println(
					"Your Account is still in pending. You can't withdraw. Contact Admin or Employee for more Information.");
		} else {

			System.out.println("How much do you want to withdraw?");
			double amount = ensureDoubleInput();

			if (a.getBalance() < amount) {
				System.out.println("You can't withdraw the amount more than your balance.");
			} else {
				bs.updateBalanceOfAccount(a, (-1 * amount));
			}
			BankAccount a1 = us.getUserBankAccount(u1);
			System.out.println("Congratulation you successfully withdrew $" + amount + " and your current balance is $"
					+ a1.getBalance());
		}

	}

//-------------------------------------------------------------------------------------------------------------------------------//
	private static void deposit(User u1) {
		BankAccount a = us.getUserBankAccount(u1);
		if (a.getStatus() == 1) {
			System.out.println(
					"Your Account was canceled. You can't deposit. Contact Admin or Employee for more Information.");
		} else if (a.getStatus() == 2) {
			System.out.println(
					"Your Account is still in pending. You can't deposit. Contact Admin or Employee for more Information.");
		} else {

			System.out.println("How much do you want to deposit?");
			double amount = ensureDoubleInput();
			if (amount <= 0) {
				System.out.println("You can't deposit negative amount or Zero amount.");
			} else {

				bs.updateBalanceOfAccount(a, amount);
				BankAccount a1 = us.getUserBankAccount(u1);
				System.out.println("Congratulation you successfully deposited $" + amount
						+ " and your current balance is $" + a1.getBalance());
			}

		}
	}

//========================================================================================================//
	private static double ensureDoubleInput() {
		// TODO Auto-generated method stub
		String amt = sc.nextLine();
		double amount = 0.0;
		try {
			amount = Double.parseDouble(amt);
		} catch (NumberFormatException e) {
			System.out.println("Couldn't convert the transfer amount");
			e.printStackTrace();
		}
		return amount;

	}

//=========================================================================================================//
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

//=======================================================================================================//
	private static User findUserInDB() {
		String fname;
		String password;
		User u;
		do {
			System.out.println("Enter your first name: ");
			fname = sc.nextLine();
			System.out.println("Enter your password: ");
			password = sc.nextLine();

			u = us.getUserByFnameAndPassword(fname, password);

		} while (u == null);
		return u;
	}

	// ======================================================================================//
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
		if (input.equalsIgnoreCase("Yes")) {
			isemployee = true;
		}
		System.out.println("Are you an Admin? Please Enter Yes or No");
		String input1 = sc.nextLine();
		boolean isadmin = false;
		if (input1.equalsIgnoreCase("Yes")) {
			isadmin = true;
		}

		return new User(0, firstname, lastname, password1, isemployee, isadmin);
	}

//===================================================================================\\
	private static void options() {
		System.out.println("Options: ");
		System.out.println("====Please Enter 1 for New User:====");
		System.out.println("===Enter 2 for the existing User:==");
		System.out.println("==Enter 3 for exit:====");
	}

//--------------------------------------------------------------------------------------------------------//	
	private static void giveOptionsToModifyUsers(User u) {
		System.out.println("Enter user id to modify the user information.");
		int choice = ensureIntegerInput();
		User user1 = us.getUserById(choice);
		System.out.println(user1);
		boolean flag = true;
		while (flag) {

			System.out.println("What do you want to change?");
			System.out.println();
			System.out.println("Enter 0 to go back");
			System.out.println("Enter 1 to change user First Name.");
			System.out.println("Enter 2 to change user Last Name.");
			System.out.println("Enter 3 to change user Password.");
			System.out.println("Enter 4 to change user Employee Status.");

			int choice1 = ensureIntegerInput();
			switch (choice1) {
			case 0:
				System.out.println("Thank you for checking out.");
				flag = false;
				break;
			case 1:
				System.out.println("Please Enter the user First Name");
				String fname = sc.nextLine();
				user1.setFirst_name(fname);
				us.updateUser(user1);
				System.out.println("User's First Name is changed.");
				break;
			case 2:
				System.out.println("Please Enter the user Last Name");
				String lname = sc.nextLine();
				user1.setFirst_name(lname);
				us.updateUser(user1);
				System.out.println("User's Last Name is changed.");
				break;
			case 3:
				System.out.println("Please Enter the user Password");
				String pass = sc.nextLine();
				user1.setPassword(pass);
				us.updateUser(user1);
				System.out.println("User's Password is changed.");
				break;
			case 4:
				System.out.println("Please Enter the user's Employee Statue T for \"True\" or F for \"False\"");
				boolean status = false;
				String estatus = sc.nextLine();
				if (estatus.equalsIgnoreCase("T")) {
					status = true;
				}
				user1.setEmployee(status);
				us.updateUser(user1);
				System.out.println("User's Employee Status is changed.");
				break;
			default:
				System.out.println("There no option for your input, Please try again.");
				break;
			}
		}
	}
}