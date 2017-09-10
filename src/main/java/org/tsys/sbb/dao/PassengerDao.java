package org.tsys.sbb.dao;

import org.tsys.sbb.model.Passenger;

import java.util.Date;
import java.util.List;

public interface PassengerDao {

    Passenger getPassengerById(int id);

    List<Passenger> getPassengerByNameSurname(String name, String surname, Date birthDate);

    void addPassenger(Passenger passenger);

    void deletePassenger(int id);
}