package org.tsys.sbb.dto;

import lombok.Data;
import org.tsys.sbb.model.*;
import org.tsys.sbb.util.DistanceAndTimeUtil;

import java.util.*;

public @Data class BoardDto {

    private int id;
    private String name;
    private String from;
    private String to;
    private String departure;
    private int distance;
    private int averageSpeed;
    private String journeyTime;
    private String expectedArrival;
    private String delay;
    private String arrival;
    private int seatsLeft;
    private String canAddDelay;
    private boolean ticketsAvailable;
    private int from_id;
    private int to_id;
    private int train_id;
    private String status;

    public static Board getBoardFromDto(BoardDto boardDto) {
        Board board = new Board();
        board.setName(boardDto.getName());
        board.setTrain_id(boardDto.getTrain_id());
        board.setFrom_id(boardDto.getFrom_id());
        board.setTo_id(boardDto.getTo_id());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date(DistanceAndTimeUtil.getDtoTime(boardDto.getDeparture())));
        calendar.add(Calendar.HOUR, -3);
        Date departure = calendar.getTime();
        board.setDeparture(departure);
        return board;
    }
}