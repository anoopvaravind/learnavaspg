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
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date getEndDateOfMonth(Date date) {
        date = removeTimeFromDate(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.getActualMaximum(Calendar.DAY_OF_MONTH));
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
}
