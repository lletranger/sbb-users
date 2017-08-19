package org.tsys.sbb.service;

import org.tsys.sbb.dto.ScheduleDto;

import java.util.List;

public interface ScheduleService {
    List<ScheduleDto> getSchedule(int id);
}
