package org.tsys.sbb.dao;

import org.tsys.sbb.model.Ticket;

import java.util.List;

public interface TicketDao {

    Ticket findTicketById(int id);

    List<Ticket> findAllTickets();

    List<Ticket> findTicketsByBoardId(int board_id);

    List<Ticket> findTicketsByUserId(int user_id);

    void addTicket(Ticket ticket);

    void deleteTicket(int id);
}