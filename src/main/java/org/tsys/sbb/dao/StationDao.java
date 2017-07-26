package org.tsys.sbb.dao;

import org.tsys.sbb.model.Station;

import java.util.List;

public interface StationDao {

    Station getStationByName(String name);
    Station getStationById(Integer id);
    List<Station> getAllStations();
    void addStaton(Station station);
}
