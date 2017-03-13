package com.anoop.expmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @RequestMapping(value = "/rent", method = RequestMethod.GET)
    public String rentReport() {
        return "rentreport";
    }
    @RequestMapping(value = "/expense", method = RequestMethod.GET)
    public String expenseReport() {
        return "expensereport";
    }
}
