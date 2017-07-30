package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tsys.sbb.service.*;

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

    @RequestMapping("search")
    public String searсhOpen() {
        return "search";
    }

    @RequestMapping("search/{id1}&{id2}")
    public String searсh(@PathVariable("id1") int id1, @PathVariable("id2") int id2, Model model) {
        return "search";
    }
}