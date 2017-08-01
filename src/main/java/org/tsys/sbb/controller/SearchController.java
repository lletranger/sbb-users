package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tsys.sbb.model.Board;
import org.tsys.sbb.model.Delay;
import org.tsys.sbb.model.Station;
import org.tsys.sbb.model.Train;
import org.tsys.sbb.service.*;
import org.tsys.sbb.util.DistanceAndTimeUtil;

import java.util.*;

@Controller
public class SearchController {
    private BoardService boardService;
    private StationService stationService;
    private TrainService trainService;
    private DelayService delayService;


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


    @RequestMapping(value = "search")
    public String searсh(Model model) {
        model.addAttribute("stations", stationService.getAllStations());
        return "search";
    }

    @RequestMapping("searchboards")
    public String searсhOpen(@RequestParam("id1") int id1, @RequestParam("id2") int id2, Model model) {
        List<Board> list;
        if (id2 == 0) {
            list = boardService.findBoardsByFrom(id1);
        } else if (id1 == 0) {
            list = boardService.findBoardsByTo(id2);
        } else {
            list = boardService.findBoardsByFromAndTo(id1, id2);
        }

        Map<Integer, String> depTime = new HashMap<>();
        Map<Integer, String> estArrTime = new HashMap<>();
        Map<Integer, String> delayTime = new HashMap<>();
        Map<Integer, String> journeyTime = new HashMap<>();
        Map<Integer, String> totalTime = new HashMap<>();
        Map<Integer, Integer> dist = new HashMap<>();
        Map<Integer, Integer> avSpeed = new HashMap<>();



        for (Board b : list) {

            Station from = stationService.getStationById(b.getFrom_id());
            Station to = stationService.getStationById(b.getTo_id());
            Train t = trainService.getTrainById(b.getTrain_id());
            avSpeed.put(b.getBoard_id(), t.getSpeed_percents()*45/100);
            String departure = DistanceAndTimeUtil.getStringDate(b.getDeparture());
            depTime.put(b.getBoard_id(), departure);

            int distance = (int) DistanceAndTimeUtil.getDistance(from, to);
            dist.put(b.getBoard_id(), distance);

            String time = DistanceAndTimeUtil.getJourneyTime(distance, t);
            journeyTime.put(b.getBoard_id(), time);

            Date arrival = new Date(b.getDeparture().getTime() + DistanceAndTimeUtil.getTime(time));
            estArrTime.put(b.getBoard_id(), DistanceAndTimeUtil.getStringDate(arrival));

            if (delayService.getDelayByBoardId(b.getBoard_id()) != null) {
                Delay d = delayService.getDelayByBoardId(b.getBoard_id());
                String delay = DistanceAndTimeUtil.getStringDelay(d.getDelay_time());
                delayTime.put(b.getBoard_id(), delay);
                arrival = new Date(b.getDeparture().getTime() + DistanceAndTimeUtil.getTime(time) + DistanceAndTimeUtil.getTime(delay));

            }

            totalTime.put(b.getBoard_id(), DistanceAndTimeUtil.getStringDate(arrival));
        }

        model.addAttribute("trains", trainService.getAllTrains());
        model.addAttribute("board", new Board());
        model.addAttribute("departures", depTime);
        model.addAttribute("arrivals", estArrTime);
        model.addAttribute("distance", dist);
        model.addAttribute("avSpeed", avSpeed);

        model.addAttribute("delays", delayTime);
        model.addAttribute("journeyTime", journeyTime);
        model.addAttribute("totalTime", totalTime);

        model.addAttribute("boardList", list);
        model.addAttribute("stations", stationService.getAllStations());
        return "search";
    }
}