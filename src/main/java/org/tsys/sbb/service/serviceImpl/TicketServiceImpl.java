package org.tsys.sbb.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsys.sbb.dao.TicketDao;
import org.tsys.sbb.dto.TicketDto;
import org.tsys.sbb.model.Board;
import org.tsys.sbb.model.Passenger;
import org.tsys.sbb.model.Station;
import org.tsys.sbb.model.Ticket;
import org.tsys.sbb.service.BoardService;
import org.tsys.sbb.service.PassengerService;
import org.tsys.sbb.service.StationService;
import org.tsys.sbb.service.TicketService;
import org.tsys.sbb.util.DistanceAndTimeUtil;

import java.util.ArrayList;
import java.util.List;

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

    public Ticket findTicketById(int id) {
        return ticketDao.findTicketById(id);
    }

    public List<Ticket> findTicketsByBoardId(int board_id) {

        return ticketDao.findTicketsByBoardId(board_id);
    }

    public void addTicket(Ticket ticket) {
        ticketDao.addTicket(ticket);
    }

    public List<TicketDto> findTicketsByUserId(int user_id){

        List<Ticket> tickets = ticketDao.findTicketsByUserId(user_id);
        List<TicketDto> list = new ArrayList<>();

        for (Ticket ticket : tickets) {

            Board board = boardService.findBoardById(ticket.getBoard_id());
            Passenger passenger = passengerService.getPassById(ticket.getPassenger().getPass_id());
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

            list.add(dto);
        }

        return list;
    }

    public void deleteTicket(int id) {

        Ticket ticket = findTicketById(id);
        Passenger passenger = ticket.getPassenger();
        ticketDao.deleteTicket(id);
        passengerService.deletePassenger(passenger.getPass_id());
    }
}