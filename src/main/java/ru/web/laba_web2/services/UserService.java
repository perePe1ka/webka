package ru.web.laba_web2.services;

import ru.web.laba_web2.dtos.*;
import java.util.List;
import java.util.Optional;

public interface UserService<Long> {
    UserDto register(UserDto userDto);

    void delete(UserDto userDto);

    void deleteByUUID(Long uuid);

    void transfer(UserDto userDto, RolesDto rolesDto, OfferDto offerDto);

    Optional<UserDto> findByUUID(Long uuid);

    List<UserDto> getAll();
}
