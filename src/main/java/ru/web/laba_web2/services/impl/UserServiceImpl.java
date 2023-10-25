package ru.web.laba_web2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.dtos.OfferDto;
import ru.web.laba_web2.dtos.RolesDto;
import ru.web.laba_web2.dtos.UserDto;
import ru.web.laba_web2.models.Offer;
import ru.web.laba_web2.models.Roles;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.repositories.OfferRepository;
import ru.web.laba_web2.repositories.RolesRepository;
import ru.web.laba_web2.repositories.UserRepository;
import ru.web.laba_web2.services.UserService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<UUID> {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public UserDto register(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        if (userDto.getRole().getUuid() != null) {
            Roles roles = rolesRepository.findByUuid(userDto.getRole().getUuid()).get();
            user.setRole(roles);
        }
        if (userDto.getRole().getUuid() != null) {
            Offer offer = offerRepository.findByUuid(userDto.getOffer().getUuid()).get();
            user.setOffer(offer);
        }
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public void delete(UserDto userDto) {
        userRepository.deleteByUuid(userDto.getUuid());
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        userRepository.deleteByUuid(uuid);
    }

    @Override
    public void transfer(UserDto userDto, RolesDto rolesDto, OfferDto offerDto) {
        User user = userRepository.findByUuid(userDto.getUuid()).get();
        Offer offer = offerRepository.findByUuid(offerDto.getUuid()).get();
        Roles roles = rolesRepository.findByUuid(rolesDto.getUuid()).get();
        user.setOffer(offer);
        user.setRole(roles);
        userRepository.save(user);
    }

    @Override
    public Optional<UserDto> findByUuid(UUID uuid) {
        return Optional.ofNullable(modelMapper.map(userRepository.findByUuid(uuid), UserDto.class));
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }
}
