package org.tsys.sbb.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.tsys.sbb.model.Station;
import org.tsys.sbb.model.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class StationDao {

    private EntityManager em;

    Station getStationByName(String name1){
      List<Station> list = em.createQuery("FROM Station WHERE stationName=:name").setParameter("name", name1).getResultList();
        return list.get(0);
    }
}
