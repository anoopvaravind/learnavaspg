package com.anoop.expmanager.controller;

import com.anoop.expmanager.model.Account;
import com.anoop.expmanager.model.Item;
import com.anoop.expmanager.model.RentSheet;
import com.anoop.expmanager.services.service.AccountService;
import com.anoop.expmanager.services.service.ItemService;
import com.anoop.expmanager.services.service.RentService;
import com.anoop.expmanager.util.AccountChart;
import com.anoop.expmanager.util.UserSession;
import com.anoop.expmanager.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SONY
 * Date: 3/9/17
 * Time: 10:22 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/app/report")
public class ReportController {
    @Autowired
    private RentService rentService;
    @Autowired
    private ItemService itemService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/rent", method = RequestMethod.GET)
    public String rentReport() {
        return "rentreport";
    }

    @RequestMapping(value = "/expense", method = RequestMethod.GET)
    public String expenseReport() {
        return "expensereport";
    }

    @RequestMapping(value = "/renthistorypermonthandyear", method = RequestMethod.GET)
    @ResponseBody
    public List<RentSheet> getRentPaidHistoryPerMonthAndYear(@RequestParam("month") int month, @RequestParam("year") int year) {
        return rentService.getRentSheetHistoryPerMonthAndYear(month, year);
    }

    @RequestMapping(value = "/expensehistorypermonthandyear", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> getExpenseHistoryPerMonthAndYear(@RequestParam("month") int month, @RequestParam("year") int year) {
        Date startDate = Util.createStartDateFromMonthAndYear(month, year);
        Date endDate = Util.getEndDateOfMonth(startDate);
        return itemService.findAllItemBetweenDate(startDate, endDate);
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
//    @ResponseBody
    public ModelAndView getAccountHistory() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("accountreport");
        modelAndView.getModelMap().addAttribute("account", accountService.getAccountHistory());
        return modelAndView;
    }

    @RequestMapping(value = "/accountsummary", method = RequestMethod.GET)
    @ResponseBody
    public List<AccountChart> getAccountSummary() {
        List<Account> accounts =  accountService.getAccountHistory();
        List<AccountChart> accountCharts = new ArrayList<AccountChart>();
        AccountChart accountChart;
        for (Account account : accounts) {
            accountChart = new AccountChart();
            accountChart.setMonthYear(Util.getMonthNameFromMonthNumber(account.getMonth())+"-"+account.getYear());
            accountChart.setTotalExpense(account.getMonthlyExpense());
//            accountChart.setOpeningBalance(account.getOpeningBalance());
//            accountChart.setClosingBalance(account.getClossingBalance());
            accountCharts.add(accountChart);
        }
        return accountCharts;
    }
}
