package com.notlinode.telekorb.controller;

import com.notlinode.telekorb.dto.UserDto;
import com.notlinode.telekorb.model.UserEntity;
import com.notlinode.telekorb.service.PhoneNumberService;
import com.notlinode.telekorb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneNumberService phoneService;

    @GetMapping("/login")
    public String showLogin(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute(userDto);

        return "login";
    }

    @GetMapping("/buy-sim")
    public String showRegister(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute(userDto);

        model.addAttribute("niceNumbers", phoneService.getAllNiceNumbers());

        return "buy-sim";
    }

    @PostMapping("/buy-sim")
    public String register(UserDto userDto) {
        UserEntity user = userService.createUser(userDto);

        return "redirect:/login?phoneNum=" + user.getUsername();
    }

}
