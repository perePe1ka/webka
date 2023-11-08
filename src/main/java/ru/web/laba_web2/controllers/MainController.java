package ru.web.laba_web2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping
public class MainController {
    @GetMapping("/")
    public ModelAndView mainPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("mainPage");
        return mav;
    }
}
