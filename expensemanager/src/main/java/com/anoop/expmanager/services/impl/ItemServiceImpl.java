package com.anoop.expmanager.services.impl;

import java.util.Date;
import java.util.List;

import com.anoop.expmanager.dao.RentSheetDAO;
import com.anoop.expmanager.model.RentSheet;
import com.anoop.expmanager.util.Notification;
import com.anoop.expmanager.util.Util;
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
    @Autowired
    private RentSheetDAO rentSheetDAO;

    @Override
    public List<Item> findAllItemBetweenDate(Date startDate, Date endDate) {
        return itemDAO.findAllItemBetweenDate(startDate, endDate);
    }

    @Override
    public List<Item> findAllItemPerUserMonthAndYear(long userID, int month, int year) {
        return itemDAO.findAllItemPerUserMonthAndYear(userID, month, year);
    }

    @Override
    public List<Item> findAllItemPerUserAndDate(long userID, Date startDate, Date endDate) {
        System.out.println("findAllItemPerUserAndDate" + itemDAO.findAllItemPerUserAndDate(userID, startDate, endDate).size());
        return itemDAO.findAllItemPerUserAndDate(userID, startDate, endDate);
    }

    @Override
    public Notification saveItem(Item item) {
        RentSheet currentRentSheet = rentSheetDAO.getRentSheetHistoryPerMonthYearUser(Util.getMonthFromDate(item.getPurchasedDate()) + 1, Util.getYearFromDate(item.getPurchasedDate()), item.getUser().getId());
        if (currentRentSheet != null) {
            System.out.println("####### ready to save !!!" + currentRentSheet.getRentGeneratedForMonth());
            System.out.println("####### ready to save !!!" + (Util.getMonthFromDate(item.getPurchasedDate()) + 1));
            System.out.println("####### ready to save !!!" + currentRentSheet.getRentGeneratedForYear());
            System.out.println("####### ready to save !!!" + Util.getYearFromDate(item.getPurchasedDate()));
            System.out.println("You can't add expense for this month since Rent is already generated !!!");
            return new Notification(false, true, "You can't add expense for this month since Rent is already generated !!!", null);
        }
        System.out.println("####### currentRentSheet !!!" + currentRentSheet);
        itemDAO.saveItem(item);
        return new Notification(true, false, "Item saved successfully !!!", itemDAO.findAllItemPerUserAndDate(item.getUser().getId(),
                Util.getStartDateOfMonth(new Date()), Util.getEndDateOfMonth(new Date())));

    }

    @Override
    public void deleteItem(Item item) {
        itemDAO.deleteItem(item);
    }

    @Override
    public List<Item> findAll() {
        return itemDAO.findAll();
    }

}
