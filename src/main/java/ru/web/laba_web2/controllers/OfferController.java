package ru.web.laba_web2.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.services.IModelService;
import ru.web.laba_web2.services.IOfferService;
import ru.web.laba_web2.services.IUserService;
import ru.web.laba_web2.viewModel.AddOfferViewModel;
import ru.web.laba_web2.viewModel.EditOffer;

import java.security.Principal;
import java.util.Optional;


@Controller
@RequestMapping("/offers")
public class OfferController {
    private IOfferService offerService;

    private IModelService modelService;

    private IUserService userService;

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Autowired
    public void setModelService(IModelService modelService) {
        this.modelService = modelService;
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOfferService(IOfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all/{offer-description}")
    ModelAndView getAll(@PathVariable("offer-description") String offerDescription, ModelAndView modelAndView, Principal principal) {
        LOG.log(Level.INFO, "Show all offers for " + principal.getName());
        modelAndView.setViewName("offerPage");
        modelAndView.addObject("offers", offerService.getAll(offerDescription));
        return modelAndView;
    }

    @GetMapping("/show")
    public ModelAndView showAllOffers(ModelAndView modelAndView, Principal principal) {
        LOG.log(Level.INFO, "Show briefly offers for " + principal.getName());
        modelAndView.setViewName("showOffer");
        modelAndView.addObject("offersInfos", offerService.allOffers());
        return modelAndView;
    }

    @ModelAttribute("newOffer")
    public AddOfferViewModel initOffer() {
        return new AddOfferViewModel();
    }


    @GetMapping("/add")
    String addOffer(Model model, Principal principal) {
        LOG.log(Level.INFO, "Add offer for " + principal.getName());
        User currentUser = userService.findByUsername(principal.getName());

        model.addAttribute("availableModels", modelService.allModels());
        model.addAttribute("seller", currentUser.getUsername());

        return "addOffer";
    }

    @PostMapping("/add")
    String registerOffer(@Valid AddOfferViewModel newOffer, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newOffer", newOffer);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newOffer", bindingResult);

            return "redirect:/offers/add";
        }

        User currentUser = userService.findByUsername(principal.getName());
        newOffer.setSeller(currentUser.getUsername());
        offerService.register(newOffer);

        return "redirect:/offers/show";
    }

    @GetMapping("/delete{offerDescription}")
    String deleteOffer(@PathVariable("offerDescription") String offerDescription) {
        offerService.deleteByOfferDescription(offerDescription);

        return "redirect:/offers/show";
    }

    @ModelAttribute("editOffer")
    public EditOffer editOffer() {
        return new EditOffer();
    }

    @GetMapping("/update/{uuid}")
    String showUpdateForm(@PathVariable("uuid") String uuid, Model model, Principal principal) throws Throwable {
        LOG.log(Level.INFO, "Edit offer for " + principal.getName());
        Optional<EditOffer> editOfferOptional = offerService.findByUuid(uuid);

        if (editOfferOptional.isPresent()) {
            EditOffer editOffer = editOfferOptional.get();
            String sellerUsername = editOffer.getSeller().getUsername();
            LOG.log(Level.INFO, "sellerUsername: " + sellerUsername);
            LOG.log(Level.INFO, "principal: " + principal.getName());
            if (sellerUsername.equals(principal.getName())) {
                model.addAttribute("availableModels", modelService.allModels());
                model.addAttribute("editOffer", editOffer);
                return "editOffer";
            } else {
                return "redirect:/offers/show";
            }
        } else {
            return "redirect:/offers/show";
        }
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
