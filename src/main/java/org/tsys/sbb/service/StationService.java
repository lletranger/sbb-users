package org.tsys.sbb.service;

import org.tsys.sbb.model.Station;

import java.util.List;

public interface StationService {

    Station getStationByName(String name);
    Station getStationById(Integer id);
    List<Station> getAllStations();
    void addStaton(Station station);
}
