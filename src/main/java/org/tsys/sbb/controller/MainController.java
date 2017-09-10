package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tsys.sbb.model.User;
import org.tsys.sbb.service.UserService;
import org.tsys.sbb.util.EmailSender;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes(types = User.class)
public class MainController {

    private UserService userService;

    private static final String SESSION_USER = "sessionUser";
    private static final String REDIRECT_INDEX = "redirect:/index";
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/index"})
    public String openIndex(HttpSession session) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUsername(username);

        if (user == null) {
            LOGGER.info("Session user now is anonymous");
        } else {
            session.setAttribute("sessionUser", user);
            LOGGER.info("Session user now is " + user.getUsername());
        }

        return "index";
    }

    @RequestMapping(value = "/send")
    public String sendEmail(@RequestParam("e-name") String ename, @RequestParam("e-msg") String emsg,
                            @RequestParam("e-sbj") String esbj, @RequestParam("e-adr") String eadr) {

        new EmailSender().send("konstelis@gmail.com", esbj,
                emsg.concat(". Author ")
        .concat(ename).concat(". You can reply to address ")
        .concat(eadr));

        return "send";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        model.addAttribute("loginUser", new User());
        LOGGER.info("Loading login form");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String getIn(@Valid @ModelAttribute("loginUser") User user, BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        User sessionUser = userService.getUserByUsername(user.getUsername());
        session.setAttribute(SESSION_USER, sessionUser);

        LOGGER.info("Login is successful");
        return REDIRECT_INDEX;
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
            session.setAttribute("existingUser", user.getUsername());
            LOGGER.info("User already exists with login = " + user.getUsername());
            return "messages/logintaken";
        }

        if (user.getEmail() != null && userService.getUserByEmail(user.getEmail()) != null) {
            session.setAttribute("existingEmail", user.getEmail());
            LOGGER.info("User already exists with email = " + user.getEmail());
            return "messages/emailtaken";
        }

        String password = user.getPassword();
        user.setRole("user");
        userService.addUser(user);
        LOGGER.info("New user's registered, username = " + user.getUsername());

        new EmailSender().send(user.getEmail(), "Successful registration",
                "You've successfully registered at mer.me with login '"
                        .concat(user.getUsername())
                        .concat("' and password '")
                        .concat(password).concat("'"));

        return "messages/success";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {

        session.removeAttribute(SESSION_USER);
        session.invalidate();
        LOGGER.info("Session user's removed, session's invalidate");
        return REDIRECT_INDEX;
    }
}