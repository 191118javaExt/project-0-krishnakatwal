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
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class BankAccountDAOImpl implements BankAccountDAO {

	private static Logger logger = Logger.getLogger(BankAccountDAOImpl.class);

	@Override
	public List<BankAccount> getBankAccount() {
		List<BankAccount> list = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM bankaccount;";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int account_id = rs.getInt("account_id");
				int account_number = rs.getInt("account_number");
				double balance = rs.getDouble("balance");
				int user_id = rs.getInt("user_id");
				int status = rs.getInt("status");
				int pin = rs.getInt("pin");

				BankAccount b = new BankAccount(account_id, account_number, balance,user_id, status, pin);

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
			String sql = "INSERT INTO project0.BankAccount"
					+ "( account_number, balance,user_id,status,pin) " +
					"VALUES (?, ?, ?, ?, ?);";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, b.getAccountNumber());
			stm.setDouble(2, b.getBalance());
			stm.setInt(3,b.getUserid());
			stm.setInt(4,b.getStatus());
	        stm.setInt(5,b.getPin());
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
		
		int accountid = b.getId();
		int accountnumber = b.getAccountNumber();
		double balance = b.getBalance();
		int userid = b.getUserid();
		int status = b.getStatus();
		int pin = b.getPin();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE project0.BankAccount SET account_number = ?, balance = ?,status = ?, pin = ? WHERE account_id = ?;"; 
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1,accountid);
			stm.setInt(2, accountnumber);
			stm.setDouble(3, balance);
			stm.setInt(4, userid);
			stm.setInt(4, status);
			stm.setInt(5, pin);
			
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
						
					String sql = "DELETE FROM project0.BankAccount WHERE account_id = ?;"; 
					
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
		int id = a.getId();
		int number = a.getAccountNumber();
		double balance = a.getBalance() + amount;
		int user_id = a.getUserid();
		int pin = a.getPin();
		int status = a.getStatus();
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "UPDATE project0.BankAccount SET account_id =?, account_number = ?, balance = ?, user_id = ?, status = ?,pin = ? WHERE account_id = ?;"; 
					
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1,id);
			stm.setInt(2, number);
			stm.setDouble(3, balance);
			stm.setInt(4, user_id);
			stm.setInt(5, status);
			stm.setInt(6, pin);
			stm.setInt(7, id);
			if(!stm.execute()) {
				return false;
			}
		} catch(SQLException e) {
			logger.warn("Unable to update the account balance", e);
			return false;
		}
		return true;
	}

	@Override
	public BankAccount getAccountIdBYPin(int pinnumber) {
		// TODO Auto-generated method stub
		return null;
	}
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//	
	public List<BankAccount> getAccountsOfUser(User u){
		List<BankAccount> list = new ArrayList<>();
		int id = u.getId(); 
		try (Connection conn = ConnectionUtil.getConnection()) {
			//System.out.println("I am here");
			String sql = "SELECT * FROM project0.BankAccount where user_id = ?;";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int account_id = rs.getInt("account_id");
				int account_number = rs.getInt("account_number");
				double balance = rs.getDouble("balance");
				int user_id = rs.getInt("user_id");
				int status = rs.getInt("status");
				int pin = rs.getInt("pin");

				BankAccount b = new BankAccount(account_id, account_number, balance,user_id, status, pin);

				list.add(b);

			}

			rs.close();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all users", e);

		}

		return list;
	}

	@Override
	public List<BankAccount> getAllAccountofUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount getAccountById(int id) {
		BankAccount b = null;
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM project0.BankAccount where account_id = ?;";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int account_id = rs.getInt("account_id");
				int account_number = rs.getInt("account_number");
				double balance = rs.getDouble("balance");
				int user_id = rs.getInt("user_id");
				int status = rs.getInt("status");
				int pin = rs.getInt("pin");

				 b = new BankAccount(account_id, account_number, balance,user_id, status, pin);

			}

			rs.close();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve account", e);

		}

		return b;
	}

}
