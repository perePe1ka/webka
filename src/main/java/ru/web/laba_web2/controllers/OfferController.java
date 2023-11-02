package ru.web.laba_web2.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.web.laba_web2.services.OfferService;
import ru.web.laba_web2.services.dtos.OfferDto;


@Controller
@RequestMapping("/")
public class OfferController {
    private OfferService offerService;
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
    public ModelAndView registerOffer(@ModelAttribute OfferDto offerDto, BindingResult result, ModelAndView modelAndView) {
        if (result.hasErrors()) {
            modelAndView.setViewName("error");
        } else {
            offerService.register(offerDto);
            modelAndView.setViewName("redirect:/offers");
        }

        return modelAndView;
    }

    @DeleteMapping("/models/delete/{uuid}")
    public ModelAndView deleteOffer(@PathVariable("uuid") String uuid, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        offerService.deleteByUuid(uuid);
        redirectAttributes.addFlashAttribute("completeDelete", "Оффер был удалён");
        modelAndView.setViewName("redirect:/offers");
        return modelAndView;
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
