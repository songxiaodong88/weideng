package com.wisdom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InitController {

    @RequestMapping(value="/wisdom")
    public String init(){
        return "redirect:/login/wisdom";
    }
}
