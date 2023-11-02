package ru.web.laba_web2.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.web.laba_web2.services.dtos.BrandDto;
import ru.web.laba_web2.services.impl.BrandServiceImpl;


@Controller
public class BrandController {
    private BrandServiceImpl brandService;

    private final ModelMapper modelMapper;

    @Autowired
    public BrandController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setBrandService(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }


    @GetMapping("/brands")
    public ModelAndView getAllBrands(ModelAndView modelAndView) {
        modelAndView.addObject("brands", brandService.getAll());
        modelAndView.setViewName("brandPage");
        return modelAndView;
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute BrandDto brandDto) {
        brandService.register(brandDto);
        return "redirect:/brands";
    }

    @DeleteMapping("/brands/delete/{uuid}")
    public String deleteBrand(@PathVariable("uuid") String uuid) {
        brandService.deleteByUuid(uuid);
        return "redirect:/brands";
    }

    @GetMapping("/brands/edit/{uuid}")
    public String editBrandForm(@PathVariable("uuid") String uuid, Model model) {
        brandService.findByUuid(uuid).ifPresent(brandDto -> model.addAttribute("brandDto", brandDto));
        return "brandPage";
    }

    @PutMapping("/brands/edit/{uuid}")
    public String editBrand(@ModelAttribute BrandDto brandDto) {
        brandService.editBrand(brandDto);
        return "redirect:/brands";
    }

//    @GetMapping("/brands")
//    public String getAllBrands(Model model) {
//        model.addAttribute("brands", brandService.getAll());
//        return "brandPage";
//    }

//    @PostMapping("/brands")
//    public String createBrand(@ModelAttribute BrandDto brandDto) {
//        brandService.create(brandDto);
//        return "redirect:/brands";
//    }
}
