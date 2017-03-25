package com.anoop.expmanager.dao;

import java.util.Date;
import java.util.List;

import com.anoop.expmanager.DO.UserIncommExpenseSummaryDO;
import com.anoop.expmanager.model.Item;

public interface ItemDAO {
	public List<Item> findAllItemBetweenDate(Date startDate, Date endDate);

    public List<Item> findAllItemPerUserMonthAndYear(long userID, int month, int year);

	public void saveItem(Item item);

	public List<Item> findAll();

	public List<UserIncommExpenseSummaryDO> calculateUserExpense(int month, int year);

    List<Item> findAllItemPerUserAndDate(long userID, Date startDate, Date endDate);

    double calculateUserExpenseBetweenDate(long userID, Date startDate, Date endDate);
}
