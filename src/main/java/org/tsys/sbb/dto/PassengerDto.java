package org.tsys.sbb.dto;

import lombok.Data;
import org.tsys.sbb.model.Passenger;
import org.tsys.sbb.util.DistanceAndTimeUtil;

public @Data class PassengerDto {

    private String name;

    private String surname;

    private String birth_date;

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