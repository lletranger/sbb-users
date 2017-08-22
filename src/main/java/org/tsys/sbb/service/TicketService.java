package org.tsys.sbb.service;

import org.tsys.sbb.dto.PassengerDto;
import org.tsys.sbb.dto.TicketDto;
import org.tsys.sbb.model.Ticket;
import org.tsys.sbb.model.User;

import java.util.List;

public interface TicketService {

    Ticket findTicketById(int id);

    List<Ticket> findTicketsByBoardId(int board_id);

    List<TicketDto> findTicketsByUserId(int user_id);

    void addTicket(Ticket ticket);

    void deleteTicket(int id);

    boolean isPassOnBoard(Ticket ticket, PassengerDto passengerDto);

    Ticket createTicket(PassengerDto passengerDto, int id, User sessionUser);
}