package org.tsys.sbb.dto;

import org.tsys.sbb.model.Board;

public class ScheduleDto {
    private String departure;
    private String arrival;
    private String name;
    private String from;
    private String to;
    private String status;

    public ScheduleDto() {
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static ScheduleDto getScheduleDto(Board board) {

        ScheduleDto dto = new ScheduleDto();
        dto.setName(board.getName());
        dto.setFrom(board.getName());

        return dto;
    }
}
