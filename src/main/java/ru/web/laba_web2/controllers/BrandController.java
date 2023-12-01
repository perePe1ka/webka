package ru.web.laba_web2.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.web.laba_web2.controllers.exceptions.BrandNotFoundException;
import ru.web.laba_web2.services.dtos.BrandDto;
import ru.web.laba_web2.services.impl.BrandServiceImpl;
import ru.web.laba_web2.viewModel.AddBrandViewModel;


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
        return "fragments/navbar";
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
            return  "redirect:/";
        }
        brandService.register(newBrand);

        return "redirect:/brands/show";
    }

    @DeleteMapping("/delete/{uuid}")
    ModelAndView deleteBrand(@PathVariable("uuid") String uuid, ModelAndView modelAndView) {
        brandService.deleteByUuid(uuid);
        modelAndView.setViewName("redirect:/brands");
        return modelAndView;
    }

    @GetMapping("/get/(uuid)")
    BrandDto getOne(@PathVariable("uuid") String uuid) throws Throwable {
        return (BrandDto) brandService.findByUuid(uuid)
                .orElseThrow(() -> new BrandNotFoundException(uuid));
    }

    @PutMapping("/edit/{uuid}")
    ModelAndView editBrand(@ModelAttribute BrandDto brandDto, ModelAndView modelAndView) {
        brandService.editBrand(brandDto);
        modelAndView.setViewName("redirect:/brands");
        return modelAndView;
    }
}
