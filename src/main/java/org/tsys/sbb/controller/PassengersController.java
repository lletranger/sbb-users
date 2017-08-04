package org.tsys.sbb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tsys.sbb.service.PassengerService;

@Controller
public class PassengersController {

    private PassengerService passengerService;

    @Autowired
    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @RequestMapping("passengers/{board_id}")
    public String passengerDetails(@PathVariable("board_id") int id) {
        return "redirect:/users";
    }
}