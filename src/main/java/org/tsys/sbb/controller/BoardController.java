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
import org.tsys.sbb.util.Sender;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@SessionAttributes(types = User.class)
public class BoardController {

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

    @RequestMapping(value = "boards", method = RequestMethod.GET)
    public String getAllBoards(Model model, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || !user.getRole().equals("admin")) {
            return "notpass";
        }

        List<BoardDto> dtos = boardService.getAllBoards()
                .stream()
                .map(b -> boardService.getFullDto(b))
                .collect(Collectors.toList());

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

        Board board = boardService.findBoardById(id);

        if (board == null) {
            session.setAttribute("errorMessage", "There is no board with the requested ID");
            return "notexist";
        }

        List<PassengerDto> passengers = ticketService.findTicketsByBoardId(id)
                .stream()
                .map(ticket -> PassengerDto.getDtoFromPassenger(ticket.getPassenger()))
                .collect(Collectors.toList());

        logger.info("Total number of passengers for board " + board.getName() + " is " + passengers.size());
        model.addAttribute("passengers", passengers);
        model.addAttribute("boardDetailed", boardService.getDtoFromBoard(board));
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
            logger.info("Trying to add a board with same from and to stations!");
            return "tofromexception";
        }

        Board board = BoardDto.getBoardFromDto(boardDto);
        boardService.addBoard(board);
        new Sender().send();
        return "redirect:/boards";
    }

    @RequestMapping(value = "/delay/add/{board_id}")
    public String addDelay(@PathVariable("board_id") int id, Model model, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || !user.getRole().equals("admin")) {
            return "notpass";
        }

        Board board = boardService.findBoardById(id);

        if(board == null) {
            session.setAttribute("errorMessage", "There is no board with the requested ID");
            return "notexist";
        }

        Station from = stationService.getStationById(board.getFrom_id());
        Station to = stationService.getStationById(board.getTo_id());

        if(DistanceAndTimeUtil.isAlreadyArrived(board.getDeparture(), boardService.findArrival(id))) {
            logger.info("Trying to add a delay to an arrived board!");
            return "delayexc";
        }

        if(!DistanceAndTimeUtil.isDepartedOrArrived(board.getDeparture())) {
            logger.info("Trying to add a delay to a board that didn't depart yet!");
            return "delayexc";
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

        if(board == null) {
            session.setAttribute("errorMessage", "There is no board with the requested ID");
            return "notexist";
        }

        if(DistanceAndTimeUtil.isAlreadyArrived(board.getDeparture(), boardService.findArrival(id))) {
            logger.info("Trying to add a delay to an arrived board!");
            return "delayexc";
        }

        if(!DistanceAndTimeUtil.isDepartedOrArrived(board.getDeparture())){
            logger.info("Trying to add a delay to a board that didn't depart yet!");
            return "delayexc";
        }

        System.out.println(delayDto.getDelay());
        delayService.addDelay(DelayDto.getDelayFromDto(delayDto, board));
        new Sender().send();

        return "redirect:/boards";
    }
}