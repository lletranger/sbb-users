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

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    public Station getStationById(Integer id) {
        Station station = (Station)em.createQuery("SELECT s FROM Station s WHERE id=:id")
                .setParameter("id", id)
                .getSingleResult();
        logger.info("Station is loaded. Station details: " + station);
        return station;
    }

    public Station getStationByName(String name) {

        Station station = (Station)em.createQuery("FROM Station WHERE name=:name")
                .setParameter("name", name)
                .getSingleResult();

        logger.info("Station is loaded. Station details: " + station);
        return station;
    }

    @SuppressWarnings("unchecked")
    public List<Station> getAllStations() {
        List<Station> list = em.createQuery("FROM Station").getResultList();
        for(Station s : list){
            logger.info("Stations list : " + s);
        }
        return list;
    }

    public void addStation(Station station) {
        em.persist(station);
        logger.info("Station is successfully added. Station details: " + station);
    }
}
