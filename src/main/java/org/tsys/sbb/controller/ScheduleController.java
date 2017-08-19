package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tsys.sbb.dto.ScheduleDto;
import org.tsys.sbb.service.BoardService;
import org.tsys.sbb.service.ScheduleService;
import org.tsys.sbb.service.StationService;

import java.util.List;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
public class ScheduleController {

    private StationService stationService;
    private ScheduleService scheduleService;

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.scheduleService = scheduleService;
    }

    @RequestMapping(value = "/schedule/stations", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Map<Integer, String> getStations() {
        return stationService.getStations();
    }

    @RequestMapping(value = "/schedule/station/{id}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<ScheduleDto> getSchedule(@PathVariable("id") int id) {
        return null;
    }
}
