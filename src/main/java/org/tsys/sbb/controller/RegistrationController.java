package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tsys.sbb.model.User;
import org.tsys.sbb.service.SecurityService;
import org.tsys.sbb.service.UserService;
import org.tsys.sbb.util.EmailSender;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    private SecurityService securityService;
    private UserService userService;
    private EmailSender emailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {

        model.addAttribute("newUser", new User());
        LOGGER.info("Loading register form");

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("newUser") User user, HttpSession session) {

        if (userService.getUserByUsername(user.getUsername()) != null) {
            session.setAttribute("registerException", "User with login '"
                    .concat(user.getUsername()).concat("' already exists"));

            return "messages/registerException";
        }

        if (!user.getEmail().equals("") && userService.getUserByEmail(user.getEmail()) != null) {
            session.setAttribute("registerException", "User with email '"
                    .concat(user.getEmail()).concat("' already exists"));

            return "messages/registerException";
        }

        user.setRole("user");
        userService.addUser(user);
        LOGGER.info("New user's registered with username {}", user.getUsername());

        emailSender.send(user.getEmail(), "Successful registration",
                "You've successfully registered at mer.me with login '"
                        .concat(user.getUsername())
                        .concat("' and password '")
                        .concat(user.getPassword()).concat("'. Congratulations!"));

//        securityService.autoLogin(user.getUsername(), user.getPassword());

        return "messages/success";
    }
}
