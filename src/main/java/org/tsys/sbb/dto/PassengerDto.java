package org.tsys.sbb.dto;

import org.tsys.sbb.model.Passenger;
import org.tsys.sbb.util.DistanceAndTimeUtil;

public class PassengerDto {
    private String name;
    private String surname;
    private String birth_date;

    public PassengerDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public static PassengerDto getDtoFromPassenger(Passenger passenger) {
        PassengerDto dto = new PassengerDto();
        dto.setName(passenger.getName());
        dto.setSurname(passenger.getSurname());
        dto.setBirth_date(DistanceAndTimeUtil.getStringBirthDate(passenger.getBirth_date()));
        return dto;
    }

    public static Passenger getPassengerFromDto(PassengerDto dto) {
        Passenger passenger = new Passenger();
        passenger.setName(dto.getName());
        passenger.setSurname(dto.getSurname());
        passenger.setBirth_date(DistanceAndTimeUtil.getBirthDateFromString(dto.getBirth_date()));
        return passenger;
    }

}