package com.anoop.expmanager.dao;

import java.util.List;

import com.anoop.expmanager.model.User;

public interface UserDAO {
    public List<User> getActiveUsers();

    User getUserByUserName(String userName);
}
