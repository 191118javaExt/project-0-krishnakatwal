package com.revature.repositories;

import java.util.List;

import com.revature.models.BankAccount;
import com.revature.models.User;

public interface BankAccountDAO {

	List<BankAccount> getBankAccount();

	boolean addBankAccount(BankAccount b);

	boolean updateBankAccount(BankAccount b);

	boolean deleteBankAccount(BankAccount b);

	public boolean updateBalanceOfAccount(BankAccount a, double amount);

	public BankAccount getAccountIdBYPin(int pin);

	public List<BankAccount> getAllAccountofUser(User u);

	BankAccount getAccountById(int id);

	List<BankAccount> getAccountsOfUser(User u);

}