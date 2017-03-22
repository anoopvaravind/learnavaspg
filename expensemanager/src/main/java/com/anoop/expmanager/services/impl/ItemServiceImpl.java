package com.anoop.expmanager.services.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.anoop.expmanager.dao.ItemDAO;
import com.anoop.expmanager.model.Item;
import com.anoop.expmanager.services.service.ItemService;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemDAO itemDAO;

	@Override
	public List<Item> findAllItemPerMonthAndYear(int month, int year) {
		return itemDAO.findAllItemPerMonthAndYear(month, year);
	}

	@Override
	public List<Item> findAllItemPerUserMonthAndYear(long userID, int month, int year) {
		return itemDAO.findAllItemPerUserMonthAndYear(userID, month, year);
	}

    @Override
    public List<Item> findAllItemPerUserAndDate(long userID, Date startDate, Date endDate) {
        return itemDAO.findAllItemPerUserAndDate(userID,startDate,endDate);
    }

    @Override
	public void saveItem(Item item) {
		itemDAO.saveItem(item);
	}

	@Override
	public List<Item> findAll() {
		return itemDAO.findAll();
	}

}
