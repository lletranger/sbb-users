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
import org.tsys.sbb.dto.DelayDto;
import org.tsys.sbb.model.Board;
import org.tsys.sbb.model.Station;
import org.tsys.sbb.service.*;
import org.tsys.sbb.util.DistanceAndTimeUtil;
import org.tsys.sbb.util.Sender;

import javax.servlet.http.HttpSession;

@Controller
public class DelayController {

    private BoardService boardService;
    private StationService stationService;
    private DelayService delayService;
    private Sender sender;

    private static final String NO_BOARD = "This board doesn't exist";
    private static final String NOT_FOUND = "messages/board/boardNotFound";
    private static final String DELAY_ERROR = "delayError"  ;
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String DELAY_EXCEPTION = "messages/board/delayException";
    private static final Logger LOGGER = LoggerFactory.getLogger(DelayController.class);

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @Autowired
    public void setDelayService(DelayService delayService) {
        this.delayService = delayService;
    }

    @Autowired
    public void setSender(Sender sender) {
        this.sender = sender;
    }

    @RequestMapping(value = "/admin/delay/add/{board_id}")
    public String addDelay(@PathVariable("board_id") int id, Model model, HttpSession session) {

        Board board = boardService.getBoardById(id);

        if (board == null) {
            session.setAttribute(ERROR_MESSAGE, NO_BOARD);
            return NOT_FOUND;
        }

        Station from = stationService.getStationById(board.getFrom_id());
        Station to = stationService.getStationById(board.getTo_id());

        if (DistanceAndTimeUtil.isAlreadyArrived(board.getDeparture(), boardService.findArrival(id))) {
            session.setAttribute(DELAY_ERROR, "One does not simply add delay to an arrived board!");
            return DELAY_EXCEPTION;
        }

        if (!DistanceAndTimeUtil.isDepartedOrArrived(board.getDeparture())) {
            session.setAttribute(DELAY_ERROR, "Come on, board didn't even depart yet!");
            return DELAY_EXCEPTION;
        }

        model.addAttribute("delay", new DelayDto());
        model.addAttribute("fromName", from.getName());
        model.addAttribute("toName", to.getName());
        model.addAttribute("board", board);
        LOGGER.info("Openin delay adding form");

        return "delays";
    }

    @Transactional
    @RequestMapping(value = "/admin/delay/add/{board_id}", method = RequestMethod.POST)
    public String registerDelay(@PathVariable("board_id") int id, @ModelAttribute("delay") DelayDto delayDto, HttpSession session) {

        Board board = boardService.getBoardById(id);

        if (board == null) {
            session.setAttribute(ERROR_MESSAGE, NO_BOARD);
            return NOT_FOUND;
        }

        if (DistanceAndTimeUtil.isAlreadyArrived(board.getDeparture(), boardService.findArrival(id))) {
            session.setAttribute(DELAY_ERROR, "One does not simply add delay to an arrived board!");
            return DELAY_EXCEPTION;
        }

        if (!DistanceAndTimeUtil.isDepartedOrArrived(board.getDeparture())) {
            session.setAttribute(DELAY_ERROR, "Come on, board didn't even depart yet!");
            return DELAY_EXCEPTION;
        }

        delayService.addDelay(DelayDto.getDelayFromDto(delayDto, board));
        LOGGER.info("Delay added to board {}", board.getName());
        sender.send();

        return "redirect:/admin/boards";
    }
}
