package org.tsys.sbb.util;

import org.junit.Test;
import org.tsys.sbb.model.Delay;
import org.tsys.sbb.model.Station;
import org.tsys.sbb.model.Train;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class DistanceAndTimeUtilTest {

    @Test
    public void getDistance() throws Exception {
        //given
        Station a = new Station();
        a.setName("A");
        a.setX(0);
        a.setY(0);

        Station b = new Station();
        b.setName("B");
        b.setX(300);
        b.setY(400);

        //run
        int distance = (int)DistanceAndTimeUtil.getDistance(a, b);

        //assert
        assertEquals(distance, 500);
    }

    @Test
    public void getJourneyTime() throws Exception {
        //given
        Station a = new Station();
        a.setName("A");
        a.setX(0);
        a.setY(0);

        Station b = new Station();
        b.setName("B");
        b.setX(300);
        b.setY(400);

        Train t = new Train();
        t.setSpeed_percents(100);

        //run
        String jt = DistanceAndTimeUtil.getJourneyTime((int)DistanceAndTimeUtil.getDistance(a, b), t);

        //assert
        assertEquals(jt, "11h 06m");
    }

    @Test
    public void getTime() throws Exception {
        //given
        String someTime = "2d 12h 34m";

        //run
        long time = DistanceAndTimeUtil.getTime(someTime);

        //assert
        assertEquals(time, 218040000);
    }

    @Test
    public void getStringDate() throws Exception {
        //given
        Date date = new Date((12*60 + 34)*60*1000 - 3*60*60*1000);

        //run
        String stringDate = DistanceAndTimeUtil.getStringDate(date);

        //assert
        assertEquals(stringDate, "12:34");

    }

    @Test
    public void getStringDelay() throws Exception {
        //given
        Date date = new Date((1*24*60 + 12*60 + 34)*60*1000 - 3*60*60*1000);
        String expected = "1d 12h 34m";

        //run
        String delay = DistanceAndTimeUtil.getStringDelay(date);

        //assert
        assertEquals(expected, delay);
    }

    @Test
    public void getStringBirthDate() throws Exception {
        //given
        Date date =  new GregorianCalendar(1989, 1, 26).getTime();

        //run

        String myBd = DistanceAndTimeUtil.getStringBirthDate(date);
        //assert
        assertEquals(myBd, "26 February 1989");
    }

    @Test
    public void getStringBirthDate2() throws Exception {
        //given
        Date date =  new GregorianCalendar(1989, 1, 26).getTime();

        //run
        String myBd = DistanceAndTimeUtil.getStringBirthDate2(date);

        //assert
        assertEquals(myBd, "26/02/1989");

    }

    @Test
    public void getBirthDateFromString() throws Exception {
        //given
        String s = "26/02/1989";
        Date date = new GregorianCalendar(1989,1,26).getTime();


        //run
        Date bd = DistanceAndTimeUtil.getBirthDateFromString(s);

        //assert
        assertEquals(bd, date);
    }

    @Test
    public void getDtoTime() throws Exception {
        //given
        String s = "00:13";

        //run
        long a = DistanceAndTimeUtil.getDtoTime(s);

        //assert
        assertEquals(a, 13*60*1000);
    }

    @Test
    public void isTenMinsGap() throws Exception {
        //given
        Date less = new Date(new Date().getTime() + 9*60*1000);
        Date more = new Date(new Date().getTime() + 11*60*1000);
        String less1 = DistanceAndTimeUtil.getStringDate(less);
        String more1 = DistanceAndTimeUtil.getStringDate(more);

        //run
        //assert
        assertTrue(DistanceAndTimeUtil.isTenMinsGap(less1));
        assertFalse(DistanceAndTimeUtil.isTenMinsGap(more1));
    }

    @Test
    public void isAlreadyArrived() throws Exception {
        //given

        //run

        //assert
    }

    @Test
    public void getResultingDelay() throws Exception {
        //given
        Delay delay1 = new Delay();
        delay1.setDelay_time(new Date(10*60*1000));

        Delay delay2 = new Delay();
        delay2.setDelay_time(new Date(8*60*1000));

        List<Delay> delays = new ArrayList<>();
        delays.add(delay1);
        delays.add(delay2);

        Date expected = new Date(18*60*1000 + 3*60*60*1000);

        //run
        Date result = DistanceAndTimeUtil.getResultingDelay(delays).getDelay_time();

        //assert
        assertEquals(result, expected);
    }

    @Test
    public void isDepartedOrArrived() throws Exception {
        //given
        Date date = new Date(new Date().getTime() - 11*60*1000);

        //run
        //assert
        assertTrue(DistanceAndTimeUtil.isDepartedOrArrived(date));
    }

    @Test
    public void isDepartingOrArriving() throws Exception {
        //given
        Date date = new Date(new Date().getTime() - 4*60*1000);

        //run
        //assert
        assertTrue(DistanceAndTimeUtil.isDepartingOrArriving(date));
    }
}