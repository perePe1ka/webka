package ru.web.laba_web2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.constants.Role;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.repositories.IRolesRepository;
import ru.web.laba_web2.repositories.IUserRepository;
import ru.web.laba_web2.services.IRolesService;
import ru.web.laba_web2.services.IUserService;
import ru.web.laba_web2.services.dtos.UserDto;
import ru.web.laba_web2.utils.ValidationUtil;
import ru.web.laba_web2.viewModel.EditUserViewModel;
import ru.web.laba_web2.viewModel.UserRegistrationViewModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
//@EnableCaching
public class UserServiceImpl implements IUserService<String> {
    private final ModelMapper modelMapper;
    private IRolesRepository rolesRepository;
    private IUserRepository userRepository;
    private ValidationUtil validationUtil;
    private IRolesService rolesService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRolesRepository(IRolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRolesService(IRolesService rolesService) {
        this.rolesService = rolesService;
    }

    @Override
//    @CacheEvict(cacheNames = "users", allEntries = true)
    public void register(UserRegistrationViewModel registrationDTO) {
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
//    @CacheEvict(cacheNames = "users", allEntries = true)
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }


    @Override
    public Optional<UserDto> findByUuid(UUID uuid) {
        return Optional.ofNullable(modelMapper.map(userRepository.findByUuid(uuid), UserDto.class));
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }

    @Override
//    @Cacheable("users")
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
//    @CacheEvict(value = "users", allEntries = true)
    public EditUserViewModel update(EditUserViewModel editUser) {
        User user = findByUsername(editUser.getUsername());
        user.setFirstName(editUser.getFirstName());
        user.setLastName(editUser.getLastName());
        user.setImageUrl(editUser.getImageUrl());
        return modelMapper.map(userRepository.save(user), EditUserViewModel.class);
    }

    @Override
    public User findByUsername(String userName) {
        return this.userRepository.findUserByUsername(userName);
    }

}
