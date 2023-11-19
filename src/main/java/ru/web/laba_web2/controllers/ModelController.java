package ru.web.laba_web2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.web.laba_web2.controllers.exceptions.ModelNotFoundException;
import ru.web.laba_web2.services.ModelService;
import ru.web.laba_web2.services.dtos.ModelDto;

import java.util.List;


@Controller
@RequestMapping("/")
public class ModelController {
    private ModelService modelService;


    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/models")
    List<ModelDto> getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("modelPage");
        modelAndView.addObject("models", modelService.getAll());
        return (List<ModelDto>) modelAndView;
    }

    @PostMapping("/register-model")
    ModelAndView registerModel(@ModelAttribute ModelDto newModel, ModelAndView modelAndView) {
        modelService.register(newModel);
        modelAndView.setViewName("redirect:/models");
        return modelAndView;
    }

    @DeleteMapping("/models/{uuid}")
    ModelAndView deleteModel(@PathVariable("uuid") String uuid, ModelAndView modelAndView) {
        modelService.deleteByUuid(uuid);
        modelAndView.setViewName("redirect:/models");
        return modelAndView;
    }

    @GetMapping("/models/(uuid)")
    ModelDto getOne(@PathVariable("uuid") String uuid) throws Throwable {
        return (ModelDto) modelService.findByUuid(uuid)
                .orElseThrow(() -> new ModelNotFoundException(uuid));
    }

    @PutMapping("/models/{uuid}")
    ModelAndView editModel(@ModelAttribute ModelDto modelDto, ModelAndView modelAndView) {
        modelService.editModel(modelDto);
        modelAndView.setViewName("redirect:/models");
        return modelAndView;
    }

    @GetMapping("/sortedByYear")
    List<ModelDto> getModelsSortedByYear() {
        return modelService.getModelsSortedByYear();
    }



}
