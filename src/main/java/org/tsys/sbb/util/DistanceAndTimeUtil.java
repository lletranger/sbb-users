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

        return hours + ":" + realMinutes;
    }

    public static long getTime(String time) {
        String[] hms = time.split(":");
        return 1000 * 60 * (Integer.valueOf(hms[0]) * 60 + Integer.valueOf(hms[1]));
    }

    public static String getStringDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
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
