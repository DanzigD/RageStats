package com.example.danielandersson.ragestats;

import android.util.SparseIntArray;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by danielandersson on 2017-07-18.
 */

public final class Utils {

    public static String formatToDayOfWeek(long timeStamp) {
        Date date = new Date(timeStamp*1000);
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

    }

    public static String formatMonth(long timeStamp) {
        Date date = new Date(timeStamp*1000);
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

    }

    public static String formatDigitalTime(long timeStamp) {
        Date date = new Date(timeStamp);
        final int minutes = date.getMinutes();
        String timeString;


        String divider = ":";
        if (minutes < 10) {
            divider = ":0";
        }
        timeString = date.getHours() + divider + minutes;
        return timeString;

    }

    public static boolean isTimestampToday(Long timestamp) {
        long epochInMillis = timestamp * 1000;
        Calendar now = Calendar.getInstance();
        Calendar timeToCheck = Calendar.getInstance();
        timeToCheck.setTimeInMillis(epochInMillis);

        if (now.get(Calendar.YEAR) == timeToCheck.get(Calendar.YEAR)) {
            if (now.get(Calendar.DAY_OF_YEAR) == timeToCheck.get(Calendar.DAY_OF_YEAR)) {
                return true;
            }
        }
        return false;
    }

    public static long getDayStartTimestamp(Calendar now) {
        now.set(Calendar.HOUR_OF_DAY, 0);

        return now.getTimeInMillis() / 1000;
    }
    public static long getDayEndTimestamp(Calendar now) {
        now.set(Calendar.HOUR_OF_DAY, 23);
        now.set(Calendar.MINUTE, 59);
        return now.getTimeInMillis() / 1000;
    }

    public static long getMonthStartTimestamp(Calendar now) {
        now.set(Calendar.DAY_OF_MONTH, 0);
        return now.getTimeInMillis() / 1000;
    }
    public static long getNextMonthStartTimestamp(Calendar now) {
        now.set(Calendar.DAY_OF_MONTH, 0);
        now.add(Calendar.MONTH, 1);
        return now.getTimeInMillis() / 1000;
    }


    public static String parseSparseArrayToString(SparseIntArray dataMap) {
        StringBuilder dataStringBuilder = new StringBuilder();


        for (int i = 0; i < dataMap.size(); i++) {
            dataStringBuilder.append(dataMap.keyAt(i)).append(" ");
            dataStringBuilder.append(dataMap.valueAt(i)).append(" ");
        }
        return dataStringBuilder.toString();
    }

    public static SparseIntArray parseStringToSparseArray(String data) {
        final SparseIntArray intArray = new SparseIntArray();
        final String[] split = data.split(" ");
        if (split.length > 1) {
            for (int i = 1; i < split.length; i += 2) {
                final int mapKey = Integer.parseInt(split[i - 1]);
                final int mapValue = Integer.parseInt(split[i]);
                intArray.put(mapKey, mapValue);
            }
        }

        return intArray;
    }


    public static List<String> hashtagFinder(String comment) {
        Pattern MY_PATTERN = Pattern.compile("#(\\S+)");
        Matcher mat = MY_PATTERN.matcher(comment);
        List<String> strs = new ArrayList<String>();
        while (mat.find()) {
            //System.out.println(mat.group(1));
            strs.add(mat.group(1));
        }

        return strs;
    }

    public static long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    public static int getThisHour(long currentTimestamp) {
        DateFormat dateFormat = SimpleDateFormat.getDateInstance();
        Date date = new Date(currentTimestamp);
        String dateString = dateFormat.format(date);
        return date.getHours();
    }
}
