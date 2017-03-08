package com.anoop.expmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anoop.expmanager.model.Account;
import com.anoop.expmanager.services.service.AccountService;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public Account getCurrentAccountDetails() {

		return accountService.getCurrentAccountDetails();
	}

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public List<Account> getAccountHistory() {

		return accountService.getAccountHistory();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void createAccount(Account account) {

		accountService.createAccount(account);
	}

	@RequestMapping(value = "/generatemonthlystatement", method = RequestMethod.POST)
	public void generateMonthlyStatement(@PathVariable("month") int month, @PathVariable("year") int year) {
		Account account = null;
		accountService.generateMonthlyStatement(month, year);
	}
}
