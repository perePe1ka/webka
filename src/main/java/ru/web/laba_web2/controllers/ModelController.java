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
import ru.web.laba_web2.viewModel.AddBrandViewModel;
import ru.web.laba_web2.viewModel.AddModelViewModel;

import java.util.List;


@Controller
@RequestMapping("/models")
public class ModelController {
    private ModelService modelService;

    private BrandService brandService;

    @Autowired
    public void setBrandService(BrandService brandService) {this.brandService = brandService;}

    @Autowired
    public void setModelService(ModelService modelService) {
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
    public String addModel(Model model) {
        model.addAttribute("availableBrands", brandService.allBrands());
        return "fragments/navbar";
    }

    @ModelAttribute("newModel")
    public AddModelViewModel initModel() {
        return new AddModelViewModel();
    }

    @PostMapping("/register-model")
    String registerModel(@Valid AddModelViewModel newModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newModel", newModel);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newBrand", bindingResult);
            return  "redirect:/";
        }
    @PostMapping("/register-model")
    ModelAndView registerModel(@ModelAttribute ModelDto newModel, ModelAndView modelAndView) {
        modelService.register(newModel);

        return "redirect:/models/show";
    }

    @DeleteMapping("/delete/{uuid}")
    ModelAndView deleteModel(@PathVariable("uuid") String uuid, ModelAndView modelAndView) {
        modelService.deleteByUuid(uuid);
        modelAndView.setViewName("redirect:/models");
        return modelAndView;
    }

    @GetMapping("/get/{uuid}")
    ModelDto getOne(@PathVariable("uuid") String uuid) throws Throwable {
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
