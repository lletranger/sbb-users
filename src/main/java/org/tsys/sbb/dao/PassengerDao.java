package org.tsys.sbb.dao;

import org.tsys.sbb.model.Passenger;

import java.util.Date;
import java.util.List;

public interface PassengerDao {

    Passenger getPassById(int id);
    Passenger getPassByEverything(String name, String surname, Date birth_date);
    List<Passenger> getAllPassengers();
    void addPassenger(Passenger passenger);
}
