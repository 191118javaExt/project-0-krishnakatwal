package com.revature.models;

public class BankAccount {

	private int id;
	private int accountNumber;
	private double balance;

	public BankAccount() {
		super();

	}

	public BankAccount(int id, int accountNumber, double balance) {
		this.id = id;
		this.accountNumber = accountNumber;
		this.balance = balance;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		BankAccount other = (BankAccount) obj;
		if (accountNumber != other.accountNumber)
			return false;

		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;

		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}

}