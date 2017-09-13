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

    private static final String REDIRECT_ADMIN_USERS = "redirect:/admin/users";
    private static final String USER_NOT_EXISTS = "This user doesn't exist";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String COMMON_ERROR = "messages/commonError";
    private static final String COMMON_NOT_FOUND = "messages/commonNotFound";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin/users")
    public String getAllUsers(Model model) {

        User sessionUser = userService.getUserByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());

        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("sessionUserId", sessionUser.getUser_id());
        LOGGER.info("Loading all users to the users page");

        return "users";
    }

    @Transactional
    @RequestMapping("/admin/remove/{id}")
    public String deleteUser(@PathVariable("id") int id, HttpSession session) {

        User sessionUser = userService.getUserByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());

        if (sessionUser.getUser_id() == id) {
            session.setAttribute(ERROR_MESSAGE, "You're trying to delete yourself");
            return COMMON_ERROR;
        }

        if (id == 115) {
            session.setAttribute(ERROR_MESSAGE, "You're trying to delete a main admin");
            return COMMON_ERROR;
        }

        User user = userService.getUserById(id);

        if (user == null) {
            session.setAttribute(ERROR_MESSAGE, USER_NOT_EXISTS);
            return COMMON_NOT_FOUND;
        }

        userService.deleteUser(id);
        LOGGER.info("Deleting user {}", user.getUsername());

        return REDIRECT_ADMIN_USERS;
    }

    @Transactional
    @RequestMapping("/admin/setadmin/{id}")
    public String setAdmin(@PathVariable("id") int id, HttpSession session) {

        User user = userService.getUserById(id);

        if (user == null) {
            session.setAttribute(ERROR_MESSAGE, USER_NOT_EXISTS);
            return COMMON_NOT_FOUND;
        }

        user.setRole("admin");
        userService.editUser(user);
        LOGGER.info("Setting admin role to user {}", user.getUsername());

        return REDIRECT_ADMIN_USERS;
    }

    @Transactional
    @RequestMapping("/admin/setuser/{id}")
    public String setUser(@PathVariable("id") int id, HttpSession session) {

        User sessionUser = userService.getUserByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());

        if (sessionUser.getUser_id() == id) {
            session.setAttribute(ERROR_MESSAGE, "You're trying to set your role to user");
            return COMMON_ERROR;
        }

        if (id == 115) {
            session.setAttribute(ERROR_MESSAGE, "You're trying to set main admin role to user");
            return COMMON_ERROR;
        }

        User user = userService.getUserById(id);

        if (user == null) {
            session.setAttribute(ERROR_MESSAGE, USER_NOT_EXISTS);
            return COMMON_NOT_FOUND;
        }

        user.setRole("user");
        userService.editUser(user);
        LOGGER.info("Setting user role to user {}", user.getUsername());

        return REDIRECT_ADMIN_USERS;
    }

    @RequestMapping("/admin/userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model, HttpSession session) {

        User user = userService.getUserById(id);

        if (user == null) {
            session.setAttribute(ERROR_MESSAGE, USER_NOT_EXISTS);
            return COMMON_NOT_FOUND;
        }

        model.addAttribute("user", user);
        LOGGER.info("Getting info about user {} ", user.getUsername());

        return "userData";
    }

    @RequestMapping("/info")
    public String userInfo(Model model) {

        User user = userService.getUserByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());
        model.addAttribute("editUser", user);
        LOGGER.info("Opening info to user {} ", user.getUsername());

        return "info";
    }
}
