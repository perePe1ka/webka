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
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<Integer> {
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
        if (userDto.getRole().getId() != 0) {
            Roles roles = rolesRepository.findById(userDto.getRole().getId()).get();
            user.setRole(roles);
        }
        if (userDto.getRole().getId() != 0) {
            Offer offer = offerRepository.findById(userDto.getOffer().getId()).get();
            user.setOffer(offer);
        }
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public void expel(UserDto userDto) {
        userRepository.deleteById(userDto.getId());
    }

    @Override
    public void expel(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void transfer(UserDto userDto, RolesDto rolesDto, OfferDto offerDto) {
        User user = userRepository.findById(userDto.getId()).get();
        Offer offer = offerRepository.findById(offerDto.getId()).get();
        Roles roles = rolesRepository.findById(rolesDto.getId()).get();
        user.setOffer(offer);
        user.setRole(roles);
        userRepository.save(user);
    }

    @Override
    public Optional<UserDto> findById(Integer id) {
        return Optional.ofNullable(modelMapper.map(userRepository.findById(id), UserDto.class));
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

//    @Override
//    public List<UserDto> findByRole(String roles) {
//        return userRepository.findAllRolesName(roles).stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
//    }

//    @Override
//    public List<UserDto> findByOffer(String offer) {
//        return userRepository.findAllOfferName(offer).stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
//    }
}
