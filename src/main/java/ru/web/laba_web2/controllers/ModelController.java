package ru.web.laba_web2.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.web.laba_web2.services.ModelService;
import ru.web.laba_web2.services.dtos.ModelDto;


@Controller
@RequestMapping("/")
public class ModelController {
    private ModelService modelService;

    private final ModelMapper modelMapper;

    @Autowired
    public ModelController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/models")
    public ModelAndView getAllModels(ModelAndView modelAndView) {
        modelAndView.addObject("models", modelService.getAll());
        modelAndView.setViewName("modelPage");
        return modelAndView;
    }

    @PostMapping("/register")
    public String registerModel(@ModelAttribute ModelDto modelDto) {
        modelService.register(modelDto);
        return "redirect:/models";
    }

    @DeleteMapping("/models/delete/{uuid}")
    public String deleteModel(@PathVariable("uuid") String uuid) {
        modelService.deleteByUuid(uuid);
        return "redirect:/models";
    }

    @GetMapping("/models/edit/{uuid}")
    public String editModelForm(@PathVariable("uuid") String uuid, Model model) {
        modelService.findByUuid(uuid).ifPresent(modelDto -> model.addAttribute("modelDto", modelDto));
        return "modelPage";
    }

    @PutMapping("/models/edit/{uuid}")
    public String editModel(@ModelAttribute ModelDto modelDto) {
        modelService.editModel(modelDto);
        return "redirect:/models";
    }

//    @GetMapping("/models")
//    public String getAllModels(Model model) {
//        model.addAttribute("models", modelService.getAll());
//        return "modelPage";
//    }

//    @PostMapping("/models")
//    public String createModel(@ModelAttribute ModelDto modelDto) {
//        modelService.create(modelDto);
//        return "redirect:/models";
//    }

}
