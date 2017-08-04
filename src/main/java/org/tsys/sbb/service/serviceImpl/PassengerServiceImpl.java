package org.tsys.sbb.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsys.sbb.dao.PassengerDao;
import org.tsys.sbb.model.Passenger;
import org.tsys.sbb.service.PassengerService;

import java.util.Date;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    private PassengerDao passengerDao;

    @Autowired
    public void setPassengerDao(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    @Transactional
    public Passenger getPassById(int id) {
        return passengerDao.getPassById(id);
    }

    @Transactional
    public List<Passenger> getPassByEverything(String name, String surname) {
        return passengerDao.getPassByEverything(name, surname);
    }

    @Transactional
    public List<Passenger> getAllPassengers() {
        return passengerDao.getAllPassengers();
    }

    @Transactional
    public void addPassenger(Passenger passenger) {
        passengerDao.addPassenger(passenger);
    }

    @Transactional
    public void deletePassenger(int id) {
        passengerDao.deletePassenger(id);
    }
}