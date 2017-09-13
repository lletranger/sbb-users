package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tsys.sbb.dto.ScheduleDto;
import org.tsys.sbb.dto.StationsDto;
import org.tsys.sbb.service.ScheduleService;
import org.tsys.sbb.service.StationService;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
public class ScheduleController {

    private StationService stationService;
    private ScheduleService scheduleService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @Autowired
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @RequestMapping(value = "/schedule/stations", produces = APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public StationsDto getStations() {
        LOGGER.info("Sending stations in JSON");

        return stationService.getAllStationsDto();
    }

    @RequestMapping(value = "/schedule/station/{id}", produces = APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public ScheduleDto getSchedule(@PathVariable("id") int id) {
        LOGGER.info("Sending schedule in JSON");

        return scheduleService.getSchedule(id);
    }
}