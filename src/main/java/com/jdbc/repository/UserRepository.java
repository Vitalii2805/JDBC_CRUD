package com.jdbc.repository;

import com.jdbc.model.User;
import com.jdbc.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private Connection connection;


    public UserRepository() {
        connection = DbUtil.getConnection();
    }

    @Override
    public void addUser(User user) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO stud(firstName, lastName, date, email) VALUES (?, ?, ?, ?) ");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setDate(3,new Date(user.getDate().getTime()));
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM stud WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void upDateUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE stud SET firstname = ?, lastname = ?, date = ?, email = ? WHERE id = ?" );
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3, new Date(user.getDate().getTime()));
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from stud");
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setDate(resultSet.getDate("date"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getUserById(int id) {
        User user = new User();
        try {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM stud WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setDate(resultSet.getDate("date"));
                user.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
