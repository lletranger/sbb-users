package org.tsys.sbb.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsys.sbb.dao.StationDao;
import org.tsys.sbb.model.Station;
import org.tsys.sbb.service.StationService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StationServiceImpl implements StationService {

    private StationDao stationDao;

    @Autowired
    public void setStationDao(StationDao stationDao) {
        this.stationDao = stationDao;
    }

    @Transactional
    public Station getStationById(Integer id) {
        return stationDao.getStationById(id);
    }

    @Transactional
    public List<Station> getAllStations() {
        return stationDao.getAllStations();
    }

    @Transactional
    public void addStation(Station station) { stationDao.addStation(station); }

    @Transactional
    public Map<Integer, String> getStations()
    {
        return stationDao.getAllStations().stream()
                .collect(Collectors.toMap(Station::getStation_id, Station::getName));
    }
}