package org.tsys.sbb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tsys.sbb.model.Station;
import org.tsys.sbb.model.Train;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    public static long getDelayTime(Date delay) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String sDelay = sdf.format(delay);
        String[] hms = sDelay.split(":");
        return 1000 * 60 * (Integer.valueOf(hms[0]) * 60 + Integer.valueOf(hms[1]));
    }
}
