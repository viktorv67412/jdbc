package com.service;

import com.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> getAllUsers() throws SQLException, ClassNotFoundException;

    void addUser(User newUser) throws SQLException, ClassNotFoundException;
}
