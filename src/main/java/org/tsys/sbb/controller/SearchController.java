package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tsys.sbb.dto.BoardDto;
import org.tsys.sbb.model.*;
import org.tsys.sbb.service.*;
import org.tsys.sbb.util.DistanceAndTimeUtil;

import java.util.*;

@Controller
public class SearchController {
    private BoardService boardService;
    private StationService stationService;
    private TrainService trainService;
    private DelayService delayService;
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

        List<Board> searchResult;
        if (id2 == 0) {
            searchResult = boardService.findBoardsByFrom(id1);
        } else if (id1 == 0) {
            searchResult = boardService.findBoardsByTo(id2);
        } else {
            searchResult = boardService.findBoardsByFromAndTo(id1, id2);
        }

        List<BoardDto> dtos = new ArrayList<>();

        for (Board board : searchResult) {

            BoardDto dto = new BoardDto();
            dto.setName(board.getName());

            List<Ticket> tickets = ticketService.findTicketsByBoardId(board.getBoard_id());
            Train train = trainService.getTrainById(board.getTrain_id());
            String departure = DistanceAndTimeUtil.getStringDate(board.getDeparture());
            //??? is it?))
            dto.setTicketsAvailable((tickets.size() < train.getSeats()) & (!DistanceAndTimeUtil.isTenMinsGap(departure)));
            dto.setDeparture(departure);
            dto.setAverageSpeed(train.getSpeed_percents() * 45 / 100);

            Station from = stationService.getStationById(board.getFrom_id());
            Station to = stationService.getStationById(board.getTo_id());
            dto.setFrom(from.getName());
            dto.setTo(to.getName());
            int distance = (int) DistanceAndTimeUtil.getDistance(from, to);
            dto.setDistance(distance);

            String journeyTime = DistanceAndTimeUtil.getJourneyTime(distance, train);
            dto.setJourneyTime(journeyTime);

            Date expectedArrival = new Date(board.getDeparture().getTime() + DistanceAndTimeUtil.getTime(journeyTime));
            dto.setExpectedArrival(DistanceAndTimeUtil.getStringDate(expectedArrival));

            List<Delay> delays = delayService.getDelayByBoardId(board.getBoard_id());
            if (!delays.isEmpty()) {
                Delay d = DistanceAndTimeUtil.getResultingDelay(delays);
                String delay = DistanceAndTimeUtil.getStringDelay(d.getDelay_time());
                expectedArrival = new Date(board.getDeparture().getTime()
                        + DistanceAndTimeUtil.getTime(DistanceAndTimeUtil.getJourneyTime(distance, train))
                        + DistanceAndTimeUtil.getTime(delay));
            }

            dto.setArrival(DistanceAndTimeUtil.getStringDate(expectedArrival));

            dtos.add(dto);
        }

        model.addAttribute("board", new Board());
        model.addAttribute("stations", stationService.getAllStations());
        model.addAttribute("dtos", dtos);

        return "search";
    }
}