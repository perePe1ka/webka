package ru.web.laba_web2.services.impl;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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
    public void register(UserDto userDto) {
        if (!this.validationUtil.isValid(userDto)) {
            this.validationUtil
                    .violations(userDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            throw new IllegalArgumentException("Что то пошло не так");
        }
        User user = this.modelMapper.map(userDto, User.class);
        user.setRole(rolesService.findByRole(userDto.getRole()));

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteByUuid(String uuid) {
        userRepository.deleteByUuid(uuid);
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
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void editUser(UserDto userDto) {
        if (!this.validationUtil.isValid(userDto)) {
            this.validationUtil
                    .violations(userDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                User user = modelMapper.map(userDto, User.class);
                userRepository.saveAndFlush(user);
            } catch (Exception e) {
                System.out.println("Что-то пошло не так");
            }
        }
    }


    @Override
    public User findByUsername(String userName) {
        return this.userRepository.findByUsername(userName);
    }

}
