package ru.web.laba_web2.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.web.laba_web2.controllers.exceptions.ModelNotFoundException;
import ru.web.laba_web2.services.BrandService;
import ru.web.laba_web2.services.ModelService;
import ru.web.laba_web2.services.dtos.ModelDto;
import ru.web.laba_web2.services.impl.BrandServiceImpl;
import ru.web.laba_web2.services.impl.ModelServiceImpl;
import ru.web.laba_web2.viewModel.AddModelViewModel;

import java.util.List;


@Controller
@RequestMapping("/models")
public class ModelController {
    private ModelServiceImpl modelService;

    private BrandServiceImpl brandService;

    @Autowired
    public void setBrandService(BrandServiceImpl brandService) {this.brandService = brandService;}

    @Autowired
    public void setModelService(ModelServiceImpl modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/all/{model-name}")
    ModelAndView getAll(@PathVariable("model-name")  String modelName, ModelAndView modelAndView) {
        modelAndView.setViewName("modelPage");
        modelAndView.addObject("models", modelService.getAll(modelName));
        return modelAndView;
    }

    @GetMapping("/show")
    public ModelAndView showAllModels(ModelAndView modelAndView) {
        modelAndView.setViewName("showModel");
        modelAndView.addObject("modelsInfos", modelService.allModels());
        return modelAndView;
    }

    @GetMapping("/add")
    public String addBrand(Model model) {
        model.addAttribute("availableBrands", brandService.allBrands());

        return "addModel";
    }

    @ModelAttribute("newModel")
    public AddModelViewModel initModel() {
        return new AddModelViewModel();
    }

    @PostMapping("/add")
    String registerModel(@Valid AddModelViewModel newModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newModel", newModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newModel", bindingResult);

            return "redirect:/models/add";
        }
        modelService.register(newModel);

        return "redirect:/models/show";
    }

    @GetMapping("/delete{modelName}")
    String deleteModel(@PathVariable("modelName") String modelName) {
        modelService.deleteByModelName(modelName);

        return "redirect:/models/show";
    }

    @GetMapping("/get/{uuid}")
    ModelDto getOne(@PathVariable("uuid") String uuid) {
        return (ModelDto) modelService.findByUuid(uuid)
                .orElseThrow(() -> new ModelNotFoundException(uuid));
    }

    @PutMapping("/edit/{uuid}")
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
