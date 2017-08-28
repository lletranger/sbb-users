package org.tsys.sbb.service;

import org.tsys.sbb.dto.ScheduleDto;
import org.tsys.sbb.model.Board;
import org.tsys.sbb.model.Station;

public interface ScheduleService {

    /**
     * Gets all existing {@link Board}s from the database
     * for the specified {@link Station} by its {@link Station#station_id}
     *
     * @param id integer
     * @return a single {@link ScheduleDto}
     */
    ScheduleDto getSchedule(int id);
}
