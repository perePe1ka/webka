package ru.web.laba_web2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.web.laba_web2.controllers.exceptions.UserNotFoundException;
import ru.web.laba_web2.services.UserService;
import ru.web.laba_web2.services.dtos.UserDto;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("userPage");
        modelAndView.addObject("users", userService.getAll());
        return modelAndView;
    }

    @PostMapping("/register-user")
    ModelAndView registerUser(@ModelAttribute UserDto newUser, ModelAndView modelAndView) {
        userService.register(newUser);
        modelAndView.setViewName("redirect:/users");
        return modelAndView;
    }


    @DeleteMapping("/delete/{uuid}")
    ModelAndView deleteUser(@PathVariable("uuid") String uuid, ModelAndView modelAndView) {
        userService.deleteByUuid(uuid);
        modelAndView.setViewName("redirect:/users");
        return modelAndView;
    }

    @GetMapping("/get/{uuid}")
    UserDto getOne(@PathVariable("uuid") String uuid) throws Throwable {
        return (UserDto) userService.findByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException(uuid));
    }
    @PutMapping("/edit/{uuid}")
    ModelAndView editUser(@ModelAttribute UserDto userDto, ModelAndView modelAndView) {
        userService.editUser(userDto);
        modelAndView.setViewName("redirect:/users");
        return modelAndView;
    }

}



