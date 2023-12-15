package ru.web.laba_web2.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.web.laba_web2.controllers.exceptions.ModelNotFoundException;
import ru.web.laba_web2.services.dtos.ModelDto;
import ru.web.laba_web2.services.impl.BrandServiceImpl;
import ru.web.laba_web2.services.impl.ModelServiceImpl;
import ru.web.laba_web2.viewModel.AddModelViewModel;
import ru.web.laba_web2.viewModel.EditModel;
import ru.web.laba_web2.viewModel.EditOffer;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/models")
public class ModelController {
    private ModelServiceImpl modelService;

    private BrandServiceImpl brandService;

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Autowired
    public void setBrandService(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @Autowired
    public void setModelService(ModelServiceImpl modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/all/{model-name}")
    ModelAndView getAll(@PathVariable("model-name") String modelName, ModelAndView modelAndView, Principal principal) {
        LOG.log(Level.INFO, "Show all models for " + principal.getName());
        modelAndView.setViewName("modelPage");
        modelAndView.addObject("models", modelService.getAll(modelName));
        return modelAndView;
    }

    @GetMapping("/show")
    public ModelAndView showAllModels(Principal principal, ModelAndView modelAndView) {
        LOG.log(Level.INFO, "Show briefly models for " + principal.getName());

        modelAndView.setViewName("showModel");
        modelAndView.addObject("modelsInfos", modelService.allModels());
        return modelAndView;
    }

    @GetMapping("/add")
    public String addBrand(Model model, Principal principal) {
        LOG.log(Level.INFO, "Add models for" + principal.getName());
        model.addAttribute("availableBrands", brandService.allBrands());

        return "addModel";
    }

    @ModelAttribute("newModel")
    public AddModelViewModel initModel() {
        return new AddModelViewModel();
    }

    @ModelAttribute("editModel")
    public EditModel editModel() {
        return new EditModel();
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

    @GetMapping("/update/{uuid}")
    String showUpdateForm(@PathVariable("uuid") String uuid, Model model, Principal principal) {
        LOG.log(Level.INFO, "Edit models for" + principal.getName());
        model.addAttribute("availableBrands", brandService.allBrands());

        model.addAttribute("editModel", modelService.findByUuid(uuid)
                .orElseThrow(() -> new ModelNotFoundException(uuid)));
        return "editModel";
    }

    @PostMapping("/update/{uuid}")
    String updateModel(@PathVariable("uuid") String uuid,
                       @Valid EditModel editModel,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("editModel", editModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editModel", bindingResult);
            return "redirect:/models/update/" + uuid;
        }

        modelService.editModel(editModel);
        return "redirect:/models/show";
    }
}
