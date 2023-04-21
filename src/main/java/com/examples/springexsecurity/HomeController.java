package com.examples.springexsecurity.springinaction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        System.out.println("》》》HomeController.home(): "+java.time.LocalTime.now());
        return "home";
    }
}

