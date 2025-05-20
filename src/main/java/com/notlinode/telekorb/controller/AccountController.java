package com.notlinode.telekorb.controller;

import com.notlinode.telekorb.dto.TariffDto;
import com.notlinode.telekorb.dto.UserDto;
import com.notlinode.telekorb.model.Tariff;
import com.notlinode.telekorb.model.UserEntity;
import com.notlinode.telekorb.service.TariffService;
import com.notlinode.telekorb.service.UnlimitedService;
import com.notlinode.telekorb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private TariffService tariffService;

    @Autowired
    private UnlimitedService unlimitedService;

    @GetMapping("/account")
    public String showAccount(Model model, Authentication auth) {
        Optional<UserEntity> userEntity = userService.findUserByUsername(auth.getName());
        UserDto userDto = userService.mapEntityToDto(userEntity.get());
        model.addAttribute(userDto);

        var tariffs = tariffService.getAllPremade();
        model.addAttribute("tariffs", tariffs);

        var personal = tariffService.getFeatured(0);
        model.addAttribute("personal", personal.get());

        if (userDto.getTariff() != null && userDto.getTariff().isPersonal()) {
            model.addAttribute("bandwidthCost", userDto.getTariff().getCosts());
            model.addAttribute("allUnlimiteds", unlimitedService.getAll());
        }

        return "account";
    }

    @PostMapping("/account/set-tariff")
    public String setTariff(@RequestParam Long tariffId, Authentication auth) {
        try {
            userService.changeTariff(auth.getName(), tariffId);
            return "redirect:/account";
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/404";
        }
    }

    @PostMapping("/account/remove-tariff")
    public String removeTariff(Authentication auth) {
        try {
            userService.removeTariff(auth.getName());
            return "redirect:/account";
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/404";
        }
    }

    @PostMapping("/account/update-personal-tariff")
    public String updatePersonalTariff(TariffDto updated, Authentication auth) {
        UserEntity user = userService.findUserByUsername(auth.getName()).get();
        var userTariffId = user.getTariff().getId();
        updated.setId(userTariffId);
        try {
            tariffService.updateTariff(updated);
        }
        catch (Exception ignored) {
        }

        return "redirect:/account";
    }

}
