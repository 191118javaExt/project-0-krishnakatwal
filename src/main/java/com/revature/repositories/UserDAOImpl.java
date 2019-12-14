package com.revature.repositories;

import java.sql.Connection;
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
			
			while(rs.next()) {
				int id = rs.getInt("user_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String password = rs.getString("email");
				boolean isEmployee = rs.getBoolean("isEmployee");
				boolean isAdmin = rs.getBoolean("isAdmin");
				
				User u = new User(id, first_name, last_name, password, isEmployee ,isAdmin);
	
				list.add(u);
			}
			
			rs.close();
		} catch(SQLException e) {
			logger.warn("Unable to retrieve all users", e);
		}
		
		return list;
	}

	@Override
	public boolean addUser(User u) {
		
		return false;
	}

	@Override
	public boolean updateUser(User u) {
//		UPDATE table User
//		SET column1 = value1,
//		    column2 = value2 
//		WHERE condition;
		return false;
	}

	@Override
	public boolean deleteUser(User u) {

		return false;
	}

}
