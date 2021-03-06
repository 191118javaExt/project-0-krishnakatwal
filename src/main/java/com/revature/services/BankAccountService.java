package com.revature.services;

import java.util.List;

import com.revature.models.BankAccount;
import com.revature.models.User;
import com.revature.repositories.BankAccountDAO;
import com.revature.repositories.BankAccountDAOImpl;

public class BankAccountService {
	
	BankAccountDAO repository = new BankAccountDAOImpl();

	List<BankAccount> getBankAccount(){
		return repository.getBankAccount();
	}
	
	boolean addBankAccount(BankAccount b) {
		return repository.addBankAccount(b);
	}

	boolean updateBankAccount(BankAccount b) {
		return repository.updateBankAccount(b);
	}

	boolean deleteBankAccount(BankAccount b) {
		return repository.deleteBankAccount(b);
	}

	public boolean updateBalanceOfAccount(BankAccount a, double amount) {
		return repository.updateBalanceOfAccount(a, amount);
		
	}

	public BankAccount getAccountBYPinNumber(int pinNumber) {
		return repository.getAccountIdBYPin(pinNumber);
	}

	public List<BankAccount> getAllAccountsOfUser(User u) {
		
		return repository.getAllAccountofUser(u);
	}


	public BankAccount getAccountById(int id) {
		return repository.getAccountById( id);
	}

	public List<BankAccount> getAccountsOfUser(User u) {
		return repository.getAccountsOfUser(u);
	}
	
}
