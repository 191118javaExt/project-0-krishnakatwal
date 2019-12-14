package com.revature.repositories;

import java.util.List;

import com.revature.models.BankAccount;

public interface BankAccountDAO {

	List<BankAccount> getBankAccount();

	boolean addBankAccount(BankAccount b);

	boolean updateBankAccount(BankAccount b);

	boolean deleteBankAccount(BankAccount b);

}
