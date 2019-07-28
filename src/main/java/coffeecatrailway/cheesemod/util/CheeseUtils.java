package coffeecatrailway.cheesemod.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author CoffeeCatRailway
 * Created: 22/07/2019
 */
public class CheeseUtils {

    public static final boolean IS_CHRISTMAS = isChristmas();

    private static boolean isChristmas() {
        boolean ret = false;
        for (int date = 20; date <= 31; date++)
            if (isDate(Calendar.DECEMBER, date) && !ret)
                ret = true;
        return ret;
    }

    public static boolean isDate(int month, int day) {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        int curentMonth = cal.get(Calendar.MONTH);
        int curentDay = cal.get(Calendar.DAY_OF_MONTH);
        boolean inRange = curentDay == day;

        return (curentMonth == month && inRange);
    }
}
