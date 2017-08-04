package org.tsys.sbb.dto;

public class TicketDto {
    private int id;
    private String boardName;
    private String from;
    private String to;
    private String departure;
    private String passName;
    private String passSurname;
    private String passBirthDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
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

    public String getPassName() {
        return passName;
    }

    public void setPassName(String passName) {
        this.passName = passName;
    }

    public String getPassSurname() {
        return passSurname;
    }

    public void setPassSurname(String passSurname) {
        this.passSurname = passSurname;
    }

    public String getPassBirthDate() {
        return passBirthDate;
    }

    public void setPassBirthDate(String passBirthDate) {
        this.passBirthDate = passBirthDate;
    }

    public TicketDto(int id, String boardName, String from, String to, String departure, String passName, String passSurname, String passBirthDate) {
        this.id = id;
        this.boardName = boardName;
        this.from = from;
        this.to = to;
        this.departure = departure;
        this.passName = passName;
        this.passSurname = passSurname;
        this.passBirthDate = passBirthDate;
    }
}
