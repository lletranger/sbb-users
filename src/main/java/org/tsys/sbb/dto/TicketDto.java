package org.tsys.sbb.dto;

import lombok.Data;

public @Data class TicketDto {
    private int id;
    private String boardName;
    private String from;
    private String to;
    private String departure;
    private String passName;
    private String passSurname;
    private String passBirthDate;
}
