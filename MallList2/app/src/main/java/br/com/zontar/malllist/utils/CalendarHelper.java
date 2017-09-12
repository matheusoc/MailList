package br.com.zontar.malllist.utils;

import android.content.Context;

import java.util.Calendar;

import br.com.zontar.malllist.R;

/**
 * Created by matheusoliveira on 12/09/2017.
 */

public abstract class CalendarHelper {

    public static String getMonthName (Context context, Integer number) {
        Integer month;

        if (number == null) {
            Calendar calendar = Calendar.getInstance();
            month = calendar.get(Calendar.MONTH);
        } else {
            month = number;
        }

        switch (month) {
            case Calendar.JANUARY:
                return context.getString(R.string.january);

            case Calendar.FEBRUARY:
                return context.getString(R.string.february);

            case Calendar.MARCH:
                return context.getString(R.string.march);

            case Calendar.APRIL:
                return context.getString(R.string.april);

            case Calendar.MAY:
                return context.getString(R.string.may);

            case Calendar.JUNE:
                return context.getString(R.string.june);

            case Calendar.JULY:
                return context.getString(R.string.july);

            case Calendar.AUGUST:
                return context.getString(R.string.august);

            case Calendar.SEPTEMBER:
                return context.getString(R.string.september);

            case Calendar.OCTOBER:
                return context.getString(R.string.october);

            case Calendar.NOVEMBER:
                return context.getString(R.string.november);

            case Calendar.DECEMBER:
                return context.getString(R.string.december);

            default:
                return null;
        }
    }

}
