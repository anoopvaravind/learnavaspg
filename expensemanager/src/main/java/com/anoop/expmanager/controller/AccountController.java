package com.anoop.expmanager.controller;

import java.util.List;

import com.anoop.expmanager.util.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.anoop.expmanager.model.Account;
import com.anoop.expmanager.services.service.AccountService;

@Controller
//@RequestMapping(value = "/app/account")
@RequestMapping(value = "/admin/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String account() throws Exception{
        return "account";
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Account getCurrentAccountDetails() throws Exception{

        return accountService.getCurrentAccountDetails();
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public List<Account> getAccountHistory() throws Exception{

        return accountService.getAccountHistory();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createAccount(Account account) throws Exception{

        accountService.createAccount(account);
    }

    @RequestMapping(value = "/generatemonthlystatement", method = RequestMethod.POST)
    @ResponseBody
    public Notification generateMonthlyStatement(@RequestParam("month") int month, @RequestParam("year") int year,
                                                 @RequestParam("currentRentAmount") double currentRentAmount) throws Exception{
        Account account = null;
        return accountService.generateMonthlyStatement(month, year, currentRentAmount);
    }
}
