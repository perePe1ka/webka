package ru.web.laba_web2.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String getAllBrands(Model model) {
        model.addAttribute("brands", brandService.getAll());
        return "brandPage";
    }

    @PostMapping("/brands")
    public String createBrand(@ModelAttribute BrandDto brandDto) {
        brandService.create(brandDto);
        return "redirect:/brands";
    }

    @GetMapping("/brands/delete/{uuid}")
    public String deleteBrand(@PathVariable("uuid") String uuid) {
        brandService.deleteByUuid(uuid);
        return "redirect:/brands";
    }

    @GetMapping("/brands/edit/{uuid}")
    public String editBrandForm(@PathVariable("uuid") String uuid, Model model) {
        brandService.findByUuid(uuid).ifPresent(brandDto -> model.addAttribute("brandDto", brandDto));
        return "brandPage";
    }

    @PostMapping("/brands/edit/{uuid}")
    public String editBrand(@ModelAttribute BrandDto brandDto) {
        brandService.editBrand(brandDto);
        return "redirect:/brands";
    }
}
