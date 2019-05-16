package com.jdbc.repository;

import com.jdbc.model.User;

import java.util.List;

public interface IUserRepository {
    public void addUser(User user);
    public void deleteUser(int id);
    public void upDateUser (User user);
    public List<User> getUsers ();
    public User getUserById (int id);
}
