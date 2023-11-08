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

    @GetMapping("/offers")
    List<UserDto> getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("userPage");
        modelAndView.addObject("users", userService.getAll());
        return (List<UserDto>) modelAndView;
    }

    @PostMapping("/register")
    ModelAndView registerUser(@ModelAttribute UserDto newUser, ModelAndView modelAndView) {
        userService.register(newUser);
        modelAndView.setViewName("redirect:/offers");
        return modelAndView;
    }


    @DeleteMapping("/users/{uuid}")
    ModelAndView deleteUser(@PathVariable("uuid") String uuid, ModelAndView modelAndView) {
        userService.deleteByUuid(uuid);
        modelAndView.setViewName("redirect:/offers");
        return modelAndView;
    }

    @GetMapping("/users/{uuid}")
    UserDto getOne(@PathVariable("uuid") String uuid) throws Throwable {
        return (UserDto) userService.findByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException(uuid));
    }
    @PutMapping("/users/{uuid}")
    ModelAndView editUser(@ModelAttribute UserDto userDto, ModelAndView modelAndView) {
        userService.editUser(userDto);
        modelAndView.setViewName("redirect:/offers");
        return modelAndView;
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




    //    @PostMapping("/register")
//    ResponseEntity<?> registerUser(@ModelAttribute UserDto newUser, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
//        userService.register(newUser);
//        modelAndView.setViewName("redirect:/users");
//        redirectAttributes.addFlashAttribute("addComplete", "Пользователь успешно добавлен");
//        return ResponseEntity.ok().build();
//    }


    //    @PutMapping("/users/{uuid}")
//    ResponseEntity<?> editUser(@ModelAttribute UserDto userDto, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
//        userService.editUser(userDto);
//        redirectAttributes.addFlashAttribute("editComplete", "Пользователь успешно изменён");
//        modelAndView.setViewName("redirect:/users");
//        return ResponseEntity.ok().build();
//    }
}



