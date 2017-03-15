package com.anoop.expmanager.dao;

import com.anoop.expmanager.model.Category;
import com.sun.javafx.collections.MappingChange;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SONY
 * Date: 3/15/17
 * Time: 9:25 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CategoryDAO {
    public List<Category> listAll();
}
