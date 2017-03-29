package com.anoop.expmanager.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.anoop.expmanager.dao.UserDAO;
import com.anoop.expmanager.model.Account;
import com.anoop.expmanager.model.User;
import com.anoop.expmanager.util.Util;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<User> getActiveUsers() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("enabled", true));
            return criteria.list();
        } catch (Exception e) {
            System.out.println("Caught exception in getactiveUsers() : " + e);
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public User getUserByUserName(String userName) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("username", userName));
            return (User) criteria.uniqueResult();
        } catch (Exception e) {
            System.out.println("Caught exception in getUserByUserName() : " + e);
        } finally {
            session.close();
        }
        return null;

    }

}
