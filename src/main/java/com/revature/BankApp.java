package com.revature;

import java.util.List;
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
				u = forNewUser();// apply for the NewAccount//
				
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

	private static User forNewUser() {
		System.out.println(" =====Your First Name:===== ");
		System.out.println("\n");
		String firstname = sc.nextLine();

		System.out.println(" ====YourLast Name:===== ");
		System.out.println("\n");
		String lastname = sc.nextLine();

		System.out.println("===Password:====");
		System.out.println("\n");
		String password1 = sc.nextLine();
		System.out.println("");

		System.out.println("====Re-enter Password:====");
		System.out.println("\n");
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

	
	private static void logIn(User u1) {

		System.out.println("Enter 1 to Exist");
		System.out.println("Enter 2 to Deposit");
		System.out.println("Enter 3 to Withdraw");
		System.out.println("Enter 4 to Transfer ");
		System.out.println();

		int option = Integer.parseInt(sc.nextLine());//convert String to Integer
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

	private static void transfer(User u1) {
		displayAccounts(u1);
		System.out.println("Enter Account id to which you want to deposit.");
		int id = Integer.parseInt(sc.nextLine());
		BankAccount a = bs.getAccountById(id);
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
				int pinNumber = ensureIntegerInput(null);
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
				
			}
		}

	}


	private static int ensureIntegerInput(String input) {
		System.out.println("Enter "+ input);
		int choice = 0;
//		Reads a String value from the user//
		String ch = sc.nextLine();
		try {
			
			choice = Integer.parseInt(ch);
		}catch(NumberFormatException e) {
			System.out.println("Error in changing input to Integer");
			e.printStackTrace();
			
		}
		
		return choice;
	}

	private static void withdraw(User u1) {
		displayAccounts(u1);
		System.out.println("Enter Account id to which you want to deposit.");
		int id = Integer.parseInt(sc.nextLine());
		BankAccount a = bs.getAccountById(id);
		System.out.println();
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
			
			System.out.println("Congratulation you successfully withdrew $" + amount );
		}

	}


	private static void deposit(User u1) {
		displayAccounts(u1);
		System.out.println("Enter Account id to which you want to deposit.");
		int id = Integer.parseInt(sc.nextLine());
		BankAccount a = bs.getAccountById(id);
		
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
				System.out.println("Congratulation you successfully deposited $" + amount
						);
			}

		}
	}

	private static double ensureDoubleInput() {
		// TODO Auto-generated method stub
		String amt = sc.nextLine();
		double amount = 0.0;
		try {
			amount = Double.parseDouble(amt);//returns the double value 
		} catch (NumberFormatException e) {
			System.out.println("Couldn't convert the transfer amount");
			e.printStackTrace();//trace the exception.help the progarmmer to trace the error.
		}
		return amount;

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
			//System.out.println("\n");
			fname = sc.nextLine();
			System.out.println("Enter your password: ");
			password = sc.nextLine();

			u = us.getUserByFnameAndPassword(fname, password);

		} while (u == null);
		return u;
	}


	private static void options() {
		System.out.println("Options: ");
		System.out.println("====Please Enter 1 for New User:====");
		//System.out.println("\n");
		System.out.println("====Enter 2 for the Existing User:==");
		//System.out.println("\n");
		System.out.println("====Enter 3 for Exit:===============");
		//System.out.println("\n");
	}


	  public static boolean displayAccounts(User u) {
		List<BankAccount> allAccountsOfUser = bs.getAccountsOfUser(u);
		if(allAccountsOfUser.size()<=0) {
			System.out.println("You don't have any accounts please OPEN ONE.");
			return false;
		}else {
			System.out.println("Here are the list of your accounts");
			System.out.println("=============================================================================================");
			for(BankAccount a : allAccountsOfUser) {
				System.out.println(a);
			}
			System.out.println("============================================================================================");
			System.out.println("Please select the Account Id of the account you like." );
			return true;
		}
	}
}