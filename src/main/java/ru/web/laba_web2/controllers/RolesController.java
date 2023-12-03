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
@RequestMapping("/roles")
public class RolesController {

    private RolesService rolesService;

    @Autowired
    public void setRolesService(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping("/all")
    ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("rolePage");
        modelAndView.addObject("roles", rolesService.getAll());
        return modelAndView;
    }


    @PostMapping("/register-role")
    ModelAndView registerRole(@ModelAttribute RolesDto newRole, ModelAndView modelAndView) {
        rolesService.register(newRole);
        modelAndView.setViewName("redirect:/roles");
        return modelAndView;
    }

    @GetMapping("/delete{rolesRole}")
    String deleteRole(@PathVariable("uuid") String role) {
        rolesService.deleteByRole(role);

        return "redirect:/roles";
    }

    @GetMapping("/get/(uuid)")
    RolesDto getOne(@PathVariable("uuid") String uuid) throws Throwable {
        return (RolesDto) rolesService.findByUuid(uuid)
                .orElseThrow(() -> new RolesNotFoundException(uuid));
    }

    @PutMapping("/edit/{uuid}")
    ModelAndView editRole(@ModelAttribute RolesDto rolesDto, ModelAndView modelAndView) {
        rolesService.editRoles(rolesDto);
        modelAndView.setViewName("redirect:/roles");
        return modelAndView;
    }

}
