package com.anoop.expmanager.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.anoop.expmanager.model.User;
import com.anoop.expmanager.util.Notification;
import com.anoop.expmanager.util.Util;
import org.springframework.beans.factory.annotation.Autowired;

import com.anoop.expmanager.DO.UserIncommExpenseSummaryDO;
import com.anoop.expmanager.dao.AccountDAO;
import com.anoop.expmanager.dao.ItemDAO;
import com.anoop.expmanager.dao.RentSheetDAO;
import com.anoop.expmanager.dao.SettingsDAO;
import com.anoop.expmanager.dao.UserDAO;
import com.anoop.expmanager.model.Account;
import com.anoop.expmanager.model.RentSheet;
import com.anoop.expmanager.services.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDAO accountDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	ItemDAO itemDAO;
	@Autowired
	RentSheetDAO rentSheetDAO;
	@Autowired
	SettingsDAO settingsDAO;

	@Override
	public Account getCurrentAccountDetails() {
		return accountDAO.getCurrentAccountDetails();
	}

	@Override
	public List<Account> getAccountDetailsPerMonthAndYear(int month, int year) {
		return accountDAO.getAccountDetailsPerMonthAndYear(month, year);
	}

	@Override
	public List<Account> getAccountHistory() {
		return accountDAO.getAccountHistory();
	}

	@Override
	public void createAccount(Account account) {
		accountDAO.createAccount(account);
	}

	@Override
	public Notification generateMonthlyStatement(int month, int year, double currentRentAmount) {
		List<Account> accountAlreadyGenerated = accountDAO.getAccountDetailsPerMonthAndYear(month, year);
		List<RentSheet> newRentSheetList = new ArrayList<RentSheet>();
		RentSheet newRentSheet;
		RentSheet previousRentSheet;
		Account monthlyAccountSummary = new Account();
        Date startDate = null;
        Date endDate = null;
		if (accountAlreadyGenerated != null && !accountAlreadyGenerated.isEmpty()) {
			System.out.println("Monthly statement already generated");
            return new Notification(false,true,"Monthly statement already generated");
		}
        System.out.println("Going to generate Monthly statement");

//        HashMap<String, String> settingsMap = settingsDAO.loadSetstingsMap();
//		double originalRentAmount = Double.parseDouble(settingsMap.get("RENT_AMOUNT"));
        double originalRentAmount;
        originalRentAmount = currentRentAmount;
		double monthlyExpense = 0.0;
		double monthlyIncomm = 0.0;
        double rentDue = 0.0;

//		List<UserIncommExpenseSummaryDO> usersIncommExpenseSummary = itemDAO.calculateUserExpense(month,year); // all
        List<User> activeUsers = userDAO.getActiveUsers();
        System.out.println("activeUsers"+activeUsers);
        for (User activeUser : activeUsers) {
            startDate = Util.createStartDateFromMonthAndYear(month,year);
            endDate = Util.getEndDateOfMonth(startDate);
            double userExpense = itemDAO.calculateUserExpenseBetweenDate(activeUser.getId(),startDate,endDate);
            System.out.println("#########################userExpense"+userExpense);
            previousRentSheet = rentSheetDAO.getLastMonthRentSheetPerUser(activeUser.getId());
            newRentSheet = new RentSheet();
            if (previousRentSheet == null) {
                newRentSheet.setAdjustedRent(originalRentAmount - userExpense);
                rentDue = 0.0;
            } else {
                monthlyIncomm += previousRentSheet.getRentActullyPaid();
                rentDue = previousRentSheet.getAdjustedRent()-previousRentSheet.getRentActullyPaid();
                newRentSheet.setAdjustedRent(
                        originalRentAmount - userExpense + rentDue);
            }
            newRentSheet.setUser(activeUser);
            newRentSheet.setDue(rentDue);
            newRentSheet.setOriginalRentAmount(originalRentAmount);
            newRentSheet.setTotalExpensePaid(userExpense);
            newRentSheet.setRentGeneratedForMonth(month);
            newRentSheet.setRentGeneratedForYear(year);
            newRentSheet.setRentGeneratedDate(new Date());
            newRentSheet.setRentActullyPaid(0);
            newRentSheet.setRentPaidDate(null);
            newRentSheet.setModifiedDate(new Date());
            newRentSheet.setComment("");

            monthlyExpense += userExpense;
           // newRentSheetList.add(newRentSheet);
            rentSheetDAO.saveRentSheet(newRentSheet);
        }

		Account latestAccount = accountDAO.getLatestAccount();
        System.out.println("latestAccount"+latestAccount);
        if(latestAccount == null) {
            monthlyAccountSummary.setOpeningBalance(0);
            monthlyAccountSummary.setClossingBalance(monthlyIncomm - monthlyExpense);
        } else {
            monthlyAccountSummary.setOpeningBalance(latestAccount.getClossingBalance());
            monthlyAccountSummary.setClossingBalance(latestAccount.getClossingBalance() + monthlyIncomm - monthlyExpense);
        }
		monthlyAccountSummary.setMonth(month);
		monthlyAccountSummary.setYear(year);
		monthlyAccountSummary.setMonthlyExpense(monthlyExpense);
		monthlyAccountSummary.setMonthlyIncomm(monthlyIncomm);
        monthlyAccountSummary.setCeatedDate(new Date());
        monthlyAccountSummary.setModifiedDate(new Date());

		accountDAO.createAccount(monthlyAccountSummary);
        return new Notification(true,false,"Successfully generated statement");
	}

}
