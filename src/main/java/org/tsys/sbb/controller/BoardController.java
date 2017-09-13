package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tsys.sbb.dto.BoardDto;
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
    private TicketService ticketService;
    private Sender sender;

    private static final String NO_BOARD = "This board doesn't exist";
    private static final String NOT_FOUND = "messages/board/boardNotFound";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);

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
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Autowired
    public void setSender(Sender sender) {
        this.sender = sender;
    }

    @RequestMapping(value = "/admin/boards")
    public String getAllBoards(Model model) {

        List<BoardDto> dtos = boardService.getAllBoards()
                .stream()
                .map(b -> boardService.getFullDto(b))
                .collect(Collectors.toList());

        model.addAttribute("dtos", dtos);

        return "boards";
    }

    @RequestMapping("/admin/boarddata/{id}")
    public String boardData(@PathVariable("id") int id, Model model, HttpSession session) {

        Board board = boardService.getBoardById(id);

        if (board == null) {
            session.setAttribute(ERROR_MESSAGE, NO_BOARD);
            return NOT_FOUND;
        }

        List<PassengerDto> passengers = ticketService.getTicketsByBoardId(id).stream()
                .map(ticket -> PassengerDto.getDtoFromPassenger(ticket.getPassenger()))
                .collect(Collectors.toList());

        LOGGER.info("Total number of passengers for board "
                .concat(board.getName())
                .concat(" is ")
                .concat(String.valueOf(passengers.size())));
        model.addAttribute("passengers", passengers);
        model.addAttribute("boardDetailed", boardService.getDtoFromBoard(board));
        return "boardData";
    }

    @RequestMapping(value = "/admin/boardsadd")
    public String addNewBoard(Model model) {

        model.addAttribute("boardDto", new BoardDto());
        model.addAttribute("stations", stationService.getAllStations());
        model.addAttribute("trains", trainService.getAllTrains());
        return "addBoard";
    }

    @Transactional
    @RequestMapping(value = "/admin/boardsadd", method = RequestMethod.POST)
    public String addBoard(@ModelAttribute("boardDto") BoardDto boardDto, HttpSession session) {

        if (boardDto.getFrom_id() == boardDto.getTo_id()) {
            LOGGER.info("Trying to add a board with same from and to stations!");
            return "messages/fromToException";
        }

        String now = DistanceAndTimeUtil.getStringDate(Calendar.getInstance().getTime());
        String dto = boardDto.getDeparture();

        if(DistanceAndTimeUtil.getDtoTime(dto) - DistanceAndTimeUtil.getDtoTime(now) <= 10*60*1000){
            session.setAttribute(ERROR_MESSAGE, "Board must depart at least 10 minutes later than current time");
            return "messages/board/addBoardBeforeNow";
        }

        Board board = BoardDto.getBoardFromDto(boardDto);
        boardService.addBoard(board);
        sender.send();
        return "redirect:/admin/boards";
    }
}
