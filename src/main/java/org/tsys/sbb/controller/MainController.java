package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.tsys.sbb.model.User;
import org.tsys.sbb.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes(types = User.class)
public class MainController {

    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/")
    public String openLocal() {

        return "redirect:/index";
    }

    @RequestMapping(value = "/index")
    public String openIndex(HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");

        if (user == null) {
            user = new User();
            user.setRole("anon");
            session.setAttribute("sessionUser", user);
            logger.info("Creating new anonymous user");
        }

        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        model.addAttribute("loginUser", new User());

        logger.info("Loading login form");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String getIn(@Valid @ModelAttribute("loginUser") User user, HttpSession session) {

        if (userService.getUserByLogin(user.getLogin()) == null) {
            session.setAttribute("noUser", user.getLogin());

            logger.info("There's no registered user with login: " + user.getLogin());
            return "redirect:/nouserexception";
        }

        if (!userService.checkUser(user.getLogin(), user.getPassword())) {

            logger.info("Wrong password for user: " + user.getLogin());
            return "redirect:/passwordexception";
        }

        session.setAttribute("sessionUser", userService.getUserByLogin(user.getLogin()));

        logger.info("Login is successful");
        return "redirect:/index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {

        model.addAttribute("newUser", new User());

        logger.info("Loading register form");
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("newUser") User user, HttpSession session) {

        if (userService.getUserByLogin(user.getLogin()) != null) {
            session.setAttribute("existingUser", user.getLogin());

            logger.info("User already exists with login: " + user.getLogin());
            return "redirect:/logintaken";
        }

        user.setRole("user");
        userService.addUser(user);

        logger.info("New user registered, login: " + user.getLogin());
        return "success";
    }

    @RequestMapping(value = "/logout")
    public String logout(SessionStatus sessionStatus, HttpSession session) {

        session.removeAttribute("sessionUser");
        session.invalidate();
        sessionStatus.setComplete();

        logger.info("Session user removed, session closed on logout");
        return "redirect:/index";
    }
}