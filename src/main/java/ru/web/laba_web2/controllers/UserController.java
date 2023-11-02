package ru.web.laba_web2.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.web.laba_web2.services.UserService;
import ru.web.laba_web2.services.dtos.UserDto;

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
    public ModelAndView getAllUsers(ModelAndView modelAndView) {
        modelAndView.addObject("users", userService.getAll());
        modelAndView.setViewName("userPages");
        return modelAndView;
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDto userDto) {
        userService.register(userDto);
        return "redirect:/users";
    }

    @DeleteMapping("/users/delete/{uuid}")
    public String deleteUser(@PathVariable("uuid") String uuid) {
        userService.deleteByUuid(uuid);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{uuid}")
    public String editUserForm(@PathVariable("uuid") String uuid, Model model) {
        userService.findByUuid(uuid).ifPresent(userDto -> model.addAttribute("userDto", userDto));
        return "userPages";
    }

    @PutMapping("/users/edit/{uuid}")
    public String editRoles(@ModelAttribute UserDto userDto) {
        userService.editUser(userDto);
        return "redirect:/users";
    }

//    @GetMapping("/users")
//    public String getAllUsers(Model model) {
//        model.addAttribute("users", userService.getAll());
//        return "userPages";
//    }

//    @PostMapping("/users")
//    public String createUser(UserDto userDto) {
//        userService.create(userDto);
//        return "redirect:/users";
//    }
}



