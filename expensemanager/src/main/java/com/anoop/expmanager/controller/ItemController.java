package com.anoop.expmanager.controller;

import com.anoop.expmanager.model.Item;
import com.anoop.expmanager.model.User;
import com.anoop.expmanager.services.service.ItemService;
import com.anoop.expmanager.util.Notification;
import com.anoop.expmanager.util.UserSession;
import com.anoop.expmanager.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = "/app/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView item() throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("item");
        return mv;
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> findAll() throws Exception{
        return itemService.findAll();
    }

    @RequestMapping(value = "/finditempermonthyear", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> findAllItemPerMonthAndYear(@RequestParam("month") int month, @RequestParam("year") int year) throws Exception{
        return itemService.findAll();
    }

    @RequestMapping(value = "/findAllItemPerUserAndDate", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> findAllItemPerUserAndDate(HttpServletRequest request) throws Exception{
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        return itemService.findAllItemPerUserAndDate(userSession.getUser().getId(),
                Util.getStartDateOfMonth(new Date()), Util.getEndDateOfMonth(new Date()));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Notification saveItem(@RequestBody Item item, HttpServletRequest request) throws Exception{
        System.out.println("#######ItemController saveItem");
        item.setPurchasedDate(Util.removeTimeFromDate(item.getPurchasedDate()));
        System.out.println("#######v" + item.getPurchasedDate());
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        item.setUser(userSession.getUser());
        System.out.println("####### item.setUser" + item.getUser());
        return itemService.saveItem(item);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteItem(@RequestBody Item item, HttpServletRequest request) throws Exception{
        System.out.println("################" + item.getItemName());

        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        if (item.getUser().getId() != userSession.getUser().getId()) {
            System.out.println("You don't have permission to delete this item!!!");
            return new Notification(false, true, "You don't have permission to delete this item!!!", null);
        }
        itemService.deleteItem(item);
        return new Notification(true, false, "Deleted Successfully !!!", itemService.findAllItemPerUserAndDate(userSession.getUser().getId(),
                Util.getStartDateOfMonth(new Date()), Util.getEndDateOfMonth(new Date())));

    }

}
