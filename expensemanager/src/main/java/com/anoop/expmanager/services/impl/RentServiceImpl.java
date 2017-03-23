package com.anoop.expmanager.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.anoop.expmanager.dao.RentSheetDAO;
import com.anoop.expmanager.model.RentSheet;
import com.anoop.expmanager.services.service.RentService;
import org.springframework.stereotype.Service;

@Service
public class RentServiceImpl implements RentService {
	@Autowired
	RentSheetDAO rentDAO;

	@Override
	public List<RentSheet> getCurrentMonthRentSheetDetails() {
		return rentDAO.getCurrentMonthRentSheetDetails();
	}

	@Override
	public List<RentSheet> getRentSheetHistory() {
		return rentDAO.getRentSheetHistory();
	}

	@Override
	public List<RentSheet> getRentSheetHistoryPerMonthAndYear(int month, int year) {
		return rentDAO.getRentSheetHistoryPerMonthAndYear(month, year);
	}

	@Override
	public void saveRentSheet(RentSheet rentSheet) {
		
		rentDAO.saveRentSheet(rentSheet);
	}

    @Override
    public List<RentSheet> getRentHistoryForUser(long userId) {
        return rentDAO.getRentHistoryForUser(userId);  //To change body of implemented methods use File | Settings | File Templates.
    }

}
