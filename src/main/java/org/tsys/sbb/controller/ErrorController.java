package org.tsys.sbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping(value = "notpass")
    public String notPass() {
        return "notpass";
    }

    @RequestMapping(value = "snexception")
    public String stationNameError() {
        return "snexception";
    }

    @RequestMapping(value = "logintaken")
    public String userLoginError() {
        return "logintaken";
    }

    @RequestMapping(value = "tofromexception")
    public String fromToError() {
        return "tofromexception";
    }

    @RequestMapping(value = "passwordexception")
    public String passwordError() {
        return "passwordexception";
    }

    @RequestMapping(value = "nouserexception")
    public String userError() {
        return "nouserexception";
    }

    @RequestMapping(value = "noplaces")
    public String placesError() {
        return "noplaces";
    }

    @RequestMapping(value = "passalready")
    public String passError() {
        return "passalready";
    }
}
