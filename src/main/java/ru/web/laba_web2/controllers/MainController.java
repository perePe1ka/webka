package ru.web.laba_web2.controllers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.web.laba_web2.models.Offer;
import ru.web.laba_web2.services.impl.StatisticsService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping
public class MainController {

    private StatisticsService statisticsService;

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Autowired
    public void setStatisticsService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/statistics")
    public String showStatistics(Model model, Principal principal) {
        LOG.log(Level.INFO, "Show statistics for" + principal.getName());
        int averageCarPrice = statisticsService.getAverageCarPrice();
        List<Offer> top3OffersByMileage = statisticsService.getTopOffersByMileage();

        model.addAttribute("averageCarPrice", averageCarPrice);
        model.addAttribute("top3OffersByMileage", top3OffersByMileage);

        return "statistics";
    }
    @GetMapping("/")
    public String mainPage() {
        return "mainPage";
    }
}

