package org.tsys.sbb.dao;

import org.tsys.sbb.model.Station;

import java.util.List;

public interface StationDao {

    Station getStationById(int id);

    List<Station> getAllStations();

    void addStation(Station station);
}