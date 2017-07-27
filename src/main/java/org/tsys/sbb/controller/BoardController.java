package org.tsys.sbb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tsys.sbb.model.Board;
import org.tsys.sbb.model.Delay;
import org.tsys.sbb.model.Station;
import org.tsys.sbb.model.Train;
import org.tsys.sbb.service.BoardService;
import org.tsys.sbb.service.DelayService;
import org.tsys.sbb.service.StationService;
import org.tsys.sbb.service.TrainService;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class BoardController
{
    private BoardService boardService;
    private StationService stationService;
    private TrainService trainService;
    private DelayService delayService;

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    public void setStationService(StationService stationService) { this.stationService = stationService; }

    @Autowired
    public void setTrainService(TrainService trainService) { this.trainService = trainService; }

    @Autowired
    public void setDelayService(DelayService delayService) { this.delayService = delayService; }

    @RequestMapping(value = "boards", method = RequestMethod.GET)
    public String getAllBoards(Model model) {

        //absolute 100% speed of the train))
        final int speed = 45;

        List<Board> boards = boardService.getAllBoards();
        Map<Integer, String> arrivals = new HashMap<>();
        Map<Integer, String> departures = new HashMap<>();
        Map<Integer, String> delays = new HashMap<>();

        for(Board b : boards) {
            Station from = stationService.getStationById(b.getFrom_id());
            Station to = stationService.getStationById(b.getTo_id());
            Train t = trainService.getTrainById(b.getTrain_id());
            SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            String departure = sdf.format(b.getDeparture());
            departures.put(b.getBoard_id(), departure);

            double distance = Math.sqrt((from.getX()-to.getX())*(from.getX()-to.getX()) + (from.getY()-to.getY())*(from.getY()-to.getY()));
            int time = (int)(3600*1000*distance*100/(speed*t.getSpeed_percents()*1.0));
            Date arrival = new Date(b.getDeparture().getTime() + time);

            if(delayService.getDelayByBoardId(b.getBoard_id()) != null) {
                Delay d = delayService.getDelayByBoardId(b.getBoard_id());
                String delay = sdf.format(d.getDelay_time());
                delays.put(b.getBoard_id(), delay);
                arrival = new Date(b.getDeparture().getTime() + time + d.getDelay_time().getTime());

            }
            String simpleDate = sdf.format(arrival);
            arrivals.put(b.getBoard_id(), simpleDate);
        }

        model.addAttribute("boards", boards);
        model.addAttribute("departures", departures);
        model.addAttribute("arrivals", arrivals);
        model.addAttribute("delays", delays);
        model.addAttribute("stations", stationService.getAllStations());
        return "boards";
    }
}
