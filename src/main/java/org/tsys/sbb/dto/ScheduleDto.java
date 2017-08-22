package org.tsys.sbb.dto;

import lombok.Data;

import java.util.List;

public @Data class ScheduleDto {

    String name;
    List<BoardDto> from;
    List<BoardDto> to;
}
