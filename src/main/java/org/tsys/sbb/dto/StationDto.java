package org.tsys.sbb.dto;

import lombok.Data;

import java.io.Serializable;

public @Data class StationDto implements Serializable {

    private int id;
    private String name;

    public StationDto(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
