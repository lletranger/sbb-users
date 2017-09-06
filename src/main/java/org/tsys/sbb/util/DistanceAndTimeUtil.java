package org.tsys.sbb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tsys.sbb.model.Board;
import org.tsys.sbb.model.Delay;
import org.tsys.sbb.model.Station;
import org.tsys.sbb.model.Train;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DistanceAndTimeUtil {

    //absolute 100% speed of the train))
    final private static int speed = 45;

    private static final Logger logger = LoggerFactory.getLogger(DistanceAndTimeUtil.class);

    /**
     * Returns distance in km between two stations
     *
     * @param from {@link Station}
     * @param to {@link Station}
     * @return {@link Double} distance in km
     */
    public static double getDistance(Station from, Station to) {
        return Math.hypot(from.getX() - to.getX(), from.getY() - to.getY());
    }

    /**
     * Calculates and returns time in a string format days hours minutes
     * to travel particular distance on a given {@link Train}
     *
     * @param distance integer
     * @param train {@link Train}
     * @return string representation of journey time
     */
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


    /**
     * Returns a milliseconds for a given time in a format days hours minutes
     *
     * @param time {@link String}
     * @return {@link Long} milliseconds
     */
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

        return 1000*60*(days*24*60 + hours*60 + mins);
    }


    /**
     * Converts a given {@link Date} to {@link String} with pattern hours:minutes
     *
     * @param date {@link Date}
     * @return {@link String} time
     */
    public static String getStringDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }


    /**
     * Converts a given {@link Date} to {@link String} with pattern days hours minutes
     *
     * @param date {@link Date}
     * @return {@link String} time
     */
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

    /**
     * Converts a given {@link Date} to {@link String} with pattern day month year
     *
     * @param date {@link Date}
     * @return {@link String} time
     */
    public static String getStringBirthDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM YYYY", Locale.ENGLISH);
        return sdf.format(date);
    }

    /**
     * Converts a given {@link Date} to {@link String} with pattern day/month/year
     *
     * @param date {@link Date}
     * @return {@link String} time
     */
    public static String getStringBirthDate2(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY", Locale.ENGLISH);
        return sdf.format(date);
    }

    /**
     * Converts a given {@link String} to {@link Date} from pattern day/month/year
     *
     * @param bd {@link String}
     * @return {@link Date} birth date
     */
    public static Date getBirthDateFromString(String bd) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = sdf.parse(bd);
        } catch (ParseException e){
            logger.info("Unable to parse new passenger birth date");
        }
        return date;
    }

    /**
     * Returns a milliseconds for a given time in a format HH:MM
     *
     * @param time String
     * @return {@link Long} milliseconds
     */
    public static long getDtoTime(String time) {
        String[] hms = time.split(":");
        int hourMins = Integer.valueOf(hms[0])*60;
        int mins = Integer.valueOf(hms[1]);
        return (hourMins + mins)*60*1000;
    }

    /**
     * Checks if a given {@link Board} in 10 minutes away from its departing
     *
     * @param departure String
     * @return true or false
     */
    public static boolean isTenMinsGap(String departure) {
        return getDtoTime(departure) - getDtoTime(getStringDate(new Date())) <= 600*1000;
    }


    /**
     * Checks if a given {@link Board} is already arrived to its destination
     *
     * @param departure {@link Date}
     * @param arrival {@link Date}
     * @return true or false
     */
    public static boolean isAlreadyArrived(Date departure, Date arrival) {

        long d = getDtoTime(getStringDate(arrival)) - getDtoTime(getStringDate(new Date()));
        long e = getDtoTime(getStringDate(arrival)) - getDtoTime(getStringDate(departure));
        return !(d > 0 || e < 0);
    }


    /**
     * Gets a resulting {@link Delay} for a given
     * {@link ArrayList} of {@link Delay}s
     *
     * @param delays {@link ArrayList} of {@link Delay}s
     * @return a single {@link Delay}
     */
    public static Delay getResultingDelay(List<Delay> delays) {

        long longDelay = delays.stream()
                .map(delay -> getStringDate(delay.getDelay_time()))
                .mapToLong(DistanceAndTimeUtil::getDtoTime)
                .sum();

        Delay result = new Delay();
        //GMT+3 => -3
        result.setDelay_time(new Date(longDelay - 3*3600*1000));
        return result;
    }

    /**
     * Checks if a given {@link Board} is already on its way
     * i.e. already arrived or departed
     *
     * @param expected {@link Date}
     * @return true or false
     */
    public static boolean isDepartedOrArrived(Date expected) {
        return getDtoTime(getStringDate(expected)) - getDtoTime(getStringDate(new Date())) <= 0;
    }

    /**
     * Checks if a given {@link Board} in 5 minutes away from its departing or arriving
     *
     * @param expected {@link Date}
     * @return true or false
     */
    public static boolean isDepartingOrArriving(Date expected) {
        return getDtoTime(getStringDate(expected)) - getDtoTime(getStringDate(new Date())) <= 300*1000;
    }
}