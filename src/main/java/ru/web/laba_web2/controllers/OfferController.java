package ru.web.laba_web2.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.web.laba_web2.services.OfferService;
import ru.web.laba_web2.services.dtos.OfferDto;


@Controller
@RequestMapping("/")
public class OfferController {
    private OfferService offerService;

    private final ModelMapper modelMapper;
    @Autowired
    public OfferController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/offers")
    public ModelAndView getAllPages(ModelAndView modelAndView) {
        modelAndView.addObject("offers", offerService.getAll());
        modelAndView.setViewName("offerPage");
        return modelAndView;
    }

    @PostMapping("/register")
    public String registerOffer(@ModelAttribute OfferDto offerDto) {
        offerService.register(offerDto);
        return "redirect:/offers";
    }

    @DeleteMapping("/models/delete/{uuid}")
    public String deleteOffer(@PathVariable("uuid") String uuid) {
        offerService.deleteByUuid(uuid);
        return "redirect:/offers";
    }

    @GetMapping("/models/edit/{uuid}")
    public String editOfferForm(@PathVariable("uuid") String uuid, Model model) {
        offerService.findByUuid(uuid).ifPresent(offerDto -> model.addAttribute("offerDto", offerDto));
        return "offerPage";
    }

    @PutMapping("/models/edit/{uuid}")
    public String editOffer(@ModelAttribute OfferDto offerDto) {
        offerService.editOffer(offerDto);
        return "redirect:/offers";
    }

//    @GetMapping("/offers")
//    public String getAllPages(Model model) {
//        model.addAttribute("offers", offerService.getAll());
//        return "offerPage";
//    }
//
//    @PostMapping("/offers")
//    public String createOffer(@ModelAttribute OfferDto offerDto) {
//        offerService.create(offerDto);
//        return "redirect:/offers";
//    }
}
