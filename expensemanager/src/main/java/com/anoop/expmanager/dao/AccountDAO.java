package com.anoop.expmanager.dao;

import java.util.List;

import com.anoop.expmanager.model.Account;

public interface AccountDAO {
	public Account getCurrentAccountDetails();

	public List<Account> getAccountHistory();

	public Account getLatestAccount();

	public void createAccount(Account account);

	public List<Account> getAccountDetailsPerMonthAndYear(int month, int year);

}
