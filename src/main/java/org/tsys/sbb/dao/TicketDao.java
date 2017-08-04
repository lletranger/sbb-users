package org.tsys.sbb.dao;

import org.tsys.sbb.model.Ticket;

import java.util.List;

public interface TicketDao {

    Ticket findTicketById(int id);

    List<Ticket> findTicketsByBoardId(int board_id);

    List<Ticket> findTicketsByUserId(int user_id);

    List<Ticket> findAllTickets();

    void addTicket(Ticket ticket);

    void deleteTicket(int id);
}