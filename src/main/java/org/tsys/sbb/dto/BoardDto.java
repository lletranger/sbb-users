package org.tsys.sbb.dto;


import org.tsys.sbb.model.Board;
import org.tsys.sbb.util.DistanceAndTimeUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BoardDto {

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
    private String isArrived;
    private boolean ticketsAvailable;
    private int from_id;
    private int to_id;
    private int train_id;

    public BoardDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(int averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public String getJourneyTime() {
        return journeyTime;
    }

    public void setJourneyTime(String journeyTime) {
        this.journeyTime = journeyTime;
    }

    public String getExpectedArrival() {
        return expectedArrival;
    }

    public void setExpectedArrival(String expectedArrival) {
        this.expectedArrival = expectedArrival;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public int getSeatsLeft() {
        return seatsLeft;
    }

    public void setSeatsLeft(int seatsLeft) {
        this.seatsLeft = seatsLeft;
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

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public boolean isTicketsAvailable() {
        return ticketsAvailable;
    }

    public void setTicketsAvailable(boolean ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    public String getIsArrived() {
        return isArrived;
    }

    public void setIsArrived(String isArrived) {
        this.isArrived = isArrived;
    }

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