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
import org.tsys.sbb.model.*;
import org.tsys.sbb.service.*;
import org.tsys.sbb.util.DistanceAndTimeUtil;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class TicketController {

    private TicketService ticketService;

    private BoardService boardService;

    private StationService stationService;

    private PassengerService passengerService;

    private UserService userService;

    private TrainService trainService;

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

    @Autowired
    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
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
        Date arrival = boardService.findArrival(id);

        if(DistanceAndTimeUtil.isAlreadyArrived(board.getDeparture(), arrival)) {
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
        Date arrival = boardService.findArrival(id);

        if(DistanceAndTimeUtil.isAlreadyArrived(board.getDeparture(), arrival)) {
            logger.info("Can't buy ticket, board's already arrived!");
            return "notexist";
        }

        List<Ticket> tickets = ticketService.findTicketsByBoardId(id);

        if (tickets.size() >= trainService.getTrainById(board.getTrain_id()).getSeats()) {
            session.setAttribute("noPlacesBoard", board.getName());
            logger.info("Can't buy ticket, board has no free seats!");
            return "noplaces";
        }


        String dtoName = passengerDto.getName();
        String dtoSurname = passengerDto.getSurname();
        String dtoBirthDate = passengerDto.getBirth_date();

        for (Ticket ticket : tickets) {

            Passenger passenger = ticket.getPassenger();

            if (passenger.getName().equalsIgnoreCase(dtoName)
                    && passenger.getSurname().equalsIgnoreCase(dtoSurname)
                    && DistanceAndTimeUtil.getStringBirthDate2(passenger.getBirth_date()).equalsIgnoreCase(dtoBirthDate)) {

                session.setAttribute("dupePassenger", PassengerDto.getDtoFromPassenger(passenger));
                session.setAttribute("dupeBoard", board);
                session.setAttribute("dupeFrom", stationService.getStationById(board.getFrom_id()).getName());
                session.setAttribute("dupeTo", stationService.getStationById(board.getTo_id()).getName());

                logger.info("Can't buy ticket for duplicate passenger");
                return "passalready";
            }
        }

        Passenger passenger = PassengerDto.getPassengerFromDto(passengerDto);
        passengerService.addPassenger(passenger);

        int passengerId = -1;
        List<Passenger> passengers = passengerService.getPassByEverything(dtoName, dtoSurname);

        for (Passenger passenger1 : passengers) {
            if (DistanceAndTimeUtil.passengerBirthDates(passenger1.getBirth_date(), passengerDto.getBirth_date())
                    && passenger1.getPass_id() > passengerId) {
                passengerId = passenger1.getPass_id();
            }
        }

        int uid = userService.getUserByLogin(((User) session.getAttribute("sessionUser"))
                .getLogin()).getUser_id();

        Ticket ticket = new Ticket();
        ticket.setBoard_id(id);
        ticket.setPassenger(passenger);
        ticket.setUser_id(uid);

        ticketService.addTicket(ticket);

        session.setAttribute("ticket", ticket);
        session.setAttribute("board", board);
        session.setAttribute("passenger", passengerDto);
        session.setAttribute("birth_date", DistanceAndTimeUtil.getStringBirthDate(passenger.getBirth_date()));
        session.setAttribute("from", stationService.getStationById(board.getFrom_id()).getName());
        session.setAttribute("to", stationService.getStationById(board.getTo_id()).getName());

        return "bought";
    }

    @RequestMapping(value = "/mytickets")
    public String allTicket(Model model, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || user.getRole().equals("anon")) {
            return "redirect:/login";
        }

        model.addAttribute("ticketsDto", ticketService.findTicketsByUserId(user.getUser_id()));
        logger.info("Loading all tickets to passenger with login " + user.getLogin());
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
        return "redirect:/mytickets";
    }
}