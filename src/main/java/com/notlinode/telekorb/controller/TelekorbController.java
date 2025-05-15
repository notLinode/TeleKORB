package com.notlinode.telekorb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TelekorbController {

    @RequestMapping("/")
    public String showHome() {
        return "index";
    }

    @RequestMapping("/buy-sim")
    public String showBuySim() {
        return "buy-sim";
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping("/404")
    public String showNotFound() {
        return "error";
    }

}
