package com.anoop.expmanager.util;

/**
 * Created with IntelliJ IDEA.
 * User: SONY
 * Date: 3/29/17
 * Time: 11:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountChart {
    String monthYear;
    double totalExpense;
    double openingBalance;
    double closingBalance;
    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public double getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(double closingBalance) {
        this.closingBalance = closingBalance;
    }
}
