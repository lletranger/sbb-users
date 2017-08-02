package org.tsys.sbb.dto;

import org.tsys.sbb.model.Delay;
import org.tsys.sbb.util.DistanceAndTimeUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DelayDto {
    private int board_id;
    private String delay;

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public static Delay getDelayFromDto(DelayDto delayDto) {
        Delay delay = new Delay();
        delay.setBoard_id(delayDto.board_id);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date(DistanceAndTimeUtil.getDtoTime(delayDto.getDelay())));
        calendar.add(Calendar.HOUR, -3);
        Date del = calendar.getTime();
        delay.setDelay_time(del);
        return delay;

    }
}
