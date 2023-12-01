package ru.web.laba_web2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.web.laba_web2.viewModel.AddBrandViewModel;
import ru.web.laba_web2.viewModel.AddModelViewModel;

@Controller
@RequestMapping
public class MainController {
    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("newBrand", new AddBrandViewModel());
        model.addAttribute("newModel", new AddModelViewModel());
        return "mainPage";
    }
}

