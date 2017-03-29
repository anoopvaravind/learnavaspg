package com.anoop.expmanager.services.impl;

import com.anoop.expmanager.dao.CategoryDAO;
import com.anoop.expmanager.model.Category;
import com.anoop.expmanager.services.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SONY
 * Date: 3/15/17
 * Time: 9:24 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    @Override
    public List<Category> listAll() {
        return categoryDAO.listAll();
    }
}
