package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tsys.sbb.dto.PassengerDto;
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

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

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

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || user.getRole().equals("anon")) {
            return "redirect:/login";
        }

        Board board = boardService.findBoardById(id);
        String from = stationService.getStationById(board.getFrom_id()).getName();
        String to = stationService.getStationById(board.getTo_id()).getName();

        if(DistanceAndTimeUtil.isAlreadyArrived(board.getDeparture(), boardService.findArrival(id))) {
            logger.info("Can't buy ticket, board's already arrived!");
            return "notexist";
        }

        model.addAttribute("board", board);
        model.addAttribute("passengerDto", new PassengerDto());
        session.setAttribute("fromTicket", from);
        session.setAttribute("toTicket", to);
        logger.info("Loading new ticket form");
        return "tickets";
    }

    @Transactional
    @RequestMapping(value = "/ticket/add/{board_id}", method = RequestMethod.POST)
    public String registerTicket(@PathVariable("board_id") int id, @ModelAttribute("passengerDto") PassengerDto passengerDto, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || user.getRole().equals("anon")) {
            return "redirect:/login";
        }

        Board board = boardService.findBoardById(id);
        String from = stationService.getStationById(board.getFrom_id()).getName();
        String to = stationService.getStationById(board.getTo_id()).getName();

        if(DistanceAndTimeUtil.isAlreadyArrived(board.getDeparture(), boardService.findArrival(id))) {
            logger.info("Can't buy ticket, board's already arrived!");
            return "notexist";
        }

        if (!boardService.isAvailable(id)) {
            session.setAttribute("noPlacesBoard", board.getName());
            logger.info("Can't buy ticket, board has no free seats!");
            return "noplaces";
        }

        if(boardService.passExists(id, passengerDto)) {
            session.setAttribute("dupePassenger", passengerDto);
            session.setAttribute("dupeBoard", board);
            session.setAttribute("dupeFrom", from);
            session.setAttribute("dupeTo", to);
            logger.info("Can't buy ticket for duplicate passenger");
            return "passalready";
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

        return "bought";
    }

    @RequestMapping(value = "/mytickets")
    public String allTicket(Model model, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || user.getRole().equals("anon")) {
            return "redirect:/login";
        }

        model.addAttribute("ticketsDto", ticketService.findTicketsByUserId(user.getUser_id()));
        logger.info("Loading all tickets to passenger with login = " + user.getLogin());
        return "mytickets";
    }

    @Transactional
    @RequestMapping(value = "/annulticket/{id}")
    public String annulTicket(@PathVariable("id") int id, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || user.getRole().equals("anon")) {
            return "redirect:/login";
        }

        ticketService.deleteTicket(id);
        logger.info("Deleting ticket with ID = " + id);

        String message = "Your ticket with ID ".concat(String.valueOf(id))
                .concat(" was annulled. We're sad =(");

        new EmailSender().send(user.getEmail(),"Your ticket annulled", message);

        return "redirect:/mytickets";
    }

    @ResponseBody
    @RequestMapping(value = "/tickets", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Ticket> getTickets() {

        logger.info("Sending tickets in JSON");
        return ticketService.findAllTickets();
    }
}