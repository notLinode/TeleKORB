package com.notlinode.telekorb.controller;

import com.notlinode.telekorb.dto.UserDto;
import com.notlinode.telekorb.model.UserEntity;
import com.notlinode.telekorb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/account")
    public String showAccount(Model model, Authentication auth) {
        Optional<UserEntity> userEntity = userService.findUserByUsername(auth.getName());
        UserDto userDto = userService.mapEntityToDto(userEntity.get());
        model.addAttribute(userDto);

        return "account";
    }

}
