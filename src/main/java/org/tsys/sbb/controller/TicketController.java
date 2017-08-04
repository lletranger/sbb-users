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
import org.tsys.sbb.dto.PassengerDto;
import org.tsys.sbb.dto.TicketDto;
import org.tsys.sbb.model.Board;
import org.tsys.sbb.model.Passenger;
import org.tsys.sbb.model.Ticket;
import org.tsys.sbb.model.User;
import org.tsys.sbb.service.*;
import org.tsys.sbb.util.DistanceAndTimeUtil;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @RequestMapping(value = "/ticket/add{board_id}")
    public String addTicket(@PathVariable("board_id") int id, Model model, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || user.getRole().equals("anon")) {
            return "redirect:/login";
        }

        logger.info("Opening form for adding passenger");

        Board board = boardService.findBoardById(id);
        model.addAttribute("passenger", new PassengerDto());
        model.addAttribute("from", stationService.getStationById(board.getFrom_id()));
        model.addAttribute("to", stationService.getStationById(board.getTo_id()));
        model.addAttribute("board", board);
        model.addAttribute("delay", new DelayDto());
        model.addAttribute("passenger", new PassengerDto());
        return "tickets";
    }

    @Transactional
    @RequestMapping(value = "/ticket/add{board_id}", method = RequestMethod.POST)
    public String registerTicket(@PathVariable("board_id") int id, @ModelAttribute("passenger") PassengerDto passengerDto, Model model, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || user.getRole().equals("anon")) {
            return "redirect:/login";
        }

        Board board = boardService.findBoardById(id);
        List<Ticket> list = ticketService.findTicketsByBoardId(id);
        logger.info("found board" + board);
        if(list.size() >= trainService.getTrainById(board.getTrain_id()).getSeats()){
            session.setAttribute("noplacesBoard", board.getName());
            return "redirect:/noplaces";
        }

        for(Ticket t : list){
            Passenger passenger = passengerService.getPassById(t.getPassenger_id());
            if(passengerDto.getName().equals(passenger.getName()) & passengerDto.getSurname().equals(passenger.getSurname()) &
                    passengerDto.getBirth_date().equals(DistanceAndTimeUtil.getStringBirthDate2(passenger.getBirth_date()))){
                session.setAttribute("pass2", passengerDto);
                session.setAttribute("boardname", board.getName());
                session.setAttribute("dep", board.getDeparture());
                session.setAttribute("from", stationService.getStationById(board.getFrom_id()).getName());
                session.setAttribute("to",  stationService.getStationById(board.getTo_id()).getName());
                return "redirect:/passalready";
            }
        }


        String name = passengerDto.getName();
        String surname = passengerDto.getSurname();
        Passenger passenger = PassengerDto.getPassengerFromDto(passengerDto);
        passengerService.addPassenger(passenger);
        logger.info("passenger bd is"+ passenger.getBirth_date());
        int passid = -1;

        List<Passenger> passengers = passengerService.getPassByEverything(name, surname);
        logger.info("found passengers for ticket " + passengers.size());
        for(Passenger p : passengers) {
            if (DistanceAndTimeUtil.passengerBirthDates(p.getBirth_date(), passengerDto.getBirth_date()) && p.getPass_id() > passid) {
                passid = p.getPass_id();
            }
        }
        logger.info("found passid " + passid);

        Ticket ticket = new Ticket();
        ticket.setBoard_id(id);
        ticket.setPassenger_id(passid);
        int uid = userService.getUserByLogin(((User)session.getAttribute("sessionUser")).getLogin()).getUser_id();
        ticket.setUser_id(uid);
        ticketService.addTicket(ticket);
        session.setAttribute("ticket", ticket);
        session.setAttribute("board", board);
        session.setAttribute("passenger", passenger);
        session.setAttribute("from", stationService.getStationById(board.getFrom_id()).getName());
        session.setAttribute("to",  stationService.getStationById(board.getTo_id()).getName());
        return "bought";
    }

    @RequestMapping(value = "/mytickets")
    public String allTicket(Model model, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || user.getRole().equals("anon")) {
            return "redirect:/login";
        }

        List<Ticket> tickets = ticketService.findTicketsByUserId(user.getUser_id());

        List<TicketDto> list = new ArrayList<>();
        for(Ticket t : tickets){
            Board b = boardService.findBoardById(t.getBoard_id());
            Passenger p = passengerService.getPassById(t.getPassenger_id());
            TicketDto dto = new TicketDto(t.getTicket_id(), b.getName(),
                    stationService.getStationById(b.getFrom_id()).getName(), stationService.getStationById(b.getTo_id()).getName(),
                    b.getDeparture().toString(), p.getName(), p.getSurname(), p.getBirth_date().toString());
            list.add(dto);
        }
        model.addAttribute("ticketsDto", list);
        return "mytickets";
    }

    @Transactional
    @RequestMapping(value = "/annulticket/{id}")
    public String annulTicket(@PathVariable("id") int id, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || user.getRole().equals("anon")) {
            return "redirect:/login";
        }

        Ticket ticket = ticketService.findTicketById(id);
        Passenger passenger = passengerService.getPassById(ticket.getPassenger_id());
        ticketService.deleteTicket(ticket.getTicket_id());
        passengerService.deletePassenger(passenger.getPass_id());
        return "redirect:/mytickets";
    }

}
