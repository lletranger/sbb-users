package org.tsys.sbb.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

public @Data class StationsDto implements Serializable {

    private List<StationDto> stations;

    public StationsDto(List<StationDto> stations) {
        this.stations = stations;
    }
}
