package ru.web.laba_web2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.web.laba_web2.controllers.exceptions.BrandNotFoundException;
import ru.web.laba_web2.services.dtos.BrandDto;
import ru.web.laba_web2.services.impl.BrandServiceImpl;

import java.util.List;


@Controller
public class BrandController {
    private BrandServiceImpl brandService;

    private ModelAndView modelAndView;

    private RedirectAttributes redirectAttributes;

    @Autowired
    public void setBrandService(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands")
    ResponseEntity<List<BrandDto>> getAll() {
        modelAndView.addObject("brands", brandService.getAll());
        modelAndView.setViewName("brandPage");
        return ResponseEntity.ok((List<BrandDto>) modelAndView);
    }

    @PostMapping("/register")
    ResponseEntity<?> registerBrand(@ModelAttribute BrandDto newBrand) {
        brandService.register(newBrand);
        modelAndView.setViewName("redirect:/brands");
        redirectAttributes.addFlashAttribute("addComplete", "Бренд успешно добавлен");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/brands/{uuid}")
    void deleteBrand(@PathVariable("uuid") String uuid) {
        brandService.deleteByUuid(uuid);
        redirectAttributes.addFlashAttribute("deleteComplete", "Бренд успешно удалён");
        modelAndView.setViewName("redirect:/brands");
    }

    @GetMapping("/brands/(uuid)")
    BrandDto getOne(@PathVariable("uuid") String uuid) throws Throwable {
        return (BrandDto) brandService.findByUuid(uuid)
                .orElseThrow(() -> new BrandNotFoundException(uuid));
    }

    @PutMapping("/brands/{uuid}")
    ResponseEntity<?> editBrand(@ModelAttribute BrandDto brandDto) {
        brandService.editBrand(brandDto);
        redirectAttributes.addFlashAttribute("editComplete", "Бренд успешно изменён");
        modelAndView.setViewName("redirect:/brands");
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/brands")
//    public ModelAndView getAllBrands(ModelAndView modelAndView) {
//        modelAndView.addObject("brands", brandService.getAll());
//        modelAndView.setViewName("brandPage");
//        return modelAndView;
//    }
//
//    @PostMapping("/register")
//    public ModelAndView registerBrand(@ModelAttribute BrandDto brandDto, BindingResult result, ModelAndView modelAndView) {
//        if (result.hasErrors()) {
//            modelAndView.setViewName("error");
//        } else {
//            brandService.register(brandDto);
//            modelAndView.setViewName("redirect:/brands");
//        }
//        return modelAndView;
//    }
//
//    @DeleteMapping("/brands/delete/{uuid}")
//    public ModelAndView deleteBrand(@PathVariable("uuid") String uuid, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
//        brandService.deleteByUuid(uuid);
//        redirectAttributes.addFlashAttribute("completeDelete", "Бренд был удалён");
//        modelAndView.setViewName("redirect:/brands");
//        return modelAndView;
//    }
//
//    @GetMapping("/brands/edit/{uuid}")
//    public ModelAndView editBrandForm(@PathVariable("uuid") String uuid, ModelAndView modelAndView) {
//        modelAndView.addObject("brands", brandService.findByUuid(uuid));
//        modelAndView.setViewName("brandPage");
//        return modelAndView;
//    }
//
//    @PutMapping("/brands/edit/{uuid}")
//    public ModelAndView editBrand(@ModelAttribute BrandDto brandDto, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
//        brandService.editBrand(brandDto);
//        redirectAttributes.addFlashAttribute("editComplete", "Бренд успешно изменён");
//        modelAndView.setViewName("redirect:/brands");
//        return modelAndView;
//    }
//
////    @GetMapping("/brands")
////    public String getAllBrands(Model model) {
////        model.addAttribute("brands", brandService.getAll());
////        return "brandPage";
////    }
//
////    @PostMapping("/brands")
////    public String createBrand(@ModelAttribute BrandDto brandDto) {
////        brandService.create(brandDto);
////        return "redirect:/brands";
////    }
}
