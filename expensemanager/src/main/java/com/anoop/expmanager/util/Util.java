package com.anoop.expmanager.util;

import java.util.Calendar;

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
}
