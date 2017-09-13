package org.tsys.sbb.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsys.sbb.dao.TicketDao;
import org.tsys.sbb.dto.PassengerDto;
import org.tsys.sbb.dto.TicketDto;
import org.tsys.sbb.model.*;
import org.tsys.sbb.service.BoardService;
import org.tsys.sbb.service.PassengerService;
import org.tsys.sbb.service.StationService;
import org.tsys.sbb.service.TicketService;
import org.tsys.sbb.util.DistanceAndTimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private TicketDao ticketDao;
    private BoardService boardService;
    private PassengerService passengerService;
    private StationService stationService;

    @Autowired
    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    public Ticket getTicketById(int id) {

        Ticket ticket = null;

        try { ticket = ticketDao.getTicketById(id);
        } catch (Exception e) { }

        return ticket;
    }

    public List<TicketDto> getAllTickets() {

        List<TicketDto> list = new ArrayList<>();

        ticketDao.getAllTickets().forEach(ticket -> {
            TicketDto ticketDto = new TicketDto();
            ticketDto.setBoardName(ticket.getBoard().getName());
            ticketDto.setFrom(stationService.getStationById(ticket.getBoard().getFrom_id()).getName());
            ticketDto.setTo(stationService.getStationById(ticket.getBoard().getTo_id()).getName());
            ticketDto.setDeparture(DistanceAndTimeUtil.getStringDate(ticket.getBoard().getDeparture()));
            ticketDto.setPassName(ticket.getPassenger().getName());
            ticketDto.setPassSurname(ticket.getPassenger().getSurname());
            ticketDto.setPassBirthDate(DistanceAndTimeUtil.getStringBirthDate(ticket.getPassenger().getBirth_date()));
            ticketDto.setUserLogin(ticket.getUser().getUsername());
            list.add(ticketDto);
        });

        return list;
    }

    public List<Ticket> getTicketsByBoardId(int board_id) {
        return ticketDao.getTicketsByBoardId(board_id);
    }

    public void addTicket(Ticket ticket) {
        ticketDao.addTicket(ticket);
    }

    public void deleteTicket(int id) {
        ticketDao.deleteTicket(id);
    }

    public List<TicketDto> getTicketsByUserId(int user_id) {

        List<TicketDto> list = new ArrayList<>();

        ticketDao.getTicketsByUserId(user_id).forEach(ticket -> {
            Board board = boardService.getBoardById(ticket.getBoard().getBoard_id());
            Passenger passenger = passengerService.getPassengerById(ticket.getPassenger().getPass_id());
            Station fromStation = stationService.getStationById(board.getFrom_id());
            Station toStation = stationService.getStationById(board.getTo_id());
            TicketDto dto = new TicketDto();
            dto.setId(ticket.getTicket_id());
            dto.setBoardName(board.getName());
            dto.setFrom(fromStation.getName());
            dto.setTo(toStation.getName());
            dto.setDeparture(board.getDeparture().toString());
            dto.setPassName(passenger.getName());
            dto.setPassSurname(passenger.getSurname());
            dto.setPassBirthDate(DistanceAndTimeUtil.getStringBirthDate(passenger.getBirth_date()));
            dto.setDeletable(!DistanceAndTimeUtil.isDepartingOrArriving(ticket.getBoard().getDeparture()));
            list.add(dto);
        });

        return list;
    }

    public boolean isPassOnBoard(Ticket ticket, PassengerDto passengerDto) {
        Passenger p1 = ticket.getPassenger();
        Passenger p2 = PassengerDto.getPassengerFromDto(passengerDto);
        return p1.getName().equals(p2.getName()) &&
                p1.getSurname().equals(p2.getSurname()) &&
                p1.getBirth_date().equals(p2.getBirth_date());
    }

    public Ticket createTicket(PassengerDto passengerDto, int id, User sessionUser) {

        Passenger passenger = PassengerDto.getPassengerFromDto(passengerDto);
        Ticket ticket = new Ticket();
        ticket.setBoard(boardService.getBoardById(id));
        ticket.setPassenger(passenger);
        ticket.setUser(sessionUser);

        return ticket;
    }
}