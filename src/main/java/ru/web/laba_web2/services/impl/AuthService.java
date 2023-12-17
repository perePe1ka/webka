package ru.web.laba_web2.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.constants.Role;
import ru.web.laba_web2.models.Roles;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.repositories.RolesRepository;
import ru.web.laba_web2.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.web.laba_web2.viewModel.UserRegistration;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    private RolesRepository rolesRepository;


    private PasswordEncoder passwordEncoder;
    @Autowired
    public AuthService(UserRepository userRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setRolesRepository(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public void register(UserRegistration registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        var userRole = rolesRepository.
                findRolesByRole(Role.USER).orElseThrow();

        User user = new User(
                registrationDTO.getUsername(),
                registrationDTO.getFirstName(),
                registrationDTO.getLastName(),
                registrationDTO.getEmail(),
                passwordEncoder.encode(registrationDTO.getPassword())
                );

        user.setRole(List.of(userRole));

        this.userRepository.save(user);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}
