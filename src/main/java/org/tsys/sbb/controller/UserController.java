package org.tsys.sbb.controller;

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

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String getAllUsers(Model model, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null || !user.getRole().equals("admin")) {
            return "notpass";
        }

        model.addAttribute("user", new User());
        model.addAttribute("allUsers", userService.getAllUsers());

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

        return "redirect:/users";
    }

    @Transactional
    @RequestMapping("setadmin/{id}")
    public String setAdmin(@PathVariable("id") int id, Model model, HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null || !sessionUser.getRole().equals("admin") || sessionUser.getUser_id() == id) {
            return "notpass";
        }

        User user = userService.getUserById(id);
        user.setRole("admin");
        userService.editUser(user);
        model.addAttribute("allUsers", userService.getAllUsers());

        return "users";
    }

    @Transactional
    @RequestMapping("setuser/{id}")
    public String setUser(@PathVariable("id") int id, Model model, HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (!sessionUser.getRole().equals("admin") || sessionUser.getUser_id() == id) {
            return "notpass";
        }

        User user = userService.getUserById(id);
        user.setRole("user");
        userService.editUser(user);
        model.addAttribute("allUsers", userService.getAllUsers());

        return "users";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (!user.getRole().equals("admin")) {
            return "notpass";
        }

        model.addAttribute("user", userService.getUserById(id));

        return "userdata";
    }
}