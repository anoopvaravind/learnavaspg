package com.anoop.expmanager.controller;

import com.anoop.expmanager.model.Item;
import com.anoop.expmanager.services.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/app/item")
public class ItemController {
	@Autowired
	private ItemService itemService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String item() {
        return "item";
    }

	@RequestMapping(value = "/finditempermonthyear", method = RequestMethod.GET)
	public List<Item> findAllItemPerMonthAndYear(@PathVariable("month") int month, @PathVariable("year") int year) {
		return itemService.findAll();
	}

	@RequestMapping(value = "/finditemperusermonthyear", method = RequestMethod.GET)
	public List<Item> findAllItemPerUserMonthAndYear(@PathVariable("month") int month, @PathVariable("year") int year) {
		return itemService.findAll();
	}

	// @RequestMapping(value = "/save", method=RequestMethod.POST)
	public List<Item> saveItem(/* @RequestBody */ Item item) {
		itemService.saveItem(item);
		return itemService.findAllItemPerUserMonthAndYear(0, 0, 0);
	}
}
