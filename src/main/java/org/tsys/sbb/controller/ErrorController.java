package org.tsys.sbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {


    @RequestMapping(value = "notpass")
    public String notPass(){
        return "notpass";
    }

    @RequestMapping(value = "snexception")
    public String stationNameError(){
        return "snexception";
    }

    @RequestMapping(value = "uexexception")
    public String userLoginError(){
        return "uexexception";
    }

    @RequestMapping(value = "tofromexception")
    public String fromToError(){
        return "tofromexception";
    }
}
