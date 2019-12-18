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

public class UserDAOImpl implements UserDAO {

	private static Logger logger = Logger.getLogger(UserDAOImpl.class);

	@Override
	public List<User> getUsers() {
		List<User> list = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM User;";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("user_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String password = rs.getString("password");
				boolean isEmployee = rs.getBoolean("isEmployee");
				boolean isAdmin = rs.getBoolean("isAdmin");

				User u = new User(id, first_name, last_name, password, isEmployee, isAdmin);

				list.add(u);
			}

			rs.close();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all users", e);
		}

		return list;
	}

	@Override
	public boolean addUser(User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO project0.users (first_name, last_name, user_password, is_employee, is_admin) "
					+ "VALUES (?, ?, ?, ?, ?);";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, u.getFirst_name());
			stm.setString(2, u.getLast_name());
			stm.setString(3, u.getPassword());

			stm.setBoolean(4, u.isEmployee());
			stm.setBoolean(5, u.isAdmin());

			if (!stm.execute()) {
				return false;
			}
		} catch (SQLException e) {
			logger.warn("Unable to add user", e);
			return false;
		}

		return true;
	}

	@Override
	public boolean updateUser(User u) {
		int id = u.getId();
		String first_name = u.getFirst_name();
		String last_name = u.getFirst_name();
		String password = u.getPassword();

		boolean isEmployee = u.isEmployee();
		boolean isAdmin = u.isAdmin();

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "UPDATE project0.users SET firstname = ?, last_name = ?, user_password = ?, account_id = ?, is_employee = ?, is_admin = ? WHERE user_id = ?;";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, first_name);
			stm.setString(2, last_name);
			stm.setString(3, password);
			stm.setBoolean(5, isEmployee);
			stm.setBoolean(6, isAdmin);
			stm.setInt(7, id);

			if (!stm.execute()) {
				return false;
			}
		} catch (SQLException ex) {
			logger.warn("Unable to update the user", ex);
			return false;
		}

		return true;

	}

	@Override
	public boolean deleteUser(User u) {
		int id = u.getId();
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "DELETE FROM users" + "WHERE user_id = ?;";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, id);
			if (!stm.execute()) {
				return false;
			}
		} catch (SQLException e) {
			logger.warn("Unable to delete the user", e);
			return false;
		}

		return true;
	}

	public User getUserByFnameAndPassword(String fname, String password) {
		User user = null;

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM project0.users WHERE first_name = ? AND user_password = ?;";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, fname);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int user_id = rs.getInt("user_id");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String password1 = rs.getString("user_password");
				boolean isEmployee = rs.getBoolean("is_employee");
				boolean isAdmin = rs.getBoolean("is_admin");
				user = new User(user_id, firstname, lastname, password1, isEmployee, isAdmin);

			}

			rs.close();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve the user by using first name and password", e);
		}
		// System.out.println(user);
		return user;
	}


	@Override
	public User getUserById(int id) {

		User user = null;

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM users WHERE user_id = ? ;";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int user_id = rs.getInt("user_id");
				String fname = rs.getString("first_name");
				String lname = rs.getString("last_name");
				String password = rs.getString("user_password");

				boolean isEmployee = rs.getBoolean("is_employee");
				boolean isAdmin = rs.getBoolean("is_admin");

				user = new User(user_id, fname, lname, password, isEmployee, isAdmin);

			}

			rs.close();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve the user", e);
		}
		return user;
	}

	@Override
	public BankAccount getUserBankAccount(User u1) {
	
		return null;
	}

}
