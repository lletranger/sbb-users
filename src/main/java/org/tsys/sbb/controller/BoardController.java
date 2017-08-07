package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tsys.sbb.dto.BoardDto;
import org.tsys.sbb.dto.DelayDto;
import org.tsys.sbb.dto.PassengerDto;
import org.tsys.sbb.model.*;
import org.tsys.sbb.service.*;
import org.tsys.sbb.util.DistanceAndTimeUtil;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@SessionAttributes(types = User.class)
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
        List<BoardDto> dtos = new ArrayList<>();

        for (Board b : boards) {

            BoardDto boardDto = new BoardDto();
            boardDto.setId(b.getBoard_id());
            boardDto.setName(b.getName());

            Station from = stationService.getStationById(b.getFrom_id());
            Station to = stationService.getStationById(b.getTo_id());
            boardDto.setFrom(from.getName());
            boardDto.setTo(to.getName());

            Train t = trainService.getTrainById(b.getTrain_id());
            boardDto.setDeparture(DistanceAndTimeUtil.getStringDate(b.getDeparture()));

            int distance = (int) DistanceAndTimeUtil.getDistance(from, to);
            boardDto.setDistance(distance);

            boardDto.setJourneyTime(DistanceAndTimeUtil.getJourneyTime(distance, t));

            Date arrival = new Date(b.getDeparture().getTime() + DistanceAndTimeUtil.getTime(DistanceAndTimeUtil.getJourneyTime(distance, t)));
            boardDto.setExpectedArrival(DistanceAndTimeUtil.getStringDate(arrival));

//            boardDto.setIsArrived(DistanceAndTimeUtil.isAlreadyArrived(b.getDeparture(), arrival) ? "true" : "false");

            List<Delay> delays = delayService.getDelayByBoardId(b.getBoard_id());
            if (!delays.isEmpty()) {
                Delay d = DistanceAndTimeUtil.getResultingDelay(delays);
                String delay = DistanceAndTimeUtil.getStringDelay(d.getDelay_time());
                boardDto.setDelay(delay);
                arrival = new Date(b.getDeparture().getTime()
                        + DistanceAndTimeUtil.getTime(DistanceAndTimeUtil.getJourneyTime(distance, t))
                        + DistanceAndTimeUtil.getTime(delay));
            }

            boardDto.setArrival(DistanceAndTimeUtil.getStringDate(arrival));
            boardDto.setIsArrived(DistanceAndTimeUtil.isAlreadyArrived(b.getDeparture(), arrival) ? "true" : "false");

            dtos.add(boardDto);
        }

        model.addAttribute("boardDto", new BoardDto());
        model.addAttribute("dtos", dtos);
        model.addAttribute("stations", stationService.getAllStations());
        model.addAttribute("trains", trainService.getAllTrains());

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
        Station fromStation = stationService.getStationById(b.getFrom_id());
        Station toStation = stationService.getStationById(b.getTo_id());
        Train train = trainService.getTrainById(b.getTrain_id());

        List<PassengerDto> passengers = new ArrayList<>();
        for (Ticket t : tickets) {
            passengers.add(PassengerDto.getDtoFromPassenger(passengerService.getPassById(t.getPassenger_id())));
        }

        logger.info("Total number of passengers for board " + b.getName() + " is " + passengers.size());

        BoardDto boardDto = new BoardDto();
        boardDto.setName(b.getName());
        boardDto.setFrom(fromStation.getName());
        boardDto.setTo(toStation.getName());
        boardDto.setDeparture(DistanceAndTimeUtil.getStringDate(b.getDeparture()));
        int distance = (int) DistanceAndTimeUtil.getDistance(fromStation, toStation);
        boardDto.setDistance(distance);
        boardDto.setJourneyTime(DistanceAndTimeUtil.getJourneyTime(distance, train));
        boardDto.setAverageSpeed(train.getSpeed_percents() * 45 / 100);

        model.addAttribute("passengers", passengers);
        model.addAttribute("boardDetailed", boardDto);

        return "boarddata";
    }

    @Transactional
    @RequestMapping(value = "boards/add", method = RequestMethod.POST)
    public String addBoard(@ModelAttribute("boardDto") BoardDto boardDto, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || !user.getRole().equals("admin")) {
            return "notpass";
        }

        if (boardDto.getFrom_id() == boardDto.getTo_id()) {
            return "redirect:/tofromexception";
        }

        Board board = BoardDto.getBoardFromDto(boardDto);
        boardService.addBoard(board);

        return "redirect:/boards";
    }

    @RequestMapping(value = "/delay/add/{board_id}")
    public String addDelay(@PathVariable("board_id") int id, Model model, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || !user.getRole().equals("admin")) {
            return "notpass";
        }

        Board board = boardService.findBoardById(id);
        Station from = stationService.getStationById(board.getFrom_id());
        Station to = stationService.getStationById(board.getTo_id());
        Train train = trainService.getTrainById(board.getTrain_id());
        int distance = (int) DistanceAndTimeUtil.getDistance(from, to);
        Date arrival = new Date(board.getDeparture().getTime() + DistanceAndTimeUtil.getTime(DistanceAndTimeUtil.getJourneyTime(distance, train)));

        List<Delay> delays = delayService.getDelayByBoardId(board.getBoard_id());
        if (!delays.isEmpty()) {
            Delay d = DistanceAndTimeUtil.getResultingDelay(delays);
            String delay = DistanceAndTimeUtil.getStringDelay(d.getDelay_time());
            arrival = new Date(board.getDeparture().getTime()
                    + DistanceAndTimeUtil.getTime(DistanceAndTimeUtil.getJourneyTime(distance, train))
                    + DistanceAndTimeUtil.getTime(delay));
        }

        if(DistanceAndTimeUtil.isAlreadyArrived(board.getDeparture(), arrival)) {
            return "notexist";
        }

        model.addAttribute("delay", new DelayDto());
        model.addAttribute("fromName", from.getName());
        model.addAttribute("toName", to.getName());
        model.addAttribute("board", board);

        return "delays";
    }

    @Transactional
    @RequestMapping(value = "/delay/add/{board_id}", method = RequestMethod.POST)
    public String registerDelay(@PathVariable("board_id") int id, @ModelAttribute("delay") DelayDto delayDto, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || !user.getRole().equals("admin")) {
            return "notpass";
        }

        Board board = boardService.findBoardById(id);
        Station from = stationService.getStationById(board.getFrom_id());
        Station to = stationService.getStationById(board.getTo_id());
        Train train = trainService.getTrainById(board.getTrain_id());
        int distance = (int) DistanceAndTimeUtil.getDistance(from, to);
        Date arrival = new Date(board.getDeparture().getTime() + DistanceAndTimeUtil.getTime(DistanceAndTimeUtil.getJourneyTime(distance, train)));

        List<Delay> delays = delayService.getDelayByBoardId(board.getBoard_id());
        if (!delays.isEmpty()) {
            Delay d = DistanceAndTimeUtil.getResultingDelay(delays);
            String delay = DistanceAndTimeUtil.getStringDelay(d.getDelay_time());
            arrival = new Date(board.getDeparture().getTime()
                    + DistanceAndTimeUtil.getTime(DistanceAndTimeUtil.getJourneyTime(distance, train))
                    + DistanceAndTimeUtil.getTime(delay));
        }

        if(DistanceAndTimeUtil.isAlreadyArrived(board.getDeparture(), arrival)) {
            return "notexist";
        }

        Delay delay = DelayDto.getDelayFromDto(delayDto);
        delayService.addDelay(delay);
        return "redirect:/boards";
    }
}