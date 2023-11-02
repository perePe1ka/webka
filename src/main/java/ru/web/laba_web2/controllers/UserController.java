package ru.web.laba_web2.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.web.laba_web2.services.UserService;
import ru.web.laba_web2.services.dtos.UserDto;

@Controller
@RequestMapping("/")
public class UserController {
    private UserService userService;
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
    public ModelAndView registerUser(@ModelAttribute UserDto userDto, BindingResult result, ModelAndView modelAndView) {
        if (result.hasErrors()) {
            modelAndView.setViewName("error");
        } else {
            userService.register(userDto);
            modelAndView.setViewName("redirect:/users");
        }

        return modelAndView;
    }

    @DeleteMapping("/users/delete/{uuid}")
    public ModelAndView deleteUser(@PathVariable("uuid") String uuid, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        userService.deleteByUuid(uuid);
        redirectAttributes.addFlashAttribute("completeDelete", "Пользователь был удалён");
        modelAndView.setViewName("redirect:/users");
        return modelAndView;
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



