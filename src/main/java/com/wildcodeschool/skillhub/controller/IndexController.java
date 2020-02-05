package com.wildcodeschool.skillhub.controller;

import org.springframework.ui.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @GetMapping("/")
    public String start() {

        return "index";
    }

    @GetMapping("/user")
    public String user() {

        return "user";
    }    
}


