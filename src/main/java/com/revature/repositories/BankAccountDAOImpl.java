package com.revature.repositories;

import java.sql.Connection;
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

				BankAccount b = new BankAccount(id, accountNumber, balance);

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

		return false;
	}

	@Override
	public boolean updateBankAccount(BankAccount b) {

		return false;
	}

	@Override
	public boolean deleteBankAccount(BankAccount b) {

		return false;
	}

}
