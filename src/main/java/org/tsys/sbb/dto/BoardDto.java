package org.tsys.sbb.dto;


import org.tsys.sbb.model.Board;
import org.tsys.sbb.util.DistanceAndTimeUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BoardDto {

    private String name;

    private int train_id;

    private int from_id;

    private int to_id;

    private String departure;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }

    public int getTo_id() {
        return to_id;
    }

    public void setTo_id(int to_id) {
        this.to_id = to_id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public static Board getBoardFromDto(BoardDto boardDto) {
        Board board = new Board();
        board.setName(boardDto.getName());
        board.setTrain_id(boardDto.getTrain_id());
        board.setFrom_id(boardDto.getFrom_id());
        board.setTo_id(boardDto.getTo_id());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date(DistanceAndTimeUtil.getTime(boardDto.getDeparture())));
        calendar.add(Calendar.HOUR, -3);
        Date dep = calendar.getTime();
        board.setDeparture(dep);
        return board;
    }
}