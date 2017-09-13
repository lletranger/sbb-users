package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tsys.sbb.model.User;

@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        model.addAttribute("loginUser", new User());
        LOGGER.info("Loading login form");

        return "login";
    }

    @RequestMapping(value = "/autherror")
    public String oups() {

        return "messages/loginException";
    }
}
