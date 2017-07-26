package org.tsys.sbb.dao.daoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.tsys.sbb.dao.StationDao;
import org.tsys.sbb.model.Station;
import org.tsys.sbb.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StationDaoImpl implements StationDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public Station getStationByName(String name) {

//        Station station = (Station) em.createQuery("FROM Station WHERE station_name=:name").setParameter("name", name).getSingleResult();
//
//        logger.info("Station loaded: " + station);
//
        return null;
    }

    public Station getStationById(Integer id) {
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Station> getAllStations() {
        List<Station> list = em.createQuery("FROM Station").getResultList();

        for(Station s : list){
            logger.info("Station in list : " + s);
        }

        return list;
    }

    public void addStaton(Station station) {
        em.persist(station);
        logger.info("Station successfully added. Station details: " + station);

    }
}
