package com.evolution.util;

import java.util.Date;

public class TimeUtil {
	public static Integer string2Milliseconds(String duration) {// 00:00:00.000
		try {
			String[] durations = duration.replace(".", ":").split(":");
			return Integer.valueOf(durations[0]) * 3600 * 1000 + Integer.valueOf(durations[1]) * 60 * 1000 + Integer.valueOf(durations[2]) * 1000 + Integer.valueOf(durations[3]);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static String amend(int intDuration, int n) {
		String duration = intDuration + "";
		while (duration.length() < n) {
			duration = "0" + duration;
		}
		return duration;
	}
	
	public static String milliseconds2String(Long milliseconds, Boolean includesMilliseconds) {
		return milliseconds2String(milliseconds.intValue(), includesMilliseconds);
	}
	
	public static String milliseconds2String(Integer milliseconds, Boolean includesMilliseconds) {
		int seconds = milliseconds / 1000;
		int minutes = seconds / 60;
		int hours = minutes / 60;
		String ms = amend(milliseconds % 1000, 3);
		String s = amend(seconds % 60, 2); 
		String m = amend(minutes % 60, 2);
		String h = amend(hours, 2);
		if (includesMilliseconds) {
			return h + ":" + m + ":" + s + ":" + ms;
		}
		return h + ":" + m + ":" + s;
	}
	
	@SuppressWarnings("deprecation")
	public static Boolean isMorning(Date date) {
		if (0 <= date.getHours() && date.getHours() < 12) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public static Boolean isAfternoon(Date date) {
		if (12 <= date.getHours() && date.getHours() < 18) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public static Boolean isEvening(Date date) {
		if (18 <= date.getHours() && date.getHours() < 24) {
			return true;
		}
		return false;
	}
}
