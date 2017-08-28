package org.tsys.sbb.service;

import org.tsys.sbb.dto.StationsDto;
import org.tsys.sbb.model.Station;

import java.util.List;

public interface StationService {

    /**
     * Gets a {@link Station} from the database
     *
     * @param id integer
     * @return a single {@link Station} with the specified {@link Station#station_id} or null
     */
    Station getStationById(Integer id);

    /**
     * Gets all existing {@link Station}s from the database
     *
     * @return an array list of {@link Station}s or null
     */
    List<Station> getAllStations();

    /**
     * Saves a new {@link Station} to the database
     *
     * @param station {@link Station}
     */
    void addStation(Station station);

    /**
     * Checks if {@link Station} with the requested {@link Station#name}
     * already exists in the database
     *
     * @param name String
     * @return true or false
     */
    boolean isExist(String name);

    /**
     * Convert all {@link Station}s in the database into
     * {@link StationsDto}
     *
     * @return a single {@link StationsDto}
     */
    StationsDto getAllStationsDto();
}