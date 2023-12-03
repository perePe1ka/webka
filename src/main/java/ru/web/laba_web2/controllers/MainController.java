package ru.web.laba_web2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {
    @GetMapping("/")
    public String mainPage() {
        return "mainPage";
    }
}

