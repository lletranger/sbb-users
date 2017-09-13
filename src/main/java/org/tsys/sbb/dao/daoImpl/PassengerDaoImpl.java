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

    private static final Logger LOGGER = LoggerFactory.getLogger(PassengerDao.class);

    @PersistenceContext
    private EntityManager em;
    public Passenger getPassengerById(int id) {
        Passenger passenger = em.find(Passenger.class, id);
        LOGGER.info("Passenger loaded by its ID {}", passenger.toString());
        return passenger;
    }

    @SuppressWarnings("unchecked")
    public List<Passenger> getPassengerByNameSurname(String name, String surname, Date birthDate) {
        List<Passenger> list =  em.createQuery("SELECT p FROM Passenger p WHERE name=:name AND surname=:surname AND birth_date=:birthDate")
                .setParameter("name", name)
                .setParameter("surname", surname)
                .setParameter("birthDate", birthDate)
                .getResultList();
        list.forEach(passenger ->
                LOGGER.info("Getting all passengers with required name and surname, got one {}",
                        passenger.toString()));
        return list;
    }

    public void addPassenger(Passenger passenger) {
        em.persist(passenger);
        LOGGER.info("Passenger added {}", passenger.toString());
    }

    public void deletePassenger(int id) {
        Passenger passenger = em.find(Passenger.class, id);
        if (passenger != null) {
            em.remove(passenger);
        }
        LOGGER.info("Passenger deleted, ID was {}", id);
    }
}