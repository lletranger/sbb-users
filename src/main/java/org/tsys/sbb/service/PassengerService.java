package org.tsys.sbb.service;

import org.tsys.sbb.model.Passenger;

import java.util.Date;
import java.util.List;

public interface PassengerService {

    Passenger getPassById(int id);
    Passenger getPassByEverything(String name, String surname, Date birth_date);
    List<Passenger> getAllPassengers();
    void addPassenger(Passenger passenger);
}
