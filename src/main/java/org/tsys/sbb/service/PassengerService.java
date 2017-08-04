package org.tsys.sbb.service;

import org.tsys.sbb.model.Passenger;

import java.util.Date;
import java.util.List;

public interface PassengerService {

    Passenger getPassById(int id);

    List<Passenger> getPassByEverything(String name, String surname);

    List<Passenger> getAllPassengers();

    void addPassenger(Passenger passenger);

    void deletePassenger(int id);
}