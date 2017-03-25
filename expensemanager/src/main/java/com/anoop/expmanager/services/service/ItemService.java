package com.anoop.expmanager.services.service;

import java.util.Date;
import java.util.List;

import com.anoop.expmanager.model.Item;
import com.anoop.expmanager.model.RentSheet;

public interface ItemService {
	public List<Item> findAllItemBetweenDate(Date startDate, Date endDate);
	public List<Item> findAllItemPerUserMonthAndYear(long userID, int month, int year);
    public List<Item> findAllItemPerUserAndDate(long userID, Date startDate, Date endDate);
	public void saveItem(Item item);

	public List<Item> findAll();
}
