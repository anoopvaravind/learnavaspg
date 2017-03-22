package com.anoop.expmanager.controller;

import com.anoop.expmanager.model.Item;
import com.anoop.expmanager.services.service.ItemService;
import com.anoop.expmanager.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

	@RequestMapping(value = "/finditemperusermonthyear", method = RequestMethod.GET)
    @ResponseBody
	public List<Item> findAllItemPerUserMonthAndYear(@PathVariable("month") int month, @PathVariable("year") int year) {
		return itemService.findAll();
	}

	@RequestMapping(value = "/save", method=RequestMethod.POST)
    @ResponseBody
	public List<Item> saveItem(@RequestBody Item item) {
        System.out.println("###########################################################");
        System.out.println("getItemName"+item.getItemName());
        System.out.println("getCategory"+item.getCategory());
        System.out.println("getPurchasedDate"+item.getPurchasedDate());
        System.out.println("getPrice"+item.getPrice());
		itemService.saveItem(item);
        return itemService.findAllItemPerUserMonthAndYear(0, 0, 0);
//		return itemService.findAllItemPerUserMonthAndYear(0, 0, 0);
        /*ModelAndView mv = new ModelAndView();
        mv.setViewName("item");mv.
        return mv;*/
	}

    @RequestMapping(value = "/save2", method=RequestMethod.POST)
    @ResponseBody
    public List<Item> saveItem2(@RequestBody HashMap<String,String> map) {
        System.out.println("###########################################################"+map);
       /* System.out.println("getItemName"+item.getItemName());
        System.out.println("getCategory"+item.getCategory());
        System.out.println("getPurchasedDate"+item.getPurchasedDate());
        System.out.println("getPrice"+item.getPrice());
        itemService.saveItem(item);
        return itemService.findAllItemPerUserMonthAndYear(0, 0, 0);*/
        return null;
    }

    @RequestMapping(value = "/save3", method=RequestMethod.POST)
    @ResponseBody
    public List<Item> saveItem3(@RequestBody Item item) {
        System.out.println("###########################################################"+item.getItemName());
        List<Item> items= new ArrayList<Item>() ;
        return  items;
    }

    @RequestMapping(value = "/save4", method=RequestMethod.POST)
    @ResponseBody
    public List<Item> saveItem4(@RequestBody String item) {
        System.out.println("###########################################################"+item);
        List<Item> items= new ArrayList<Item>() ;
        return  items;
    }

    @RequestMapping(value = "/save5", method=RequestMethod.POST)
    public @ResponseBody List<Item> saveItem5(@ModelAttribute(value="item") Item item, BindingResult result) {
        System.out.println("###########################################################"+item);
        System.out.println("###########################################################"+item.getItemName());
        List<Item> items= new ArrayList<Item>() ;
        return  items;
    }

    @RequestMapping(value = "/save6", method=RequestMethod.POST)
    public  @ResponseBody List<Item> saveItem6(@RequestBody String itemName) {
        System.out.println("###########################################################"+itemName);
        List<Item> items= new ArrayList<Item>() ;
        return  items;
    }

    @RequestMapping(value = "/save7", method=RequestMethod.POST)
    public  @ResponseBody String saveItem7() {
        System.out.println("###########################################################7");
        List<Item> items= new ArrayList<Item>() ;
        return  "hello";
    }

}
