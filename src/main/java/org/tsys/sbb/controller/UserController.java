package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.tsys.sbb.model.User;
import org.tsys.sbb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "users")
    public String getAllUsers(Model model, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || !user.getRole().equals("admin")) {
            return "notpass";
        }

        model.addAttribute("allUsers", userService.getAllUsers());

        logger.info("Loading all users to the users page");
        return "users";
    }

    @Transactional
    @RequestMapping("remove/{id}")
    public String deleteUser(@PathVariable("id") int id, HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null || !sessionUser.getRole().equals("admin") || sessionUser.getUser_id() == id) {
            return "notpass";
        }

        userService.deleteUser(id);

        logger.info("Deleting user with ID = " + id);
        return "redirect:/users";
    }

    @Transactional
    @RequestMapping("setadmin/{id}")
    public String setAdmin(@PathVariable("id") int id, HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null || !sessionUser.getRole().equals("admin") || sessionUser.getUser_id() == id) {
            return "notpass";
        }

        User user = userService.getUserById(id);
        user.setRole("admin");
        userService.editUser(user);

        logger.info("Setting admin role to user with ID = " + id);
        return "redirect:/users";
    }

    @Transactional
    @RequestMapping("setuser/{id}")
    public String setUser(@PathVariable("id") int id, HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null || !sessionUser.getRole().equals("admin") || sessionUser.getUser_id() == id) {
            return "notpass";
        }

        User user = userService.getUserById(id);
        user.setRole("user");
        userService.editUser(user);

        logger.info("Setting user role to user with ID = " + id);
        return "redirect:/users";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model, HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null || !sessionUser.getRole().equals("admin")) {
            return "notpass";
        }

        model.addAttribute("user", userService.getUserById(id));

        logger.info("Getting info about user with ID = " + id);
        return "userdata";
    }
}