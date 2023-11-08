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

//    private ModelAndView modelAndView;
//
//    private RedirectAttributes redirectAttributes;

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

    @PostMapping("/register")
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

//    @GetMapping("/models")
//    public ModelAndView getAllModels(ModelAndView modelAndView) {
//        modelAndView.addObject("models", modelService.getAll());
//        modelAndView.setViewName("modelPage");
//        return modelAndView;
//    }
//
//    @PostMapping("/register")
//    public ModelAndView registerModel(@ModelAttribute ModelDto modelDto, BindingResult result, ModelAndView modelAndView) {
//        if (result.hasErrors()) {
//            modelAndView.setViewName("error");
//        } else {
//            modelService.register(modelDto);
//            modelAndView.setViewName("redirect:/models");
//        }
//
//        return modelAndView;
//    }
//
//    @DeleteMapping("/models/delete/{uuid}")
//    public ModelAndView deleteModel(@PathVariable("uuid") String uuid, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
//        modelService.deleteByUuid(uuid);
//        redirectAttributes.addFlashAttribute("completeDelete", "Модель была удалена");
//        modelAndView.setViewName("redirect:/models");
//        return modelAndView;
//    }
//
//    @GetMapping("/models/edit/{uuid}")
//    public ModelAndView editModelForm(@PathVariable("uuid") String uuid, ModelAndView modelAndView) {
//        modelAndView.addObject("models", modelService.findByUuid(uuid));
//        modelAndView.setViewName("modelPage");
//        return modelAndView;
//    }
//
//    @PutMapping("/models/edit/{uuid}")
//    public ModelAndView editModel(@ModelAttribute ModelDto modelDto, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
//        modelService.editModel(modelDto);
//        redirectAttributes.addFlashAttribute("editComplete", "Модель успешно изменена");
//        modelAndView.setViewName("redirect:/models");
//        return modelAndView;
//    }
//
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

//    @PostMapping("/register")
//    public String registerModel(@ModelAttribute ModelDto modelDto, BindingResult result) {
//        if (result.hasErrors()) {
//            return "error";
//        }
//        modelService.register(modelDto);
//        return "redirect:/models";
//    }

}
