package com.anoop.expmanager.controller;

import com.anoop.expmanager.model.Item;
import com.anoop.expmanager.model.User;
import com.anoop.expmanager.services.service.ItemService;
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
    public ModelAndView item() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("item");
        return mv;
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> findAll() {
        return itemService.findAll();
    }

	@RequestMapping(value = "/finditempermonthyear", method = RequestMethod.GET)
    @ResponseBody
	public List<Item> findAllItemPerMonthAndYear(@RequestParam("month") int month, @RequestParam("year") int year) {
		return itemService.findAll();
	}

	@RequestMapping(value = "/findAllItemPerUserAndDate", method = RequestMethod.GET)
    @ResponseBody
	public List<Item> findAllItemPerUserAndDate(HttpServletRequest request) {
        UserSession userSession = (UserSession)request.getSession().getAttribute("userSession");
		return itemService.findAllItemPerUserAndDate(userSession.getUser().getId(),
                Util.getStartDateOfMonth(new Date()),Util.getEndDateOfMonth(new Date()));
	}

	@RequestMapping(value = "/save", method=RequestMethod.POST)
    @ResponseBody
	public List<Item> saveItem(@RequestBody Item item, HttpServletRequest request) {
        System.out.println("###########################################################");
        System.out.println("getItemName"+item.getItemName());
        System.out.println("getCategory"+item.getCategory());
        System.out.println("getPurchasedDate"+item.getPurchasedDate());
        System.out.println("getPrice"+item.getPrice());
        System.out.println("getCategory"+item.getCategory());
        System.out.println("getCategory().getId()"+item.getCategory().getId());
        item.setPurchasedDate(Util.removeTimeFromDate(item.getPurchasedDate()));
        UserSession userSession = (UserSession)request.getSession().getAttribute("userSession");
        item.setUser(userSession.getUser());
		itemService.saveItem(item);
        return itemService.findAllItemPerUserAndDate(userSession.getUser().getId(),
                Util.getStartDateOfMonth(new Date()),Util.getEndDateOfMonth(new Date()) );
	}

}
