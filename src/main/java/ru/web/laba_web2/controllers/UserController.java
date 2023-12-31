package ru.web.laba_web2.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.services.UserService;
import ru.web.laba_web2.viewModel.EditUser;
import ru.web.laba_web2.viewModel.UserProfileView;
import ru.web.laba_web2.viewModel.UserRegistration;


import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void setAuthService(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userRegistration")
    public UserRegistration initForm() {
        return new UserRegistration();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegistration userRegistration,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistration", userRegistration);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistration", bindingResult);

            return "redirect:/users/register";
        }


        this.userService.register(userRegistration);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/users/login";
    }

    @GetMapping("/delete/{username}")
    public String showDeleteConfirmation(@PathVariable("username") String username, Model model) {
        model.addAttribute("username", username);
        return "delete-confirmation";
    }

    @PostMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        userService.deleteByUsername(username);
        SecurityContextHolder.clearContext();
        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        LOG.log(Level.INFO, "Show profile for " + principal.getName());
        String username = principal.getName();
        User user = userService.getUser(username);

        UserProfileView userProfileView = new UserProfileView(
                user.getUuid(),
                username,
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );

        model.addAttribute("user", userProfileView);

        return "profile";
    }




        @GetMapping("/profile/edit")
        public String showEditProfile(Model model, Principal principal) {
            User editUser = userService.findByUsername(principal.getName());
            model.addAttribute("user", editUser);
            return "editUser";
        }

        @PostMapping("/profile/edit")
        public String editProfile(@Valid @ModelAttribute("user") EditUser editUser,
                                  BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return "editUser";
            }

            userService.update(editUser);

            return "redirect:/users/profile";
    }




}
