package com.anoop.expmanager.controller;

import java.util.List;

import com.anoop.expmanager.util.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.anoop.expmanager.model.Account;
import com.anoop.expmanager.model.User;
import com.anoop.expmanager.services.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String user() throws Exception{

        return "user";
    }
    @RequestMapping(value = "/activeusers", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getActiveUsers() throws Exception{

        return userService.getActiveUsers();
    }

    @RequestMapping(value = "/allusers", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() throws Exception{

        return userService.getAllUsers();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Notification saveUser(@RequestBody User user, HttpServletRequest request) throws Exception{
        userService.saveUser(user);
        return new Notification(true,false,"User saved successfully",userService.getAllUsers());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Notification deleteUser(@RequestBody User user, HttpServletRequest request) throws Exception{
        System.out.println("#######ItemController deleteUser");
        userService.deleteUser(user);
        return new Notification(true,false,"User deleted successfully",userService.getAllUsers());
    }
}
