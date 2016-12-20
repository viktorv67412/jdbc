package com.dao;

import com.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    List<User> getAllUsers() throws SQLException, ClassNotFoundException;

    void addUser(User newUser) throws SQLException, ClassNotFoundException;
}
