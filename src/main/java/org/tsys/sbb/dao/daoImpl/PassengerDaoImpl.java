package org.tsys.sbb.dao.daoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        logger.info("Found passenger " + passenger);
        return null;
    }

    public Passenger getPassByEverything(String name, String surname, Date birth_date) {
        Passenger passenger = (Passenger)em
                .createQuery("SELECT u FROM User u WHERE name=:name AND surname=:surname AND birth_date=:birth_date")
                .setParameter("name", name)
                .setParameter("surname", surname)
                .setParameter("birth_date", birth_date)
                .getSingleResult();
        logger.info("Found passenger " + passenger);
        return passenger;
    }

    @SuppressWarnings("unchecked")
    public List<Passenger> getAllPassengers() {
        List<Passenger> list = em.createQuery("FROM Passenger").getResultList();
        for(Passenger p : list){
            logger.info("Passengers list: " + p);
        }
        return list;
    }

    public void addPassenger(Passenger passenger) {
        em.persist(passenger);
        logger.info("Passenger saved: " + passenger);
    }
}
