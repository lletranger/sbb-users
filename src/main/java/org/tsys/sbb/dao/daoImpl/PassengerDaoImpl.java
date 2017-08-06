package org.tsys.sbb.dao.daoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;
import org.tsys.sbb.dao.PassengerDao;
import org.tsys.sbb.model.Passenger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public class PassengerDaoImpl implements PassengerDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    public Passenger getPassById(int id) {
        Passenger passenger = em.find(Passenger.class, id);
        logger.info("Passenger loaded by id: " + passenger);
        return passenger;
    }

    @SuppressWarnings("unchecked")
    public List<Passenger> getPassByEverything(String name, String surname) {
        List<Passenger> list =  em.createQuery("SELECT p FROM Passenger p WHERE name=:name AND surname=:surname")
                .setParameter("name", name)
                .setParameter("surname", surname)
                .getResultList();

        for (Passenger p : list) {
            logger.info("Getting all passengers with same name and surname: " + p);
        }        return list;
    }

    public void addPassenger(Passenger passenger) {
        em.persist(passenger);
        logger.info("Passenger added: " + passenger);
    }

    public void deletePassenger(int id) {
        Passenger passenger = em.find(Passenger.class, id);

        if (passenger != null) {
            em.remove(passenger);
        }
        logger.info("Passenger deleted: " + passenger);
    }
}