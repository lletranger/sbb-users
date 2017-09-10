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
@Transactional
public class PassengerServiceImpl implements PassengerService {

    private PassengerDao passengerDao;

    @Autowired
    public void setPassengerDao(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    public Passenger getPassengerById(int id) {
        return passengerDao.getPassengerById(id);
    }

    public List<Passenger> getPassengerByEverything(String name, String surname, Date birthDate) {
        return passengerDao.getPassengerByNameSurname(name, surname, birthDate);
    }

    public void addPassenger(Passenger passenger) {
        passengerDao.addPassenger(passenger);
    }

    public void deletePassenger(int id) {
        passengerDao.deletePassenger(id);
    }
}