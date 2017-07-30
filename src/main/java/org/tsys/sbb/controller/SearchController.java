package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tsys.sbb.model.Board;
import org.tsys.sbb.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    private BoardService boardService;
    private StationService stationService;
    private TrainService trainService;
    private DelayService delayService;
    private PassengerService passengerService;
    private TicketService ticketService;

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    @Autowired
    public void setDelayService(DelayService delayService) {
        this.delayService = delayService;
    }

    @Autowired
    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(value = "search")
    public String searсh(Model model) {
        model.addAttribute("stations", stationService.getAllStations());
        return "search";
    }

    @RequestMapping("searchboards")
    public String searсhOpen(@RequestParam("id1") int id1, @RequestParam("id2") int id2, Model model) {
        if (id2 == 0) {
            model.addAttribute("boardList", boardService.findBoardsByFrom(id1));
        } else if (id1 == 0) {
            model.addAttribute("boardList", boardService.findBoardsByTo(id2));
        } else {
            model.addAttribute("boardList", boardService.findBoardsByFromAndTo(id1, id2));
        }
        model.addAttribute("stations", stationService.getAllStations());
        return "search";
    }
}