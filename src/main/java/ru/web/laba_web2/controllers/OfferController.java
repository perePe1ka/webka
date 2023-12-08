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
import ru.web.laba_web2.viewModel.AddOfferViewModel;
import ru.web.laba_web2.viewModel.EditOffer;


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

    @ModelAttribute("editOffer")
    public EditOffer editOffer() {
        return new EditOffer();
    }

    @GetMapping("/add")
    String addOffer(Model model) {
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

    @GetMapping("/update/{uuid}")
    String showUpdateForm(@PathVariable("uuid") String uuid, Model model) throws Throwable {
        model.addAttribute("availableModels", modelService.allModels());
        model.addAttribute("availableUsers", userService.getAll());

        model.addAttribute("editOffer", offerService.findByUuid(uuid)
                .orElseThrow(() -> new OfferNotFoundException(uuid)));
        return "editOffer";
    }

    @PostMapping("/update/{uuid}")
    String updateOffer(@PathVariable("uuid") String uuid,
                       @Valid EditOffer editOffer,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("editOffer", editOffer);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editOffer", bindingResult);
            return "redirect:/offers/update/" + uuid;
        }

        offerService.editOffer(editOffer);
        return "redirect:/offers/show";
    }


}
