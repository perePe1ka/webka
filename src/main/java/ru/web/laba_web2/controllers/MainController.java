package ru.web.laba_web2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class MainController {
    @GetMapping("/")
    public String brandPage() {
        return "brandPage";
    }
}
