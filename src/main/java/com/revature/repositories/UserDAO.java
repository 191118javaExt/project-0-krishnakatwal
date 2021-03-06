package com.revature.repositories;

import java.util.List;

import com.revature.models.BankAccount;
import com.revature.models.User;

public interface UserDAO {

List<User> getUsers();

boolean addUser(User u);

boolean updateUser(User u);

boolean deleteUser(User u);

public  User getUserByFnameAndPassword(String fname, String password);


public BankAccount getUserBankAccount(User u1);

public User getUserById(int choice);


}
