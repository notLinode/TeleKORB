package com.notlinode.telekorb.controller;

import com.notlinode.telekorb.model.Tariff;
import com.notlinode.telekorb.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TelekorbController {

    @Autowired
    private TariffService tariffService;

    @RequestMapping("/")
    public String showHome(Model model) {
        model.addAttribute("personalTariff", tariffService.getFeatured(0).get());
        model.addAttribute("featured1", tariffService.getFeatured(1).get());
        model.addAttribute("featured2", tariffService.getFeatured(2).get());

        return "index";
    }

    @RequestMapping("/404")
    public String showNotFound() {
        return "error";
    }

}
