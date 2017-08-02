package org.tsys.sbb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tsys.sbb.model.Station;
import org.tsys.sbb.model.User;
import org.tsys.sbb.service.StationService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StationController {

    private StationService stationService;

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @RequestMapping(value = "stations", method = RequestMethod.GET)
    public String getAllStations(Model model, HttpSession session) {
        User user = (User)session.getAttribute("sessionUser");
        if(!user.getRole().equals("admin")){
            return "notpass";
        }
        model.addAttribute("station", new Station());
        model.addAttribute("allStations", stationService.getAllStations());
        return "stations";
    }

    @RequestMapping(value = "stations/add", method = RequestMethod.POST)
    public String addStation(@ModelAttribute("station") Station station, HttpSession session) throws Exception {
        User user = (User)session.getAttribute("sessionUser");
        if(!user.getRole().equals("admin")){
            return "notpass";
        }
        List<Station> list = stationService.getAllStations();

        for (Station s : list) {
            if (station.getName().toLowerCase().equals(s.getName().toLowerCase())) {
                session.setAttribute("existingStation", s.getName());
                return "redirect:/snexception";
            }
        }
        stationService.addStation(station);
        return "redirect:/stations";
    }
}