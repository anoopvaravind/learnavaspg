package com.anoop.expmanager.services.service;

import java.util.List;

import com.anoop.expmanager.model.RentSheet;

public interface RentService {
	public List<RentSheet> getCurrentMonthRentSheetDetails();

	public List<RentSheet> getRentSheetHistory();

	public List<RentSheet> getRentSheetHistoryPerMonthAndYear(int month, int year);

	public void saveRentSheet(RentSheet rentSheet);

    public List<RentSheet> getRentHistoryForUser(long userId);

}
