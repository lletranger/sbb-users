package org.tsys.sbb.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsys.sbb.dao.StationDao;
import org.tsys.sbb.model.Station;
import org.tsys.sbb.service.StationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class StationServiceImpl implements StationService {

    private StationDao stationDao;

    @Autowired
    public void setStationDao(StationDao stationDao) {
        this.stationDao = stationDao;
    }

    public Station getStationById(Integer id) {
        return stationDao.getStationById(id);
    }

    public List<Station> getAllStations() {
        return stationDao.getAllStations();
    }

    public void addStation(Station station) { stationDao.addStation(station); }

    public Map<Integer, String> getStations() {
        return stationDao.getAllStations().stream()
                .collect(Collectors.toMap(Station::getStation_id, Station::getName));
    }

    public List<String> getAllStationsNames() {
        return getAllStations().stream()
                .map(Station::getName)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}