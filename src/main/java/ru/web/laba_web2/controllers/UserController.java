package ru.web.laba_web2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.web.laba_web2.controllers.exceptions.BrandNotFoundException;
import ru.web.laba_web2.controllers.exceptions.UserNotFoundException;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.services.UserService;
import ru.web.laba_web2.services.dtos.BrandDto;
import ru.web.laba_web2.services.dtos.UserDto;

import java.util.List;


@Controller
@RequestMapping("/")
public class UserController {
    private UserService userService;

//    private ModelAndView modelAndView;
//
//    private RedirectAttributes redirectAttributes;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    ModelAndView getAll(ModelAndView modelAndView) {
        List<UserDto> userDtoList = userService.getAll();
        modelAndView.setViewName("userPage");
        modelAndView.addObject("users", userDtoList);
        return modelAndView;
    }

    @PostMapping("/register")
    ResponseEntity<?> registerUser(@ModelAttribute UserDto newUser, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        userService.register(newUser);
        modelAndView.setViewName("redirect:/users");
        redirectAttributes.addFlashAttribute("addComplete", "Пользователь успешно добавлен");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{uuid}")
    String deleteUser(@PathVariable("uuid") String uuid) {
        userService.deleteByUuid(uuid);
        return "redirect:/users";
    }

    @GetMapping("/users/{uuid}")
    UserDto getOne(@PathVariable("uuid") String uuid) throws Throwable {
        return (UserDto) userService.findByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException(uuid));
    }

    @PutMapping("/users/{uuid}")
    ResponseEntity<?> editUser(@ModelAttribute UserDto userDto, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        userService.editUser(userDto);
        redirectAttributes.addFlashAttribute("editComplete", "Пользователь успешно изменён");
        modelAndView.setViewName("redirect:/users");
        return ResponseEntity.ok().build();
    }
//    @GetMapping("/users")
//    ResponseEntity<List<UserDto>> getAll() {
//        modelAndView.addObject("users", userService.getAll());
//        modelAndView.setViewName("userPage");
//        return ResponseEntity.ok((List<UserDto>) modelAndView);
//    }

//    @GetMapping("/users")
//    public ModelAndView getAllUsers(ModelAndView modelAndView) {
//        modelAndView.addObject("users", userService.getAll());
//        modelAndView.setViewName("userPages");
//        return modelAndView;
//    }
//
//    @PostMapping("/register")
//    public ModelAndView registerUser(@ModelAttribute UserDto userDto, BindingResult result, ModelAndView modelAndView) {
//        if (result.hasErrors()) {
//            modelAndView.setViewName("error");
//        } else {
//            userService.register(userDto);
//            modelAndView.setViewName("redirect:/users");
//        }
//
//        return modelAndView;
//    }
//
//    @DeleteMapping("/users/delete/{uuid}")
//    public ModelAndView deleteUser(@PathVariable("uuid") String uuid, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
//        userService.deleteByUuid(uuid);
//        redirectAttributes.addFlashAttribute("completeDelete", "Пользователь был удалён");
//        modelAndView.setViewName("redirect:/users");
//        return modelAndView;
//    }
//
//    @GetMapping("/users/edit/{uuid}")
//    public ModelAndView editUserForm(@PathVariable("uuid") String uuid, ModelAndView modelAndView) {
//        modelAndView.addObject("users", userService.findByUuid(uuid));
//        modelAndView.setViewName("userPages");
//        return modelAndView;
//    }
//
//    @PutMapping("/users/edit/{uuid}")
//    public ModelAndView editRoles(@ModelAttribute UserDto userDto, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
//        userService.editUser(userDto);
//        redirectAttributes.addFlashAttribute("editComplete", "Юзер успешно изменён");
//        modelAndView.setViewName("redirect:/users");
//        return modelAndView;
//    }


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



