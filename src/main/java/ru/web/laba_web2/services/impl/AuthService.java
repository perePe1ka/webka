//package ru.web.laba_web2.services.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ru.web.laba_web2.repositories.RolesRepository;
//import ru.web.laba_web2.repositories.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import java.util.List;
//@Service
//public class AuthService {
//
//    private UserRepository userRepository;
//
//    private RolesRepository rolesRepository;
//
//
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public AuthService(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public void setRolesRepository(RolesRepository rolesRepository) {
//        this.rolesRepository = rolesRepository;
//    }
//
//    public void register(UserRegistrationDto registrationDTO) {
//        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
//            throw new RuntimeException("passwords.match");
//        }
//
//        Optional<User> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());
//
//        if (byEmail.isPresent()) {
//            throw new RuntimeException("email.used");
//        }
//
//        var userRole = userRoleRepository.
//                findRoleByName(UserRoles.USER).orElseThrow();
//
//        User user = new User(
//                registrationDTO.getUsername(),
//                passwordEncoder.encode(registrationDTO.getPassword()),
//                registrationDTO.getEmail(),
//                registrationDTO.getFullname(),
//                registrationDTO.getAge()
//        );
//
//        user.setRoles(List.of(userRole));
//
//        this.userRepository.save(user);
//    }
//
//    public User getUser(String username) {
//        return userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
//    }
//}
