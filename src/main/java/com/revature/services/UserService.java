package com.revature.services;

import java.util.List;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImpl;

public class UserService {

	UserDAO repository = new UserDAOImpl();

	public List<User> getUsers() {
		return repository.getUsers();
	}

	public boolean addUser(User u) {
		return repository.addUser(u);
	}

	public boolean updateUser(User u) {
		return repository.updateUser(u);
	}

	public boolean deleteUser(User u) {
		return repository.deleteUser(u);

	}

	public User getUserByFnameAndPassword(String fname, String password) {
		// TODO Auto-generated method stub
		return repository.getUserByFnameAndPassword(fname, password);
	}
}
