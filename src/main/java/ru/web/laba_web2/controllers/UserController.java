package ru.web.laba_web2.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.services.IUserService;
import ru.web.laba_web2.viewModel.EditUserViewModel;
import ru.web.laba_web2.viewModel.UserProfileViewModel;
import ru.web.laba_web2.viewModel.UserRegistrationViewModel;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.EnumSet;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {

    private IUserService userService;

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    public void setAuthService(IUserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userRegistration")
    public UserRegistrationViewModel initForm() {
        return new UserRegistrationViewModel();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegistrationViewModel userRegistration,
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

        UserProfileViewModel userProfileView = new UserProfileViewModel(
                user.getUuid(),
                username,
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getImageUrl()
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
    @Value("${upload_users.path}")
    private String uploadPath;

    @PostMapping("/profile/edit")
    public String editProfile(@Valid @ModelAttribute("user") EditUserViewModel editUser,
                              BindingResult bindingResult,
                              @RequestParam("file")MultipartFile file) throws IOException {
        if (bindingResult.hasErrors()) {
            return "editUser";
        }

        if(file != null) {
            File uploadFolder = new File(uploadPath);

            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "1111" +  file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));


            editUser.setImageUrl(resultFileName);
        }

        userService.update(editUser);

        return "redirect:/users/profile";
    }

}
