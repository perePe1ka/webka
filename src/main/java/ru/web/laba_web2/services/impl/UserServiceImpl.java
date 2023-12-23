package ru.web.laba_web2.services.impl;

import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.constants.Role;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.repositories.RolesRepository;
import ru.web.laba_web2.repositories.UserRepository;
import ru.web.laba_web2.services.RolesService;
import ru.web.laba_web2.services.UserService;
import ru.web.laba_web2.services.dtos.UserDto;
import ru.web.laba_web2.utils.ValidationUtil;
import ru.web.laba_web2.viewModel.EditUser;
import ru.web.laba_web2.viewModel.UserRegistration;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class UserServiceImpl implements UserService<String> {
    private final ModelMapper modelMapper;
    private RolesRepository rolesRepository;
    private UserRepository userRepository;
    private ValidationUtil validationUtil;
    private RolesService rolesService;

    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRolesRepository(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRolesService(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @Override
    @CacheEvict(cacheNames = "users", allEntries = true)
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

    @Override
    @CacheEvict(cacheNames = "users", allEntries = true)
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }


    @Override
    public Optional<UserDto> findByUuid(String uuid) {
        return Optional.ofNullable(modelMapper.map(userRepository.findByUuid(uuid), UserDto.class));
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }

    @Override
    @Cacheable("users")
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    @CacheEvict(cacheNames = "users", allEntries = true)
    public void update(EditUser editUser) {
        Optional<User> existingUserOptional = userRepository.findByUuid(editUser.getUuid());
        User existingUser = existingUserOptional.orElseThrow(() -> new RuntimeException());

        if (StringUtils.isNotBlank(editUser.getPassword())) {
            if (!editUser.getPassword().equals(editUser.getConfirmPassword())) {
                throw new RuntimeException("passwords.match");
            }
            existingUser.setPassword(passwordEncoder.encode(editUser.getPassword()));
        }
        existingUser.setUsername(editUser.getUsername());
        existingUser.setFirstName(editUser.getFirstName());
        existingUser.setLastName(editUser.getLastName());
//        existingUser.setActive(editUser.isActive());
        existingUser.setImageUrl(editUser.getImageUrl());
        existingUser.setEmail(editUser.getEmail());

        userRepository.save(existingUser);
    }

    @Override
    public User findByUsername(String userName) {
        return this.userRepository.findUserByUsername(userName);
    }

}
