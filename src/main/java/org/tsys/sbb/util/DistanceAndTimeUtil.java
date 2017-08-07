package org.tsys.sbb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tsys.sbb.model.Delay;
import org.tsys.sbb.model.Station;
import org.tsys.sbb.model.Train;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class DistanceAndTimeUtil {

    //absolute 100% speed of the train))
    final private static int speed = 45;

    private static final Logger logger = LoggerFactory.getLogger(DistanceAndTimeUtil.class);

    public static double getDistance(Station from, Station to) {
        return Math.hypot(from.getX() - to.getX(), from.getY() - to.getY());
    }

    public static String getJourneyTime(int distance, Train train) {
        double realSpeed = (1.0 * speed * train.getSpeed_percents()) / 100;
        int hours = (int) (distance / realSpeed);
        int minutes = (int) (60 * (distance / realSpeed - hours));

        String realMinutes;
        if (minutes < 10) {
            realMinutes = "0" + String.valueOf(minutes);
        } else {
            realMinutes = String.valueOf(minutes);
        }
        if (hours >= 24) {
            return hours / 24 + "d " + hours % 24 + "h " + realMinutes + "m";
        }
        if (hours > 0) {
            return hours + "h " + realMinutes + "m";
        }

        return realMinutes + "m";
    }

    public static long getTime(String time) {
        int days = 0;
        int hours = 0;

        if (time.contains("d")) {
            days = Integer.valueOf(time.substring(0, time.indexOf("d")));
            time = time.substring(time.indexOf("d") + 1, time.length()).trim();
        }

        if (time.contains("h")) {
            hours = Integer.valueOf(time.substring(0, time.indexOf("h")));
            time = time.substring(time.indexOf("h") + 1, time.length()).trim();
        }

        int mins = Integer.valueOf(time.substring(0, time.indexOf("m")));

        return 1000 * 60 * (days * 24 * 60 + hours * 60 + mins);
    }

    public static String getStringDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }


    public static String getStringDelay(Date date) {
        long mins = date.getTime() / 60000;
        mins += 180;
        if (mins >= 1440) {
            return mins / 1440 + "d " + (mins % 1440) / 60 + "h " + (mins % 1440) % 60 + "m";
        } else if (mins >= 60) {
            return mins / 60 + "h " + mins % 60 + "m";
        } else {
            return mins + "m";
        }

    }

    public static String getStringBirthDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM YYYY", Locale.ENGLISH);
        return sdf.format(date);
    }

    public static String getStringBirthDate2(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY", Locale.ENGLISH);
        return sdf.format(date);
    }

    public static Date getBirthDateFromString(String bd) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = sdf.parse(bd);
        } catch (ParseException e){
            logger.info("Unable to parse new passenger birth date");
        }
        logger.info("Parsed new passenger birth date to" + date);
        return date;
    }

    public static long getDtoTime(String time) {
        String[] hms = time.split(":");
        logger.info("getting dtoTime for " + time + "and hours are " + Integer.valueOf(hms[0]));
        logger.info("getting dtoTime for " + time + "and minutes are " + Integer.valueOf(hms[1]));
        int hourMins = Integer.valueOf(hms[0])*60;
        int mins = Integer.valueOf(hms[1]);
        long all = (hourMins + mins)*60*1000;
        logger.info("getting dtoTime for " + time + "and it's " + all/1000 + " seconds");
        return all;
    }

    public static boolean isTenMinsGap(String departure) {
        Date now = new Date();
        String nows = getStringDate(now);
        return getDtoTime(departure) - getDtoTime(nows) <= 10 * 60 * 1000;
    }

    public static boolean isAlreadyArrived(Date departure, Date arrival) {

        Date now = new Date();

        String a = getStringDate(arrival);
        String b = getStringDate(new Date());
        String c = getStringDate(departure);

        logger.info("now in HH:MM " + b);
        logger.info("departure in HH:MM " + c);
        logger.info("arrival in HH:MM " + a);

        long d = getDtoTime(getStringDate(arrival)) - getDtoTime(getStringDate(now));
        long e = getDtoTime(getStringDate(arrival)) - getDtoTime(getStringDate(departure));

        logger.info("arr - now = " + d/1000 + " arr - dep = " + e/1000);

        return !(d > 0 || e < 0);
    }

    public static boolean passengerBirthDates(Date date, String string) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String sdate = sdf.format(date);
        logger.info("pass date " + sdate);
        logger.info("dto date " + string);
        return sdate.equals(string);
    }

    public static Delay getResultingDelay(List<Delay> delays) {
        long longDelay = 0;
        for (Delay delay : delays) {
            String stringDelay = getStringDate(delay.getDelay_time());
            longDelay += getDtoTime(stringDelay);
        }
        Delay result = new Delay();
        //GMT+3 => -3
        result.setDelay_time(new Date(longDelay - 3 * 60 * 60 * 1000));
        return result;
    }
}