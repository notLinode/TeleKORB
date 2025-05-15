package com.notlinode.telekorb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TelekorbController {

    @RequestMapping("/")
    public String showHome() {
        return "index";
    }

    @RequestMapping("/404")
    public String showNotFound() {
        return "error";
    }

}
