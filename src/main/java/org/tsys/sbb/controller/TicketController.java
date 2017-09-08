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
import org.tsys.sbb.dto.PassengerDto;
import org.tsys.sbb.dto.TicketDto;
import org.tsys.sbb.model.*;
import org.tsys.sbb.service.*;
import org.tsys.sbb.util.DistanceAndTimeUtil;
import org.tsys.sbb.util.EmailSender;

import javax.servlet.http.HttpSession;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
public class TicketController {

    private TicketService ticketService;
    private BoardService boardService;
    private StationService stationService;

    private static final String SU = "sessionUser";
    private static final String NO_BOARD ="There is no board with the requested ID";
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @RequestMapping(value = "/ticket/add/{board_id}")
    public String addTicket(@PathVariable("board_id") int id, Model model, HttpSession session) {

        Board board = boardService.findBoardById(id);

        if (board == null) {
            session.setAttribute("errorMessage", NO_BOARD);
            return "messages/notexist";
        }

        String from = stationService.getStationById(board.getFrom_id()).getName();
        String to = stationService.getStationById(board.getTo_id()).getName();

        if(DistanceAndTimeUtil.isAlreadyArrived(board.getDeparture(), boardService.findArrival(id))) {
            LOGGER.info("Can't buy ticket, board's already arrived!");
            return "messages/notexist";
        }

        model.addAttribute("board", board);
        model.addAttribute("passengerDto", new PassengerDto());
        session.setAttribute("fromTicket", from);
        session.setAttribute("toTicket", to);
        LOGGER.info("Loading new ticket form");
        return "tickets";
    }

    @Transactional
    @RequestMapping(value = "/ticket/add/{board_id}", method = RequestMethod.POST)
    public String registerTicket(@PathVariable("board_id") int id, @ModelAttribute("passengerDto") PassengerDto passengerDto, HttpSession session) {

        User user = (User) session.getAttribute(SU);

        Board board = boardService.findBoardById(id);

        if (board == null) {
            session.setAttribute("errorMessage", NO_BOARD);
            return "messages/notexist";
        }

        String from = stationService.getStationById(board.getFrom_id()).getName();
        String to = stationService.getStationById(board.getTo_id()).getName();

        if(DistanceAndTimeUtil.isAlreadyArrived(board.getDeparture(), boardService.findArrival(id))) {
            LOGGER.info("Can't buy ticket, board's already arrived!");
            return "messages/notexist";
        }

        if (!boardService.isAvailable(id)) {
            session.setAttribute("noPlacesBoard", board.getName());
            LOGGER.info("Can't buy ticket, board has no free seats!");
            return "messages/noplaces";
        }

        if(boardService.passExists(id, passengerDto)) {
            session.setAttribute("dupePassenger", passengerDto);
            session.setAttribute("dupeBoard", board);
            session.setAttribute("dupeFrom", from);
            session.setAttribute("dupeTo", to);
            LOGGER.info("Can't buy ticket for duplicate passenger");
            return "messages/passalready";
        }

        Ticket ticket = ticketService.createTicket(passengerDto, id, user);
        ticketService.addTicket(ticket);
        session.setAttribute("ticket", ticket);
        session.setAttribute("board", board);
        session.setAttribute("passenger", passengerDto);
        session.setAttribute("from", from);
        session.setAttribute("to", to);

        String message = "You've bought a ticket #".concat(String.valueOf(id))
                .concat(" for ")
                .concat(passengerDto.getName())
                .concat(" ")
                .concat(passengerDto.getSurname())
                .concat(" to ")
                .concat(to)
                .concat(". Departing ")
                .concat(DistanceAndTimeUtil.getStringDate(board.getDeparture()))
                .concat(" from ")
                .concat(from)
                .concat(". Have a nice trip!");

        new EmailSender().send(user.getEmail(),"Your ticket from MeR", message);

        return "messages/bought";
    }

    @RequestMapping(value = "/mytickets")
    public String allTicket(Model model, HttpSession session) {

        User user = (User) session.getAttribute(SU);
        model.addAttribute("ticketsDto", ticketService.findTicketsByUserId(user.getUser_id()));
        LOGGER.info("Loading all tickets to passenger with login = " + user.getUsername());
        return "mytickets";
    }

    @Transactional
    @RequestMapping(value = "/annulticket/{id}")
    public String annulTicket(@PathVariable("id") int id, HttpSession session) {

        User user = (User) session.getAttribute(SU);
        ticketService.deleteTicket(id);
        LOGGER.info("Deleting ticket with ID = " + id);
        String message = "Your ticket with ID "
                .concat(String.valueOf(id))
                .concat(" was annulled. We're sad =(");

        new EmailSender().send(user.getEmail(),"Your ticket annulled", message);
        return "redirect:/mytickets";
    }

    @RequestMapping(value = "/tickets", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<TicketDto> getTickets() {
        LOGGER.info("Sending tickets in JSON");
        return ticketService.findAllTickets();
    }
}