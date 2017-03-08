package com.anoop.expmanager.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.sun.javafx.collections.MappingChange;
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
	public void generateMonthlyStatement(int month, int year) {
		List<Account> accountAlreadyGenerated = accountDAO.getAccountDetailsPerMonthAndYear(month, year);
		List<RentSheet> newRentSheetList = new ArrayList<RentSheet>();
		RentSheet newRentSheet;
		RentSheet previousRentSheet;
		Account monthlyAccountSummary = new Account();

		if (accountAlreadyGenerated == null || accountAlreadyGenerated.isEmpty()) {
			System.out.println("Monthly statement already generated");
			return;
		}

        HashMap<String, String> settingsMap = settingsDAO.loadSetstingsMap();
		double originalRentAmount = Double.parseDouble(settingsMap.get("RENT_AMOUNT"));

		double monthlyExpense = 0.0;
		double monthlyIncomm = 0.0;

		List<UserIncommExpenseSummaryDO> usersIncommExpenseSummary = itemDAO.calculateUserExpense(month,year); // all
																										// user
																										// expense
		for (UserIncommExpenseSummaryDO userIncommExpense : usersIncommExpenseSummary) {
			previousRentSheet = rentSheetDAO.getLastMonthRentSheetPerUser(userIncommExpense.getUserID());
			newRentSheet = new RentSheet();
			if (previousRentSheet == null) {
				newRentSheet.setAdjustedRent(originalRentAmount - userIncommExpense.getExpensePaid());
				newRentSheet.setDue(originalRentAmount - userIncommExpense.getExpensePaid());
			} else {
				newRentSheet.setAdjustedRent(
						originalRentAmount - userIncommExpense.getExpensePaid() + previousRentSheet.getDue());
				newRentSheet
						.setDue(originalRentAmount - userIncommExpense.getExpensePaid() + previousRentSheet.getDue());
			}
			newRentSheet.setOriginalRentAmount(originalRentAmount);
			newRentSheet.setTotalExpensePaid(userIncommExpense.getExpensePaid());
			newRentSheet.setRentGeneratedForMonth(month);
			newRentSheet.setRentGeneratedForYear(year);
			newRentSheet.setRentGeneratedDate(new Date());
			newRentSheet.setRentActullyPaid(0);
			newRentSheet.setRentPaidDate(null);
			newRentSheet.setModifiedDate(new Date());
			newRentSheet.setComment("");

			monthlyExpense += userIncommExpense.getExpensePaid();
			monthlyIncomm += userIncommExpense.getRentPaid();
			newRentSheetList.add(newRentSheet);
		}

		rentSheetDAO.saveRentSheet(newRentSheetList);

		Account latestAccount = accountDAO.getLatestAccount();
		monthlyAccountSummary.setMonth(month);
		monthlyAccountSummary.setYear(year);
		monthlyAccountSummary.setMonthlyExpense(monthlyExpense);
		monthlyAccountSummary.setOpeningBalance(latestAccount.getClossingBalance());
		monthlyAccountSummary.setMonthlyIncomm(monthlyIncomm);
		monthlyAccountSummary.setClossingBalance(latestAccount.getClossingBalance() + monthlyIncomm - monthlyExpense);

		accountDAO.createAccount(monthlyAccountSummary);
	}

}
