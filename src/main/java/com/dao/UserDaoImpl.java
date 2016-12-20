package com.dao;

import com.model.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    private static final String SELECT_FROM_TABLE = "SELECT * FROM USERS;";

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<User>();

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(SELECT_FROM_TABLE);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            users.add(new User(resultSet.getString("name"), resultSet.getInt("age")));
        }
        return users;
    }

    public void addUser(User newUser) throws SQLException, ClassNotFoundException {
        String INSERT_INTO_TABLE = "INSERT INTO users (name, age) VALUES('" + newUser.getName() + "', " + newUser.getAge() + ");";

        Statement statement = getDbConnection().createStatement();
        statement.execute(INSERT_INTO_TABLE);
    }

    private Connection getDbConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:file:/Users/viktor/Desktop/java_ee/h2_db", "root", "root");
    }
}
