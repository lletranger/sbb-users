package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    private UserService userService;
    private EmailSender emailSender;

    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String COMMON_NOT_FOUND = "messages/ticket/boardInMove";
    private static final String NO_BOARD = "This board doesn't exist";
    private static final String NOT_FOUND = "messages/board/boardForTicketNotFound";
    private static final String PASSENGER_DTO = "passengerDto";
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

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @RequestMapping(value = "/ticket/add/{board_id}")
    public String addTicket(@PathVariable("board_id") int id, Model model, HttpSession session) {

        Board board = boardService.getBoardById(id);

        if (board == null) {
            session.setAttribute(ERROR_MESSAGE, NO_BOARD);
            return NOT_FOUND;
        }

        if (!boardService.isAvailable(id)) {
            session.setAttribute(ERROR_MESSAGE, "There are no available seats left this on board!");
            return "messages/ticket/noPlaces";
        }

        String from = stationService.getStationById(board.getFrom_id()).getName();
        String to = stationService.getStationById(board.getTo_id()).getName();

        if (DistanceAndTimeUtil.isDepartedOrArrived(boardService.findArrival(id))) {
            session.setAttribute(ERROR_MESSAGE, "This board has already arrived!");
            return COMMON_NOT_FOUND;
        }

        if (DistanceAndTimeUtil.isDepartedOrArrived(board.getDeparture())) {
            session.setAttribute(ERROR_MESSAGE, "This board has already departed!");
            return COMMON_NOT_FOUND;
        }

        if (session.getAttribute(PASSENGER_DTO) != null) {
            model.addAttribute(PASSENGER_DTO, (PassengerDto)session.getAttribute(PASSENGER_DTO));
        } else {
            model.addAttribute(PASSENGER_DTO, new PassengerDto());
        }

        model.addAttribute("board", board);
        session.setAttribute("fromTicket", from);
        session.setAttribute("toTicket", to);
        LOGGER.info("Loading new ticket form");

        return "tickets";
    }

    @Transactional
    @RequestMapping(value = "/ticket/add/{board_id}", method = RequestMethod.POST)
    public String registerTicket(@PathVariable("board_id") int id, @ModelAttribute(PASSENGER_DTO) PassengerDto passengerDto, HttpSession session) {

        User sessionUser = userService.getUserByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());

        Board board = boardService.getBoardById(id);

        if (board == null) {
            session.setAttribute(ERROR_MESSAGE, NO_BOARD);
            return NOT_FOUND;
        }

        if (!boardService.isAvailable(id)) {
            session.setAttribute(ERROR_MESSAGE, "There are no available seats left this on board!");
            return "messages/ticket/noPlaces";
        }

        String from = (String)session.getAttribute("fromTicket");
        String to = (String)session.getAttribute("toTicket");

        if (DistanceAndTimeUtil.isDepartedOrArrived(boardService.findArrival(id))) {
            session.setAttribute(ERROR_MESSAGE, "This board has already arrived!");
            return COMMON_NOT_FOUND;
        }

        if (DistanceAndTimeUtil.isDepartedOrArrived(board.getDeparture())) {
            session.setAttribute(ERROR_MESSAGE, "This board has already departed!");
            return COMMON_NOT_FOUND;
        }

        if(boardService.passExists(id, passengerDto)) {
            session.setAttribute("dupePassenger", passengerDto);
            session.setAttribute(PASSENGER_DTO, passengerDto);
            session.setAttribute("dupeBoard", board);
            session.setAttribute("dupeFrom", from);
            session.setAttribute("dupeTo", to);
            LOGGER.info("Can't buy ticket for duplicate passenger");
            return "messages/ticket/passengerAlready";
        }

        Ticket ticket = ticketService.createTicket(passengerDto, id, sessionUser);
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

        emailSender.send(sessionUser.getEmail(),"Your ticket from MeR", message);

        return "messages/ticket/ticketBought";
    }

    @RequestMapping(value = "/mytickets")
    public String allTicket(Model model) {

        User sessionUser = userService.getUserByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());
        model.addAttribute("ticketsDto", ticketService.getTicketsByUserId(sessionUser.getUser_id()));
        LOGGER.info("Loading all tickets to passenger with login '{}'", sessionUser.getUsername());

        return "myTickets";
    }

    @Transactional
    @RequestMapping(value = "/annulticket/{id}")
    public String annulTicket(@PathVariable("id") int id, HttpSession session) {

        User sessionUser = userService.getUserByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());
        Ticket ticket = ticketService.getTicketById(id);

        if(ticket == null || !sessionUser.getUsername().equalsIgnoreCase(ticket.getUser().getUsername())) {

            session.setAttribute(ERROR_MESSAGE, "This ticket doesn't exist!");
            return "messages/ticket/ticketNotFound";
        }

        if (DistanceAndTimeUtil.isDepartingOrArriving(ticket.getBoard().getDeparture())) {

            session.setAttribute(ERROR_MESSAGE, "You can't cancel used ticket!");
            return "messages/ticket/ticketUsed";
        }

        ticketService.deleteTicket(id);
        LOGGER.info("Deleting ticket with ID {} ", id);
        String message = "Your ticket with ID ".concat(String.valueOf(id)).concat(" was annulled. We're sad =(");
        emailSender.send(sessionUser.getEmail(),"Your ticket annulled", message);

        return "redirect:/mytickets";
    }

    @RequestMapping(value = "/tickets", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<TicketDto> getTickets() {

        LOGGER.info("Sending tickets in JSON");
        return ticketService.getAllTickets();
    }
}