package org.tsys.sbb.dao;

import org.tsys.sbb.model.Ticket;

import java.util.List;

public interface TicketDao {

    Ticket getTicketById(int id);

    List<Ticket> getTicketsByBoardId(int board_id);

    List<Ticket> getTicketsByUserId(int user_id);

    List<Ticket> getAllTickets();

    void addTicket(Ticket ticket);

    void deleteTicket(int id);
}