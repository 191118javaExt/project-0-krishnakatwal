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
				User nw = null;
				while(find) {	 
					options(); 
					int input = '\0';
					switch(input) {
					case 1:
						nw = forNewUser();
						us.addUser(nw);
						break;
					case 2:
						User et = findUserInDB();
						if(checkUserInDB(et)) {
							
							logIn(et);
						}
						find = false;
						break;
					case 3:
						System.out.println("Thanking you for using app");
						System.exit(0);
					default:
						System.out.println("No such option exists. Please try again");
						break;
						
					}
					
					
				}
				
			}

		

		private static void logIn(User et) {
			
			System.out.println("====================");
			System.out.println("Enter your username");
			System.out.println("enter your password ");
			System.out.println("======================");
			 
			int option = '\0';
			switch(option) {
			case 1:
				System.out.println("Thank you for visiting.");
				System.exit(0);
				break;
			case 2:
				deposit(et);
				break;
			case 3:
				withdraw(et);
				break;
			case 4: 
				transfer(et);
				break;
			default:
				System.out.println("\"Invalid option!! please enter again\"");
				break;
			}
		}
	
			
		private static void transfer(User et) {
			// TODO Auto-generated method stub
			
		}

		private static void withdraw(User et) {
			// TODO Auto-generated method stub
			
		}

		private static void deposit(User et) {
			// TODO Auto-generated method stub
			
		}


		

		private static boolean checkUserInDB(User et) {
			// TODO Auto-generated method stub
			return false;
		}

		private static User findUserInDB() {
			// TODO Auto-generated method stub
			return null;
		}

		private static User forNewUser() {
			// TODO Auto-generated method stub
			System.out.println(" Your First Name: ");
			String firstname = sc.nextLine();
			
			System.out.println(" YourLast Name: ");
			String lastname = sc.nextLine();
			
			System.out.println("Password:");
			String password1 = sc.nextLine();
			System.out.println("");
			
			System.out.println("Re-enter Password:");
			String password2 = sc.nextLine();
			return null;
		}

		private static void options() {
			System.out.println("Options: ");
			System.out.println("Please Enter 1 for New User");
			System.out.println("Enter 2 for the existing User");
			System.out.println("And Enter 3 for exit");
			
		}
}

		
