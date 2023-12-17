package ru.web.laba_web2.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.services.impl.AuthService;
import ru.web.laba_web2.viewModel.UserProfileView;
import ru.web.laba_web2.viewModel.UserRegistration;


import java.security.Principal;

@Controller
@RequestMapping("/users")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("userRegistrationDto")
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

        this.authService.register(userRegistration);

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

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String username = principal.getName();
        User user = authService.getUser(username);

        UserProfileView userProfileView = new UserProfileView(
                username,
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );

        model.addAttribute("user", userProfileView);

        return "profile";
    }
}
