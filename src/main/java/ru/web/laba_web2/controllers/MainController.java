package ru.web.laba_web2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping
public class MainController {
    private ModelAndView modelAndView;
    @GetMapping("/")
    public ModelAndView mainPage() {
        modelAndView.setViewName("mainPage");
        return modelAndView;
    }
}
