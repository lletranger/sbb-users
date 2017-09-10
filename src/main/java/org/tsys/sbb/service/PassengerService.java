package org.tsys.sbb.service;

import org.tsys.sbb.model.Passenger;

import java.util.Date;
import java.util.List;

public interface PassengerService {

    /**
     * Gets a {@link Passenger} from the database
     *
     * @param id integer
     * @return a single {@link Passenger} with the specified {@link Passenger#pass_id} or null
     */
    Passenger getPassengerById(int id);

    /**
     * Gets all existing {@link Passenger}s from the database
     * with the specified {@link Passenger#name}, {@link Passenger#surname}
     * and {@link Passenger#birth_date}
     *
     * @param name    {@link String}
     * @param surname {@link String}
     * @param birthDate {@link Date}
     * @return an array list of {@link Passenger}s or null
     */
    List<Passenger> getPassengerByEverything(String name, String surname, Date birthDate);

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