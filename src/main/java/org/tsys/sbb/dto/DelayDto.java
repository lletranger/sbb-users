package org.tsys.sbb.dto;

import org.tsys.sbb.model.Delay;
import org.tsys.sbb.util.DistanceAndTimeUtil;

import java.util.Date;

public class DelayDto {
    private int board_id;
    private String delay;

    public DelayDto() {
    }

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
        long longDelay = DistanceAndTimeUtil.getDtoTime(delayDto.getDelay());
        //GMT+3 => -3
        Date date = new Date(longDelay - 3*60*60*1000);
        delay.setDelay_time(date);
        return delay;
    }
}
