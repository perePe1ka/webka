package ru.web.laba_web2.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.web.laba_web2.services.RolesService;
import ru.web.laba_web2.services.dtos.RolesDto;

@Controller
@RequestMapping("/")
public class RolesController {

    private RolesService rolesService;

    private final ModelMapper modelMapper;
    @Autowired
    public RolesController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setRolesService(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping("/roles")
    public String getAllRoles(Model model) {
        model.addAttribute("roles", rolesService.getAll());
        return "rolesPages";
    }

    @PostMapping("/roles")
    public String createRoles(@ModelAttribute RolesDto rolesDto) {
        rolesService.create(rolesDto);
        return "redirect:/roles";
    }

    @GetMapping("/roles/delete/{uuid}")
    public String deleteRoles(@PathVariable("uuid") String uuid) {
        rolesService.deleteByUuid(uuid);
        return "redirect:/roles";
    }

    @GetMapping("/roles/edit/{uuid}")
    public String editRolesForm(@PathVariable("uuid") String uuid, Model model) {
        rolesService.findByUuid(uuid).ifPresent(rolesDto -> model.addAttribute("rolesDto", rolesDto));
        return "rolesPages";
    }

    @PostMapping("/roles/edit/{uuid}")
    public String editRoles(@ModelAttribute RolesDto rolesDto) {
        rolesService.editRoles(rolesDto);
        return "redirect:/roles";
    }
}
