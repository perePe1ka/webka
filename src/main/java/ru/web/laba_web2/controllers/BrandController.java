package ru.web.laba_web2.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.web.laba_web2.controllers.exceptions.BrandNotFoundException;
import ru.web.laba_web2.controllers.exceptions.ModelNotFoundException;
import ru.web.laba_web2.services.dtos.BrandDto;
import ru.web.laba_web2.services.impl.BrandServiceImpl;
import ru.web.laba_web2.viewModel.AddBrandViewModel;
import ru.web.laba_web2.viewModel.EditBrand;


@Controller
@RequestMapping("/brands")
public class BrandController {
    private BrandServiceImpl brandService;

    @Autowired
    public void setBrandService(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/all/{brand-name}")
    ModelAndView getAll(@PathVariable("brand-name") String brandName, ModelAndView modelAndView) {
        modelAndView.setViewName("brandPage");
        modelAndView.addObject("brands", brandService.getAll(brandName));
        return modelAndView;
    }

    @GetMapping("/show")
    public ModelAndView showAllBrands(ModelAndView modelAndView) {
        modelAndView.setViewName("showBrand");
        modelAndView.addObject("brandsInfos", brandService.allBrands());
        return modelAndView;
    }

    @GetMapping("/add")
    public String addBrand() {
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
    String deleteBrand(@PathVariable("brandName") String brandName) {
        brandService.deleteByName(brandName);

        return "redirect:/brands/show";
    }

    @GetMapping("/update/{uuid}")
    String showUpdateForm(@PathVariable("uuid") String uuid, Model model) {
        model.addAttribute("editBrand", brandService.findByUuid(uuid)
                .orElseThrow(() -> new ModelNotFoundException(uuid)));
        return "editBrand";
    }

    @PostMapping("/update/{uuid}")
    String updateBrand(@PathVariable("uuid") String uuid,
                              @Valid EditBrand editBrand,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("editBrand", editBrand);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editBrand", bindingResult);
            return "redirect:/brands/update/" + uuid;
        }

        brandService.editBrand(editBrand);
        return "redirect:/brands/show";
    }

}
