package com.revature.services;

import java.util.List;

import com.revature.models.BankAccount;
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
	
}
