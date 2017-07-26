package org.tsys.sbb.controller;

import org.tsys.sbb.model.Station;
import org.tsys.sbb.model.User;
import org.tsys.sbb.service.StationService;
import org.tsys.sbb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private StationService stationService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String getAllUsers(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allStations", stationService.getAllStations());
        return "users";
    }

    @RequestMapping(value = "users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user){
        if(user.getUser_id() == 0){
            userService.addUser(user);
        }else {
            userService.editUser(user);
        }

        return "redirect:/users";
    }

    @RequestMapping(value = "users/addStation", method = RequestMethod.POST)
    public String addStation(@ModelAttribute("station") Station station){
     stationService.addStaton(station);
     return "redirect:/users";
    }

    @RequestMapping("remove/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);

        return "redirect:/users";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("allUsers", userService.getAllUsers());

        return "users";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("status", userService.getUserById(id).getRole());
        return "userdata";
    }
}
