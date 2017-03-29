package com.anoop.expmanager.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static int getCurrentDate() {
        return Calendar.getInstance().get(Calendar.DATE);
    }

    public static Date getStartDateOfMonth(Date date) {
        date = removeTimeFromDate(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date getEndDateOfMonth(Date date) {
        date = removeTimeFromDate(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date removeTimeFromDate(Date date) {
        System.out.println("removeTimeFromDate1" + date);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            System.out.println("removeTimeFromDate1");
            return formatter.parse(formatter.format(date));
        } catch (ParseException e) {
            System.out.println("ParseException");
            e.printStackTrace();
        }
        return null;
    }

    public static Date createStartDateFromMonthAndYear(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1, 0, 0, 0);
        return removeTimeFromDate(calendar.getTime());
    }

    public static int getDayFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonthFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public static int getYearFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }
}
