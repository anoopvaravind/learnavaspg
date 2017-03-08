package com.anoop.expmanager.services.service;

import java.util.List;

import com.anoop.expmanager.model.Account;

public interface AccountService {
	public Account getCurrentAccountDetails();

	public List<Account> getAccountDetailsPerMonthAndYear(int month, int year);

	public List<Account> getAccountHistory();

	public void createAccount(Account account);

	public void generateMonthlyStatement(int month, int year);
}
