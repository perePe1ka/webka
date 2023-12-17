package ru.web.laba_web2.init;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.web.laba_web2.constants.Role;
import ru.web.laba_web2.models.Roles;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.repositories.RolesRepository;
import ru.web.laba_web2.repositories.UserRepository;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;

    private final RolesRepository userRoleRepository;

//    private final PasswordEncoder passwordEncoder;
//    private final String defaultPassword;


    public DataInitializer(UserRepository userRepository, RolesRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.defaultPassword = defaultPassword;
    }

    @Override
    public void run(String... args) throws Exception {
        initRoles();
//        initNormalUser();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var adminRole = new Roles(Role.ADMIN);
            var normalUserRole = new Roles(Role.USER);
            userRoleRepository.save(adminRole);
            userRoleRepository.save(normalUserRole);
        }
    }

//    private void initUsers() {
//        if (userRepository.count() == 0) {
////            initAdmin();
////            initModerator();
////            initNormalUser();
//        }
//    }
//
//    private void initAdmin(){
//        var adminRole = userRoleRepository.
//                findRoleByName(UserRoles.ADMIN).orElseThrow();
//
//        var adminUser = new User("admin", passwordEncoder.encode(defaultPassword), "admin@example.com", "Admin Adminovich", 30);
//        adminUser.setRoles(List.of(adminRole));
//
//        userRepository.save(adminUser);
//    }
//
//    private void initModerator(){
//
//        var moderatorRole = userRoleRepository.
//                findRoleByName(UserRoles.MODERATOR).orElseThrow();
//
//        var moderatorUser = new User("moderator", passwordEncoder.encode(defaultPassword), "moderator@example.com", "Moder Moderovich", 24);
//        moderatorUser.setRoles(List.of(moderatorRole));
//
//        userRepository.save(moderatorUser);
//    }
//
//    private void initNormalUser(){
//        var userRole = userRoleRepository.
//                findRolesByRole(Role.USER).orElseThrow();
//
//        var normalUser = new User("user", passwordEncoder.encode(defaultPassword), "user@example.com", "User Userovich", "22");
//        normalUser.setRole(List.of(userRole));
//
//        userRepository.save(normalUser);
//    }
}
