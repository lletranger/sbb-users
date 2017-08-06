package org.tsys.sbb.service;

import org.tsys.sbb.model.Ticket;

import java.util.List;

public interface TicketService {

    Ticket findTicketById(int id);

    List<Ticket> findTicketsByBoardId(int board_id);

    List<Ticket> findTicketsByUserId(int user_id);

    void addTicket(Ticket ticket);

    void deleteTicket(int id);
}