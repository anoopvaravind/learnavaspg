package com.anoop.expmanager.dao;

import java.util.List;

import com.anoop.expmanager.model.RentSheet;

public interface RentSheetDAO {
    public List<RentSheet> getCurrentMonthRentSheetDetails();

    public List<RentSheet> getRentSheetHistory();

    public List<RentSheet> getRentSheetHistoryPerMonthAndYear(int month, int year);

    public void saveRentSheet(RentSheet rentPaid);

    public RentSheet getLastMonthRentSheetPerUser(long userID);

    public List<RentSheet> getRentHistoryForUser(long userId);

    public List<RentSheet> getRentSheetHistoryPerMonthYearUser(int month, int year, long userId);
}
