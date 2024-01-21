package ru.web.laba_web2.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.web.laba_web2.constants.Role;
import ru.web.laba_web2.models.Roles;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.repositories.IRolesRepository;
import ru.web.laba_web2.repositories.IUserRepository;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final IUserRepository userRepository;

    private final IRolesRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;
    private final String defaultPassword;

    @Autowired
    public DataInitializer(IUserRepository userRepository, IRolesRepository userRoleRepository, PasswordEncoder passwordEncoder, @Value("${app.default.password}") String defaultPassword) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultPassword = defaultPassword;
    }

    @Override
    public void run(String... args) throws Exception {
        initRoles();
        initUsers();

    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var adminRole = new Roles(Role.ADMIN);
            var normalUserRole = new Roles(Role.USER);
            userRoleRepository.save(adminRole);
            userRoleRepository.save(normalUserRole);
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            initAdmin();
            initNormalUser();
        }
    }

    private void initAdmin() {
        var adminRole = userRoleRepository.
                findRolesByRole(Role.ADMIN).orElseThrow();

        var adminUser = new User("admin", "Vladislav", "Uskov", "admin@mail.ru", passwordEncoder.encode(defaultPassword));
        adminUser.setRole(List.of(adminRole));

        userRepository.save(adminUser);
    }

    private void initNormalUser() {
        var userRole = userRoleRepository.
                findRolesByRole(Role.USER).orElseThrow();

        var normalUser = new User("user", "vlad", "uskov", "user@mail.ru", passwordEncoder.encode(defaultPassword));
        normalUser.setRole(List.of(userRole));

        userRepository.save(normalUser);
    }
}
