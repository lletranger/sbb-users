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

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    public Ticket findTicketById(int id) {
        Ticket ticket = (Ticket) em.createQuery("SELECT t FROM Ticket t WHERE id=:id")
                .setParameter("id", id)
                .getSingleResult();
        logger.info("Ticket loaded by id: " + ticket);
        return ticket;
    }

    @SuppressWarnings("unchecked")
    public List<Ticket> findTicketsByBoardId(int board_id) {
        List<Ticket> list = em.createQuery("SELECT t FROM Ticket t WHERE board_id=:board_id")
                .setParameter("board_id", board_id)
                .getResultList();
        for(Ticket t :list){
            logger.info("Getting all tickets by board id: " + t);
        }        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Ticket> findAllTickets() {
        List<Ticket> list = em.createQuery("FROM Ticket").getResultList();
        for(Ticket t : list) {
            logger.info("Getting all tickets: " + t);
        }
        return list;
    }

    public void addTicket(Ticket ticket) {
        em.persist(ticket);
        logger.info("Ticket added: " + ticket);
    }
}
