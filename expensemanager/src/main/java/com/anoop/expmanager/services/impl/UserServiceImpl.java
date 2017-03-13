package com.anoop.expmanager.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.anoop.expmanager.dao.UserDAO;
import com.anoop.expmanager.model.User;
import com.anoop.expmanager.services.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;

	@Override
	public List<User> getActiveUsers() {
		return userDAO.getactiveUsers();
	}

    @Override
    public User getUserByUserName(String username) {
        return userDAO.getUserByUserName(username);
    }

}
