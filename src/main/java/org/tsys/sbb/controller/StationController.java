package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tsys.sbb.model.Station;
import org.tsys.sbb.service.StationService;

import javax.servlet.http.HttpSession;

@Controller
public class StationController {

    private StationService stationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(StationController.class);

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @RequestMapping(value = "/admin/stations")
    public String getAllStations(Model model) {

        model.addAttribute("station", new Station());
        model.addAttribute("allStations", stationService.getAllStations());
        LOGGER.info("Loading all stations to the stations page");

        return "stations";
    }

    @Transactional
    @RequestMapping(value = "/admin/stations/add", method = RequestMethod.POST)
    public String addStation(@ModelAttribute("station") Station station, HttpSession session) {

        if (stationService.isExist(station.getName())) {

            session.setAttribute("existingStation", station.getName());
            return "messages/stationExists";
        }

        stationService.addStation(station);
        LOGGER.info("New station's created {}", station.getName());

        return "redirect:/admin/stations";
    }
}