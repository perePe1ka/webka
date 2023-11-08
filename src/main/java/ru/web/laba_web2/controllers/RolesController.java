package ru.web.laba_web2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.web.laba_web2.controllers.exceptions.RolesNotFoundException;
import ru.web.laba_web2.services.RolesService;
import ru.web.laba_web2.services.dtos.RolesDto;

import java.util.List;

@Controller
@RequestMapping("/")
public class RolesController {

    private RolesService rolesService;

    private ModelAndView modelAndView;

    private RedirectAttributes redirectAttributes;

    @Autowired
    public void setRolesService(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping("/roles")
    List<RolesDto> getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("rolePage");
        modelAndView.addObject("roles", rolesService.getAll());
        return (List<RolesDto>) modelAndView;
    }


    @PostMapping("/register")
    ModelAndView registerRole(@ModelAttribute RolesDto newRole, ModelAndView modelAndView) {
        rolesService.register(newRole);
        modelAndView.setViewName("redirect:/roles");
        return modelAndView;
    }

    @DeleteMapping("/roles/{uuid}")
    ModelAndView deleteRole(@PathVariable("uuid") String uuid, ModelAndView modelAndView) {
        rolesService.deleteByUuid(uuid);
        modelAndView.setViewName("redirect:/roles");
        return modelAndView;
    }

    @GetMapping("/roles/(uuid)")
    RolesDto getOne(@PathVariable("uuid") String uuid) throws Throwable {
        return (RolesDto) rolesService.findByUuid(uuid)
                .orElseThrow(() -> new RolesNotFoundException(uuid));
    }

    @PutMapping("/roles/{uuid}")
    ModelAndView editRole(@ModelAttribute RolesDto rolesDto, ModelAndView modelAndView) {
        rolesService.editRoles(rolesDto);
        modelAndView.setViewName("redirect:/roles");
        return modelAndView;
    }

//    @GetMapping("/roles")
//    public ModelAndView getAllRoles(ModelAndView modelAndView) {
//        modelAndView.addObject("roles", rolesService.getAll());
//        modelAndView.setViewName("rolesPage");
//        return modelAndView;
//    }
//
//    @PostMapping("/register")
//    public ModelAndView registerRole(@ModelAttribute RolesDto rolesDto, BindingResult result, ModelAndView modelAndView) {
//        if (result.hasErrors()) {
//            modelAndView.setViewName("error");
//        } else {
//            rolesService.register(rolesDto);
//            modelAndView.setViewName("redirect:/roles");
//        }
//
//        return modelAndView;
//    }
//
//    @DeleteMapping("/roles/delete/{uuid}")
//    public ModelAndView deleteRoles(@PathVariable("uuid") String uuid, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
//        rolesService.deleteByUuid(uuid);
//        redirectAttributes.addFlashAttribute("completeDelete", "Роль была удалена");
//        modelAndView.setViewName("redirect:/roles");
//        return modelAndView;
//    }
//
//    @GetMapping("/roles/edit/{uuid}")
//    public ModelAndView editRolesForm(@PathVariable("uuid") String uuid, ModelAndView modelAndView) {
//        modelAndView.addObject("roles", rolesService.findByUuid(uuid));
//        modelAndView.setViewName("rolesPages");
//        return modelAndView;
//    }
//
//    @PutMapping("/roles/edit/{uuid}")
//    public ModelAndView editRoles(@ModelAttribute RolesDto rolesDto, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
//        rolesService.editRoles(rolesDto);
//        redirectAttributes.addFlashAttribute("editComplete", "Роль успешно изменена");
//        modelAndView.setViewName("redirect:/roles");
//        return modelAndView;
//    }

//    @GetMapping("/roles")
//    public String getAllRoles(Model model) {
//        model.addAttribute("roles", rolesService.getAll());
//        return "rolesPages";
//    }
//
//    @PostMapping("/roles")
//    public String createRoles(@ModelAttribute RolesDto rolesDto) {
//        rolesService.create(rolesDto);
//        return "redirect:/roles";
//    }
}
