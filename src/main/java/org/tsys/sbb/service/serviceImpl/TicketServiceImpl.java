package org.tsys.sbb.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsys.sbb.dao.TicketDao;
import org.tsys.sbb.model.Ticket;
import org.tsys.sbb.service.TicketService;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketDao ticketDao;

    @Autowired
    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Transactional
    public Ticket findTicketById(int id) {
        return ticketDao.findTicketById(id);
    }

    @Transactional
    public List<Ticket> findTicketsByBoardId(int board_id) {
        return ticketDao.findTicketsByBoardId(board_id);
    }

    @Transactional
    public void addTicket(Ticket ticket) {
        ticketDao.addTicket(ticket);
    }

    @Transactional
    public List<Ticket> findTicketsByUserId(int user_id){
        return ticketDao.findTicketsByUserId(user_id);
    }

    @Transactional
    public void deleteTicket(int id) {
        ticketDao.deleteTicket(id);
    }
}