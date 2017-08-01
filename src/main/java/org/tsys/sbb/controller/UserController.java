package org.tsys.sbb.controller;

import org.springframework.web.bind.annotation.*;
import org.tsys.sbb.model.User;
import org.tsys.sbb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes(types = User.class)
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String getAllUsers(Model model, HttpSession session) {

        User user = (User)session.getAttribute("sessionUser");
        if(!user.getRole().equals("admin")){
            return "notpass";
        }

        model.addAttribute("user", new User());
        model.addAttribute("allUsers", userService.getAllUsers());
        return "users";
    }

    @RequestMapping(value = "users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {

        if(userService.getUserByLogin(user.getLogin()) != null) {
            return "redirect:/uexexception";
        }

        if (user.getUser_id() == 0) {
            userService.addUser(user);
        } else {
            userService.editUser(user);
        }

        return "redirect:/users";
    }

    @RequestMapping("remove/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);

        return "redirect:/users";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("allUsers", userService.getAllUsers());
        return "users";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("status", userService.getUserById(id).getRole());
        return "userdata";
    }
}