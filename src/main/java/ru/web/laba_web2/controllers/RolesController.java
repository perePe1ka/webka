package ru.web.laba_web2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<List<RolesDto>> getAll() {
        modelAndView.addObject("roles", rolesService.getAll());
        modelAndView.setViewName("rolesPage");
        return ResponseEntity.ok((List<RolesDto>) modelAndView);
    }

    @PostMapping("/register")
    ResponseEntity<?> registerRole(@ModelAttribute RolesDto newRole) {
        rolesService.register(newRole);
        modelAndView.setViewName("redirect:/roles");
        redirectAttributes.addFlashAttribute("addComplete", "Роль успешно добавлен");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/roles/{uuid}")
    void deleteRole(@PathVariable("uuid") String uuid) {
        rolesService.deleteByUuid(uuid);
        redirectAttributes.addFlashAttribute("deleteComplete", "Роль успешно удалён");
        modelAndView.setViewName("redirect:/roles");
    }

    @GetMapping("/roles/(uuid)")
    RolesDto getOne(@PathVariable("uuid") String uuid) throws Throwable {
        return (RolesDto) rolesService.findByUuid(uuid)
                .orElseThrow(() -> new RolesNotFoundException(uuid));
    }

    @PutMapping("/roles/{uuid}")
    ResponseEntity<?> editRole(@ModelAttribute RolesDto rolesDto) {
        rolesService.editRoles(rolesDto);
        redirectAttributes.addFlashAttribute("editComplete", "Роль успешно изменён");
        modelAndView.setViewName("redirect:/roles");
        return ResponseEntity.ok().build();
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
