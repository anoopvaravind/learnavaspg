package com.anoop.expmanager.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

    public static void main(String[] args) throws ParseException {

        /* Calendar cal = Calendar.getInstance();

    cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.getActualMinimum(Calendar.DAY_OF_MONTH));

    Date startDate = cal.getTime();
    cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.getActualMaximum(Calendar.DAY_OF_MONTH));

    Date endDate = cal.getTime();

    System.out.println("cal.get(Calendar.MONTH) : " + cal.get(Calendar.MONTH));

    System.out.println("Start date Year is0 : " + startDate);

    System.out.println("End date Year is0 : " + endDate);
    System.out.println("Current Year is1 : " + cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    System.out.println("Current Year is2 : " + cal.getActualMinimum(Calendar.DAY_OF_MONTH));
    Date d= new Date();
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    d = formatter.parse(formatter.format(d));
    System.out.println("getStartDateOfMonth : " + d);
    System.out.println("getStartDateOfMonth : " + getStartDateOfMonth(d));
    System.out.println("getEndDateOfMonth : " + getEndDateOfMonth(d));
    System.out.println("createStartDateFromMonthAndYear : " + createStartDateFromMonthAndYear(12,2017));*/
        int i = 10;
        System.out.println("####### ready to save !!!" + (i + 1));
        //System.out.println("getStartDateOfMonth : " + Util.getMonthFromDate(new Date()));
    }

    public static Date getStartDateOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date getEndDateOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date removeTimeFromDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatter.parse(formatter.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date createStartDateFromMonthAndYear(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1, 0, 0, 0);

        return calendar.getTime();
    }
}
