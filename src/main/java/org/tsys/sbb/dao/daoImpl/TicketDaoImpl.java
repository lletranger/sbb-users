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

    private static final Logger logger = LoggerFactory.getLogger(TicketDao.class);

    @PersistenceContext
    private EntityManager em;

    public Ticket findTicketById(int id) {

        Ticket ticket = em.find(Ticket.class, id);

        logger.info("Ticket loaded " + ticket);

        return ticket;
    }

    @SuppressWarnings("unchecked")
    public List<Ticket> findTicketsByBoardId(int board_id) {

        List<Ticket> list = em.createQuery("SELECT t FROM Ticket t WHERE board_id=:board_id")
                .setParameter("board_id", board_id)
                .getResultList();

        list.forEach(ticket -> logger.info("Getting all tickets by board ID, got one " + ticket));

        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Ticket> findTicketsByUserId(int user_id) {

        List<Ticket> list = em.createQuery("SELECT t FROM Ticket t WHERE user_id=:user_id")
                .setParameter("user_id", user_id)
                .getResultList();

        list.forEach(ticket -> logger.info("Getting all tickets by user ID, got one " + ticket));

        return list;
    }

    public void addTicket(Ticket ticket) {

        em.persist(ticket);

        logger.info("Ticket added " + ticket);
    }

    public void deleteTicket(int id) {

        Ticket ticket = em.find(Ticket.class, id);

        if (ticket != null) {
            em.remove(ticket);
            logger.info("Ticket deleted, ID was " + id);
        }
    }
}