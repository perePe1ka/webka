package ru.web.laba_web2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.services.dtos.OfferDto;
import ru.web.laba_web2.services.dtos.RolesDto;
import ru.web.laba_web2.services.dtos.UserDto;
import ru.web.laba_web2.models.Roles;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.repositories.RolesRepository;
import ru.web.laba_web2.repositories.UserRepository;
import ru.web.laba_web2.services.UserService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<String> {
    private final ModelMapper modelMapper;
    private final RolesRepository rolesRepository;
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, RolesRepository rolesRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.rolesRepository = rolesRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User create(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public UserDto register(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        if (userDto.getRole().getUuid() != null) {
            Roles roles = rolesRepository.findByUuid(userDto.getRole().getUuid()).get();
            user.setRole(roles);
        }
        return modelMapper.map(userRepository.saveAndFlush(user), UserDto.class);
    }

    @Override
    public void delete(UserDto userDto) {
        userRepository.deleteByUuid(userDto.getUuid());
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
}
