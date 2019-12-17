package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.BankAccount;
import com.revature.util.ConnectionUtil;

public class BankAccountDAOImpl implements BankAccountDAO {

	private static Logger logger = Logger.getLogger(BankAccountDAOImpl.class);

	@Override
	public List<BankAccount> getBankAccount() {
		List<BankAccount> list = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM BankAccount;";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				int accountNumber = rs.getInt("accountnumber");
				double balance = rs.getDouble("balance");
				int userid = rs.getInt("userid");

				BankAccount b = new BankAccount(id, accountNumber, balance,userid);

				list.add(b);

			}

			rs.close();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all users", e);

		}

		return list;
	}

	@Override
	public boolean addBankAccount(BankAccount b) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO bankaccount "
					+ "( accountnumber, balance,userid) " +
					"VALUES (?, ?, ?, ?, ?,?,?);";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, b.getAccountNumber());
			stm.setDouble(2, b.getBalance());
			stm.setInt(3,b.getUserid());
	
			if(!stm.execute()) {
		       return false;
	}
		}
		
		catch(SQLException e) {
			logger.warn("Unable to add  account", e);
			return false;
		}
		
		return true;
	}

	@Override
	public boolean updateBankAccount(BankAccount b) {
		
		int id = b.getId();
		int accountnumber = b.getAccountNumber();
		double balance = b.getBalance();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE project0.bankaccountsSET account_number = ?, balance = ? WHERE user_id = ?;"; 
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, accountnumber);
			stm.setDouble(2, balance);
			stm.setInt(3, id);
			
			if(!stm.execute()) {
				return false;
			}
		}
		catch(SQLException e) {
			logger.warn("Unable to update the user's account", e);
			return false;
	}
		return true;
	}

	@Override
	public boolean deleteBankAccount(BankAccount b) {
		
		int id = b.getId();
		try (Connection conn = ConnectionUtil.getConnection()) {
						
					String sql = "DELETE FROM project0.accounts WHERE user_id = ?;"; 
					
					PreparedStatement stm = conn.prepareStatement(sql);
					stm.setInt(1, id);
					
					if(!stm.execute()) {
						return false;
					}
				} catch(SQLException e) {
					logger.warn("Unable to delete the account", e);
					return false;
				}
				
				return true;
	}

	@Override
	public boolean updateBalanceOfAccount(BankAccount a, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BankAccount getAccountIdBYPinNumber(int pinNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
