package ru.web.laba_web2.services;

import ru.web.laba_web2.models.Brand;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.services.dtos.OfferDto;
import ru.web.laba_web2.services.dtos.RolesDto;
import ru.web.laba_web2.services.dtos.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService<String> {
//    User create(UserDto userDto);

    void register(UserDto userDto);

    void deleteByUuid(String uuid);

    void transfer(UserDto userDto, RolesDto rolesDto, OfferDto offerDto);

    Optional<UserDto> findByUuid(String uuid);

    List<UserDto> getAll();

    void editUser(UserDto userDto);

    User findByUsername(String userName);
}
