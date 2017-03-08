package com.anoop.expmanager.services.service;

import java.util.List;

import com.anoop.expmanager.model.Item;

public interface ItemService {
	public List<Item> findAllItemPerMonthAndYear(int month, int year);

	public List<Item> findAllItemPerUserMonthAndYear(long userID, int month, int year);

	public void saveItem(Item item);

	public List<Item> findAll();
}
