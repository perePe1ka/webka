package ru.web.laba_web2.services;

import ru.web.laba_web2.dtos.*;
import ru.web.laba_web2.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService<ID> {
    UserDto register(UserDto userDto);

    void expel(UserDto userDto);

    void expel(ID id);

    void transfer(UserDto userDto, RolesDto rolesDto, OfferDto offerDto);

    Optional<UserDto> findById(ID id);

    List<UserDto> getAll();
}
