package org.tsys.sbb.dao.daoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.tsys.sbb.dao.TicketDao;
import org.tsys.sbb.model.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TicketDaoImpl implements TicketDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketDao.class);

    @PersistenceContext
    private EntityManager em;

    public Ticket getTicketById(int id) {
        Ticket ticket = em.find(Ticket.class, id);
        LOGGER.info("Ticket loaded by its ID {}", id);
        return ticket;
    }

    @SuppressWarnings("unchecked")
    public List<Ticket> getTicketsByBoardId(int board_id) {
        List<Ticket> list = em.createQuery("SELECT t FROM Ticket t WHERE board_id=:board_id")
                .setParameter("board_id", board_id)
                .getResultList();
        list.forEach(ticket -> LOGGER.info("Getting all tickets by board ID, got one {}", ticket.toString()));
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Ticket> getTicketsByUserId(int user_id) {
        List<Ticket> list = em.createQuery("SELECT t FROM Ticket t WHERE user_id=:user_id")
                .setParameter("user_id", user_id)
                .getResultList();
        list.forEach(ticket -> LOGGER.info("Getting all tickets by user ID, got one {}", ticket.toString()));

        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Ticket> getAllTickets() {
        List<Ticket> list = em.createQuery("FROM Ticket").getResultList();
        list.forEach(ticket -> LOGGER.info("Getting all tickets, got one {}", ticket.toString()));
        return list;
    }

    public void addTicket(Ticket ticket) {
        em.persist(ticket);
        LOGGER.info("Ticket added {}", ticket.toString());
    }

    public void deleteTicket(int id) {
        Ticket ticket = em.find(Ticket.class, id);
        if (ticket != null) {
            em.remove(ticket);
            LOGGER.info("Ticket deleted, ID was {}", id);
        }
    }
}