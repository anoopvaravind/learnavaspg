package com.anoop.expmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anoop.expmanager.model.Account;
import com.anoop.expmanager.model.User;
import com.anoop.expmanager.services.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/activeusers", method = RequestMethod.GET)
    public List<User> getActiveUsers() {

        return userService.getActiveUsers();
    }
}
