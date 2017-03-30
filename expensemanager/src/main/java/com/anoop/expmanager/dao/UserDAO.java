package com.anoop.expmanager.dao;

import java.util.List;

import com.anoop.expmanager.model.User;

public interface UserDAO {
    public List<User> getActiveUsers();

    public List<User> getAllUsers();

    public User getUserByUserName(String userName);

    public void save(User user);

    public void deleteUser(User user);
}
