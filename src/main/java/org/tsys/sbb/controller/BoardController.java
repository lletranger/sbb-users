package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tsys.sbb.dto.BoardDto;
import org.tsys.sbb.dto.PassengerDto;
import org.tsys.sbb.model.*;
import org.tsys.sbb.service.*;
import org.tsys.sbb.util.DistanceAndTimeUtil;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class BoardController {
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

    @RequestMapping(value = "boards", method = RequestMethod.GET)
    public String getAllBoards(Model model, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || !user.getRole().equals("admin")) {
            return "notpass";
        }

        List<Board> boards = boardService.getAllBoards();

        Map<Integer, String> depTime = new HashMap<>();
        Map<Integer, String> estArrTime = new HashMap<>();
        Map<Integer, String> delayTime = new HashMap<>();
        Map<Integer, String> journeyTime = new HashMap<>();
        Map<Integer, String> totalTime = new HashMap<>();

        for (Board b : boards) {

            Station from = stationService.getStationById(b.getFrom_id());
            Station to = stationService.getStationById(b.getTo_id());
            Train t = trainService.getTrainById(b.getTrain_id());

            String departure = DistanceAndTimeUtil.getStringDate(b.getDeparture());
            depTime.put(b.getBoard_id(), departure);

            int distance = (int) DistanceAndTimeUtil.getDistance(from, to);
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
        model.addAttribute("boards", boards);
        model.addAttribute("departures", depTime);
        model.addAttribute("arrivals", estArrTime);
        model.addAttribute("delays", delayTime);
        model.addAttribute("journeyTime", journeyTime);
        model.addAttribute("totalTime", totalTime);
        model.addAttribute("stations", stationService.getAllStations());
        return "boards";
    }

    @RequestMapping("boarddata/{id}")
    public String boardData(@PathVariable("id") int id, Model model, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || !user.getRole().equals("admin")) {
            return "notpass";
        }

        Board b = boardService.findBoardById(id);
        List<Ticket> tickets = ticketService.findTicketsByBoardId(id);
        Station from = stationService.getStationById(b.getFrom_id());
        Station to = stationService.getStationById(b.getTo_id());
        Train tr = trainService.getTrainById(b.getTrain_id());

        List<PassengerDto> passengers = new ArrayList<>();
        for (Ticket t : tickets) {
            passengers.add(PassengerDto.getDtoFromPassenger(passengerService.getPassById(t.getPassenger_id())));
        }

        logger.info("Total number of passengers for board " + b.getName() + " is " + passengers.size());

        model.addAttribute("board", b);
        model.addAttribute("onBoard", passengers);
        model.addAttribute("from", from.getName());
        model.addAttribute("to", to.getName());
        int distance = (int) DistanceAndTimeUtil.getDistance(from, to);
        model.addAttribute("distance", distance);
        String journeyTime = DistanceAndTimeUtil.getJourneyTime(distance, tr);
        model.addAttribute("time", journeyTime);
        model.addAttribute("speed", (tr.getSpeed_percents() * 45 / 100));
        return "boarddata";
    }

    @Transactional
    @RequestMapping(value = "boards/add", method = RequestMethod.POST)
    public String addBoard(@ModelAttribute("boardDto") BoardDto boardDto) {
        if (boardDto.getFrom_id() == boardDto.getTo_id()) {
            return "redirect:/tofromexception";
        }
        Board board = BoardDto.getBoardFromDto(boardDto);
        boardService.addBoard(board);
        return "redirect:/boards";
    }
}