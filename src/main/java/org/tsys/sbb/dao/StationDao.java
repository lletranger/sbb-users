package org.tsys.sbb.dao;

import org.tsys.sbb.model.Station;

import java.util.List;

public interface StationDao {

    Station getStationById(Integer id);

    Station getStationByName(String name);

    List<Station> getAllStations();

    void addStation(Station station);
}