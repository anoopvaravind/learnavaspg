package com.anoop.expmanager.services.impl;

import com.anoop.expmanager.dao.UserDAO;
import com.anoop.expmanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SONY
 * Date: 3/6/17
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("loginService")
public class LoginService implements UserDetailsService {
    @Autowired
    UserDAO userDao;


    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try {
            System.out.println("userName" + userName);
            User user = userDao.getUserByUserName(userName);
            String role = user.getRoles().getRolename();
            List<GrantedAuthority> rolesList = new ArrayList<GrantedAuthority>();
            rolesList.add(new SimpleGrantedAuthority(role));

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    true, true, true, true, rolesList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}