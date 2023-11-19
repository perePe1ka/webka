package ru.web.laba_web2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.web.laba_web2.controllers.exceptions.BrandNotFoundException;
import ru.web.laba_web2.services.dtos.BrandDto;
import ru.web.laba_web2.services.impl.BrandServiceImpl;

import java.util.List;


@Controller
public class BrandController {
    private BrandServiceImpl brandService;

    @Autowired
    public void setBrandService(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands")
    List<BrandDto> getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("brandPage");
        modelAndView.addObject("brands", brandService.getAll());
        return (List<BrandDto>) modelAndView;
    }

    @PostMapping("/register-brand")
    ModelAndView registerBrand(@ModelAttribute BrandDto newBrand, ModelAndView modelAndView) {
        brandService.register(newBrand);
        modelAndView.setViewName("redirect:/brands");
        return modelAndView;
    }

    @DeleteMapping("/brands/{uuid}")
    ModelAndView deleteBrand(@PathVariable("uuid") String uuid, ModelAndView modelAndView) {
        brandService.deleteByUuid(uuid);
        modelAndView.setViewName("redirect:/brands");
        return modelAndView;
    }

    @GetMapping("/brands/(uuid)")
    BrandDto getOne(@PathVariable("uuid") String uuid) throws Throwable {
        return (BrandDto) brandService.findByUuid(uuid)
                .orElseThrow(() -> new BrandNotFoundException(uuid));
    }

    @PutMapping("/brands/{uuid}")
    ModelAndView editBrand(@ModelAttribute BrandDto brandDto, ModelAndView modelAndView) {
        brandService.editBrand(brandDto);
        modelAndView.setViewName("redirect:/brands");
        return modelAndView;
    }


}
