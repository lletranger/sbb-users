package org.tsys.sbb.service;

import org.tsys.sbb.model.Passenger;

import java.util.List;

public interface PassengerService {

    /**
     * Gets a {@link Passenger} from the database
     *
     * @param id integer
     * @return a single {@link Passenger} with the specified {@link Passenger#pass_id} or null
     */
    Passenger getPassById(int id);

    /**
     * Gets all existing {@link Passenger}s from the database
     * with the specified {@link Passenger#name} and {@link Passenger#surname}
     *
     * @param name    String
     * @param surname String
     * @return an array list of {@link Passenger}s or null
     */
    List<Passenger> getPassByEverything(String name, String surname);

    /**
     * Saves a new {@link Passenger} to the database
     *
     * @param passenger {@link Passenger}
     */
    void addPassenger(Passenger passenger);

    /**
     * Deletes given {@link Passenger} from the database
     *
     * @param id integer
     */
    void deletePassenger(int id);
}