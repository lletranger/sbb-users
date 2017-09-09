package org.tsys.sbb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin/users")
    public String getAllUsers(Model model) {

        model.addAttribute("allUsers", userService.getAllUsers());
        LOGGER.info("Loading all users to the users page");
        return "users";
    }

    @Transactional
    @RequestMapping("/admin/remove/{id}")
    public String deleteUser(@PathVariable("id") int id, HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null || sessionUser.getUser_id() == id || id == 115) {
            return "messages/notpass";
        }

        userService.deleteUser(id);
        LOGGER.info("Deleting user with ID = " + id);
        return "redirect:/admin/users";
    }

    @Transactional
    @RequestMapping("/admin/setadmin/{id}")
    public String setAdmin(@PathVariable("id") int id) {

        User user = userService.getUserById(id);
        user.setRole("admin");
        userService.editUser(user);
        LOGGER.info("Setting admin role to user with ID = " + id);
        return "redirect:/admin/users";
    }

    @Transactional
    @RequestMapping("/admin/setuser/{id}")
    public String setUser(@PathVariable("id") int id, HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null || sessionUser.getUser_id() == id || id == 115) {
            return "messages/notpass";
        }

        User user = userService.getUserById(id);
        user.setRole("user");
        userService.editUser(user);
        LOGGER.info("Setting user role to user with ID = " + id);
        return "redirect:/admin/users";
    }

    @RequestMapping("/admin/userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model) {

        model.addAttribute("user", userService.getUserById(id));
        LOGGER.info("Getting info about user with ID = " + id);
        return "userdata";
    }

    @RequestMapping("/info")
    public String userInfo(Model model) {

        User currentUser = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("editUser", currentUser);
        LOGGER.info("Opening info to user " + currentUser.getUsername());
        return "info";
    }
}