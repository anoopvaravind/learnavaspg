package com.anoop.expmanager.controller;

import com.anoop.expmanager.model.Item;
import com.anoop.expmanager.model.RentSheet;
import com.anoop.expmanager.services.service.ItemService;
import com.anoop.expmanager.services.service.RentService;
import com.anoop.expmanager.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        Date startDate = Util.createStartDateFromMonthAndYear(month,year);
        Date endDate = Util.getEndDateOfMonth(startDate);
        return itemService.findAllItemBetweenDate(startDate, endDate);
    }
}
