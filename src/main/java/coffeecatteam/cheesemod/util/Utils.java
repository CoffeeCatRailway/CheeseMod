package coffeecatteam.cheesemod.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import coffeecatteam.cheesemod.Reference;

public class Utils {

	private static Logger logger;
	
	/**
	 * Check if it is Christmas time!
	 */
	public static final boolean IS_CHRISTMAS = isChristmas();

	public static Logger getLogger() {
		if (logger == null) {
			logger = LogManager.getFormatterLogger(Reference.MODID.replace("c", "C").replace("m", "M"));
		}
		return logger;
	}

	public static Logger getLogger(String name) {
		if (logger == null) {
			logger = LogManager.getFormatterLogger(Reference.MODID.replace("c", "C").replace("m", "M")+"_"+name);
		}
		return logger;
	}

	private static boolean isChristmas() {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);

		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		boolean inRange = (day >= 20 && day <= 31);
		
		return (month == Calendar.DECEMBER && inRange);
	}
}
