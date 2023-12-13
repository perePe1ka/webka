package ru.web.laba_web2.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.web.laba_web2.controllers.exceptions.UserNotFoundException;
import ru.web.laba_web2.services.RolesService;
import ru.web.laba_web2.services.UserService;
import ru.web.laba_web2.services.dtos.UserDto;
import ru.web.laba_web2.viewModel.EditModel;
import ru.web.laba_web2.viewModel.EditUser;

import java.security.Principal;


@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    private RolesService rolesService;

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRolesService(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping("/all")
    ModelAndView getAll(Principal principal, ModelAndView modelAndView) {
        LOG.log(Level.INFO, "Showw all Users for " + principal.getName());

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

    @ModelAttribute("editUser")
    public EditUser editUser() {
        return new EditUser();
    }

    @GetMapping("/delete{userUsername}")
    String deleteUser(@PathVariable("userUsername") String username) {
        userService.deleteByUserName(username);

        return "redirect:/users";
    }

    @GetMapping("/update/{uuid}")
    String showUpdateForm(@PathVariable("uuid") String uuid, Model model) throws Throwable {
        model.addAttribute("availableRoles", rolesService.getAll());

        model.addAttribute("editUser", userService.findByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException(uuid)));
        return "editUser";
    }

    @PostMapping("/update/{uuid}")
    String updateUser(@PathVariable("uuid") String uuid,
                             @Valid EditUser editUser,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("editUser", editUser);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editUser", bindingResult);
            return "redirect:/users/update/" + uuid;
        }

        userService.editUser(editUser);
        return "redirect:/users/all";
    }

}



