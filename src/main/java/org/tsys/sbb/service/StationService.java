package org.tsys.sbb.service;

import org.tsys.sbb.model.Station;

import java.util.List;

public interface StationService {

    Station getStationById(Integer id);
    Station getStationByName(String name);
    List<Station> getAllStations();
    void addStation(Station station);
}
