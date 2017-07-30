package org.tsys.sbb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tsys.sbb.service.PassengerService;

@Controller
public class PassengersController {

    private PassengerService passengerService;

    @Autowired
    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @RequestMapping(value = "passengers", method = RequestMethod.GET)
    public String getAllPassengers() {
        return "passengers";
    }

    @RequestMapping("passengers/{board_id}")
    public String deleteUser(@PathVariable("board_id") int id) {


        return "redirect:/users";
    }
}