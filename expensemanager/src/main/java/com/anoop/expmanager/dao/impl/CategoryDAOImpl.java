package com.anoop.expmanager.dao.impl;

import com.anoop.expmanager.dao.CategoryDAO;
import com.anoop.expmanager.model.Category;
import com.anoop.expmanager.services.service.CategoryService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SONY
 * Date: 3/15/17
 * Time: 9:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CategoryDAOImpl implements CategoryDAO {
    @Autowired
    SessionFactory sessionFactory;
    @Override
    public List<Category> listAll() {
        Session session = null;
        List<Category> categories= null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Category.class);
            categories  =criteria.list();
            if(categories == null || categories.isEmpty()) {
                System.out.println("category is null");
                return null;
            }


        } catch (Exception e) {
            System.out.println("Caught exception in listAll() : " + e);
        } finally {
            session.close();
        }
        return categories;
    }
}
