package ru.web.laba_web2.services.impl;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.models.Model;
import ru.web.laba_web2.models.Roles;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.repositories.RolesRepository;
import ru.web.laba_web2.repositories.UserRepository;
import ru.web.laba_web2.services.RolesService;
import ru.web.laba_web2.services.UserService;
import ru.web.laba_web2.services.dtos.OfferDto;
import ru.web.laba_web2.services.dtos.RolesDto;
import ru.web.laba_web2.services.dtos.UserDto;
import ru.web.laba_web2.utils.ValidationUtil;
import ru.web.laba_web2.viewModel.AddModelViewModel;
import ru.web.laba_web2.viewModel.EditUser;

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

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
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
    public void register(UserDto newUser) {
        if (!this.validationUtil.isValid(newUser)) {
            this.validationUtil
                    .violations(newUser)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                User user = modelMapper.map(newUser, User.class);
                user.setRole(rolesRepository.findRolesByRole(newUser.getRole()).orElse(null));
                this.userRepository.saveAndFlush(user);
            } catch (Exception e) {
                System.out.println("Oops, something went wrong! :(");
            }
        }
    }

    @Override
    @CacheEvict(cacheNames = "users", allEntries = true)
    public void deleteByUserName(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public void transfer(UserDto userDto, RolesDto rolesDto, OfferDto offerDto) {
        User user = userRepository.findByUuid(userDto.getUuid()).get();
        Roles roles = rolesRepository.findByUuid(rolesDto.getUuid()).get();
        user.setRole(roles);
        userRepository.saveAndFlush(user);
    }

    @Override
    public Optional<UserDto> findByUuid(String uuid) {
        return Optional.ofNullable(modelMapper.map(userRepository.findByUuid(uuid), UserDto.class));
    }


    @Override
    @Cacheable("users")
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    @CacheEvict(cacheNames = "users", allEntries = true)
    public void editUser(EditUser editUser) {
        if (!this.validationUtil.isValid(editUser)) {
            this.validationUtil
                    .violations(editUser)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                User user = modelMapper.map(editUser, User.class);
                user.setRole(rolesRepository.findRolesByRole(editUser.getRoles()).orElse(null));
                this.userRepository.saveAndFlush(user);
            } catch (Exception e) {
                System.out.println("Что-то пошло не так");
            }
        }
    }


    @Override
    public User findByUsername(String userName) {
        return this.userRepository.findUserByUsername(userName);
    }

}
