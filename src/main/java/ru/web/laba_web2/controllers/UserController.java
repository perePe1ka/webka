package ru.web.laba_web2.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.services.UserService;
import ru.web.laba_web2.services.dtos.RolesDto;
import ru.web.laba_web2.services.dtos.UserDto;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {
    private UserService userService;

    private final ModelMapper modelMapper;

    @Autowired
    public UserController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "userPages";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute UserDto userDto) {
        userService.create(userDto);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{uuid}")
    public String deleteUser(@PathVariable("uuid") String uuid) {
        userService.deleteByUuid(uuid);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{uuid}")
    public String editUserForm(@PathVariable("uuid") String uuid, Model model) {
        userService.findByUuid(uuid).ifPresent(userDto -> model.addAttribute("userDto", userDto));
        return "userPages";
    }

    @PostMapping("/users/edit/{uuid}")
    public String editRoles(@ModelAttribute UserDto userDto) {
        userService.editUser(userDto);
        return "redirect:/users";
    }
}



