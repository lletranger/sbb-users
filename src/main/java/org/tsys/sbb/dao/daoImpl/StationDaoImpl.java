package org.tsys.sbb.dao.daoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.tsys.sbb.dao.StationDao;
import org.tsys.sbb.model.Station;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StationDaoImpl implements StationDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(StationDao.class);

    @PersistenceContext
    private EntityManager em;

    public Station getStationById(int id) {
       Station station = em.find(Station.class, id);
        LOGGER.info("Station loaded by its ID {}", station.toString());
        return station;
    }

    @SuppressWarnings("unchecked")
    public List<Station> getAllStations() {
        List<Station> list = em.createQuery("FROM Station").getResultList();
        list.forEach(station -> LOGGER.info("Getting all stations, got one {}", station.toString()));
        return list;
    }

    public void addStation(Station station) {
        em.persist(station);
        LOGGER.info("Station added {}", station.toString());
    }
}