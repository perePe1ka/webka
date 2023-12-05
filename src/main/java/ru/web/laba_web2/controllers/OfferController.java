package ru.web.laba_web2.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.web.laba_web2.controllers.exceptions.OfferNotFoundException;
import ru.web.laba_web2.services.ModelService;
import ru.web.laba_web2.services.OfferService;
import ru.web.laba_web2.services.UserService;
import ru.web.laba_web2.services.dtos.OfferDto;
import ru.web.laba_web2.viewModel.AddOfferViewModel;


@Controller
@RequestMapping("/offers")
public class OfferController {
    private OfferService offerService;

    private ModelService modelService;

    private UserService userService;

    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all/{offer-description}")
    ModelAndView getAll(@PathVariable("offer-description") String offerDescription, ModelAndView modelAndView) {
        modelAndView.setViewName("offerPage");
        modelAndView.addObject("offers", offerService.getAll(offerDescription));
        return modelAndView;
    }

    @GetMapping("/show")
    public ModelAndView showAllOffers(ModelAndView modelAndView) {
        modelAndView.setViewName("showOffer");
        modelAndView.addObject("offersInfos", offerService.allOffers());
        return modelAndView;
    }

    @ModelAttribute("newOffer")
    public AddOfferViewModel initOffer() {
        return new AddOfferViewModel();
    }

    @GetMapping("/add")
    String addOffer(Model model)
    {
        model.addAttribute("availableModels", modelService.allModels());
        model.addAttribute("availableUsers", userService.getAll());
        return "addOffer";
    }

    @PostMapping("/add")
    String registerOffer(@Valid AddOfferViewModel newOffer, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newOffer", newOffer);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newOffer", bindingResult);

            return "redirect:/offers/add";
        }
        offerService.register(newOffer);

        return "redirect:/offers/show";
    }

    @GetMapping("/delete{offerDescription}")
    String deleteOffer(@PathVariable("offerDescription") String offerDescription) {
        offerService.deleteByOfferDescription(offerDescription);

        return "redirect:/offers/show";
    }

    @GetMapping("/get/(uuid)")
    OfferDto getOne(@PathVariable("uuid") String uuid) throws Throwable {
        return (OfferDto) offerService.findByUuid(uuid)
                .orElseThrow(() -> new OfferNotFoundException(uuid));
    }

    @PutMapping("/edit/{uuid}")
    ModelAndView editOffer(@ModelAttribute OfferDto offerDto, ModelAndView modelAndView) {
        offerService.editOffer(offerDto);
        modelAndView.setViewName("redirect:/offers");
        return modelAndView;
    }

    @GetMapping("/admins")
    ModelAndView getAllAdmins(ModelAndView modelAndView) {
        modelAndView.addObject("admins", offerService.getAllAdmins());
        modelAndView.setViewName("redirect:/offers");
        return modelAndView;
    }


    @GetMapping("/totalPrice")
    int calculateTotalPrice() {
        return offerService.calculateTotalPrice();
    }


}
