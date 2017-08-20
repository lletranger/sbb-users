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
import org.tsys.sbb.model.User;
import org.tsys.sbb.service.StationService;

import javax.servlet.http.HttpSession;

@Controller
public class StationController {

    private StationService stationService;

    private static final Logger logger = LoggerFactory.getLogger(StationController.class);

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @RequestMapping(value = "stations")
    public String getAllStations(Model model, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || !user.getRole().equals("admin")) {
            return "notpass";
        }

        model.addAttribute("station", new Station());
        model.addAttribute("allStations", stationService.getAllStations());
        logger.info("Loading all stations to the stations page");
        return "stations";
    }

    @Transactional
    @RequestMapping(value = "stations/add", method = RequestMethod.POST)
    public String addStation(@ModelAttribute("station") Station station, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || !user.getRole().equals("admin")) {
            return "notpass";
        }

        if (stationService.getAllStationsNames().stream()
                .anyMatch(station.getName()::equalsIgnoreCase)) {
            session.setAttribute("existingStation", station.getName());
            logger.info("Can't create station, already exists with the name " + station.getName());
            return "snexception";
        }

        stationService.addStation(station);
        logger.info("Creating new station " + station);
        return "redirect:/stations";
    }
}