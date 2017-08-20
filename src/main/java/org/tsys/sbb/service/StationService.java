package org.tsys.sbb.service;

import org.tsys.sbb.model.Station;

import java.util.List;
import java.util.Map;

public interface StationService {

    Station getStationById(Integer id);

    List<Station> getAllStations();

    void addStation(Station station);

    Map<Integer, String> getStations();

    List<String> getAllStationsNames();
}