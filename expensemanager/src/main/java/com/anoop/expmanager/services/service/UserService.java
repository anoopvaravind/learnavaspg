package com.anoop.expmanager.services.service;

import java.util.List;

import com.anoop.expmanager.model.User;

public interface UserService {
	public List<User> getActiveUsers();
    public User getUserByUserName(String username);

}
