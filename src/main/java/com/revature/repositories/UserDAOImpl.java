package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

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
				String password = rs.getString("email");
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

			String sql = "INSERT INTO users (first_name, last_name, password, isemployee, isadmin) "
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

			String sql = "UPDATE public.users SET fname = ?, lname = ?, password = ?, account_id = ?, is_employee = ?, is_admin = ?, is_logged_in = ? WHERE user_id = ?;";

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

}
