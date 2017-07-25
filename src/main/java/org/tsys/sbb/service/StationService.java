package org.tsys.sbb.service;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.tsys.sbb.dao.StationDao;
import org.tsys.sbb.model.Station;

import java.util.List;

@Service
public class StationService {
    private StationDao stationDao;

    public void setStationDao(StationDao stationDao) {
        this.stationDao = stationDao;
    }

//    Station getS/tationByName(String name1){
//        stationDao./
//    }
}
