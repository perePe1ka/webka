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
import ru.web.laba_web2.services.impl.BrandServiceImpl;
import ru.web.laba_web2.viewModel.AddBrandViewModel;
import ru.web.laba_web2.viewModel.EditBrand;

import java.security.Principal;
import java.util.Optional;


@Controller
@RequestMapping("/brands")
public class BrandController {
    private BrandServiceImpl brandService;

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Autowired
    public void setBrandService(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/all/{brand-name}")
    ModelAndView getAll(@PathVariable("brand-name") String brandName, ModelAndView modelAndView, Principal principal) {
        LOG.log(Level.INFO, "Show all brands for " + principal.getName());
        modelAndView.setViewName("brandPage");
        modelAndView.addObject("brands", brandService.getAll(brandName));
        return modelAndView;
    }

    @GetMapping("/show")
    public ModelAndView showAllBrands(Principal principal, ModelAndView modelAndView) {
        LOG.log(Level.INFO, "Show briefly brands for" + principal.getName());
        modelAndView.setViewName("showBrand");
        modelAndView.addObject("brandsInfos", brandService.allBrands());
        return modelAndView;
    }

    @GetMapping("/add")
    public String addBrand(Principal principal) {
        LOG.log(Level.INFO, "Add brands for" + principal.getName());
        return "addBrand";
    }

    @ModelAttribute("newBrand")
    public AddBrandViewModel initBrand() {
        return new AddBrandViewModel();
    }

    @PostMapping("/add")
    String registerBrand(@Valid AddBrandViewModel newBrand, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newBrand", newBrand);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newBrand", bindingResult);
            return "redirect:/brands/add";
        }
        brandService.register(newBrand);

        return "redirect:/brands/show";
    }

    @GetMapping("/delete{brandName}")
    String deleteBrand(@PathVariable("brandName") String brandName, Principal principal) {
        brandService.deleteByName(brandName);

        return "redirect:/brands/show";
    }

    @GetMapping("/update/{uuid}")
    String showUpdateForm(@PathVariable("uuid") String uuid, Model model, Principal principal) {
        LOG.log(Level.INFO, "Edit brands for" + principal.getName());
        Optional<EditBrand> editBrand = brandService.findByUuid(uuid);
        model.addAttribute("editBrand", editBrand.orElse(new EditBrand()));
        return "editBrand";
    }

    @ModelAttribute("editBrand")
    public EditBrand editBrand() {
        return new EditBrand();
    }
    @PostMapping("/update/{uuid}")
    String updateBrand(@Valid @ModelAttribute("editBrand") EditBrand editBrand,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editBrand";
        }

        brandService.editBrand(editBrand);
        return "redirect:/brands/show";
    }
}
