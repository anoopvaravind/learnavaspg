package com.anoop.expmanager.controller;

import com.anoop.expmanager.model.Category;
import com.anoop.expmanager.services.service.CategoryService;
import com.anoop.expmanager.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SONY
 * Date: 3/15/17
 * Time: 9:22 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/app/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public Response listAll() throws Exception{

        List<Category> categories = categoryService.listAll();
        if (categories == null || categories.isEmpty()) {
            return new Response("Null");
//            return null;
        }

        HashMap<String, String> categoryMap = new LinkedHashMap<String, String>();
        for (Category category : categories) {
            categoryMap.put(category.getId() + "", category.getCategoryName());
        }
        return new Response(categoryMap);
//        return categoryMap;
    }
}
