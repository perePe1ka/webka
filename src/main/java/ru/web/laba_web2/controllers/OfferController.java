package ru.web.laba_web2.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.web.laba_web2.controllers.exceptions.OfferNotFoundException;
import ru.web.laba_web2.services.OfferService;
import ru.web.laba_web2.services.UserService;
import ru.web.laba_web2.services.dtos.OfferDto;
import ru.web.laba_web2.services.dtos.UserDto;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/")
public class OfferController {
    private OfferService offerService;

    private ModelMapper modelMapper;

    private ModelAndView modelAndView;

    private RedirectAttributes redirectAttributes;

    @Autowired
    public OfferController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/offers")
    List<OfferDto> getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("offerPage");
        modelAndView.addObject("offers", offerService.getAll());
        return (List<OfferDto>) modelAndView;
    }

    @PostMapping("/register")
    ModelAndView registerOffer(@ModelAttribute OfferDto offerDto, ModelAndView modelAndView) {
        offerService.register(offerDto);
        modelAndView.setViewName("redirect:/offers");
        return modelAndView;
    }

    @DeleteMapping("/offers/{uuid}")
    ModelAndView deleteOffer(@PathVariable("uuid") String uuid, ModelAndView modelAndView) {
        offerService.deleteByUuid(uuid);
        modelAndView.setViewName("redirect:/offers");
        return modelAndView;
    }

    @GetMapping("/offers/(uuid)")
    OfferDto getOne(@PathVariable("uuid") String uuid) throws Throwable {
        return (OfferDto) offerService.findByUuid(uuid)
                .orElseThrow(() -> new OfferNotFoundException(uuid));
    }

    @PutMapping("/offers/{uuid}")
    ModelAndView editOffer(@ModelAttribute OfferDto offerDto, ModelAndView modelAndView) {
        offerService.editOffer(offerDto);
        modelAndView.setViewName("redirect:/offers");
        return modelAndView;
    }

    @GetMapping("/admins")
    ModelAndView getAllAdmins() {
        modelAndView.addObject("admins", offerService.getAllAdmins());
        modelAndView.setViewName("redirect:/offers");
        return modelAndView;
    }


    @GetMapping("/totalPrice")
    int calculateTotalPrice() {
        return offerService.calculateTotalPrice();
    }

//    @GetMapping("/offers")
//    public ModelAndView getAllPages(ModelAndView modelAndView) {
//        modelAndView.addObject("offers", offerService.getAll());
//        modelAndView.setViewName("offerPage");
//        return modelAndView;
//    }
//
//    @PostMapping("/register")
//    public ModelAndView registerOffer(@ModelAttribute OfferDto offerDto, BindingResult result, ModelAndView modelAndView) {
//        if (result.hasErrors()) {
//            modelAndView.setViewName("error");
//        } else {
//            offerService.register(offerDto);
//            modelAndView.setViewName("redirect:/offers");
//        }
//
//        return modelAndView;
//    }
//
//    @DeleteMapping("/models/delete/{uuid}")
//    public ModelAndView deleteOffer(@PathVariable("uuid") String uuid, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
//        offerService.deleteByUuid(uuid);
//        redirectAttributes.addFlashAttribute("completeDelete", "Оффер был удалён");
//        modelAndView.setViewName("redirect:/offers");
//        return modelAndView;
//    }
//
//    @GetMapping("/models/edit/{uuid}")
//    public ModelAndView editOfferForm(@PathVariable("uuid") String uuid, ModelAndView modelAndView) {
//        modelAndView.addObject("offers", offerService.findByUuid(uuid));
//        modelAndView.setViewName("offerPage");
//        return modelAndView;
//    }
//
//    @PutMapping("/models/edit/{uuid}")
//    public ModelAndView editOffer(@ModelAttribute OfferDto offerDto, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
//        offerService.editOffer(offerDto);
//        redirectAttributes.addFlashAttribute("editComplete", "Оффер успешно изменён");
//        modelAndView.setViewName("redirect:/offers");
//        return modelAndView;
//    }
//
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
