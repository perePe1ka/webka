package ru.web.laba_web2.services;

import ru.web.laba_web2.dtos.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService<Long> {
    UserDto register(UserDto userDto);

    void delete(UserDto userDto);

    void deleteByUuid(Long uuid);

    void transfer(UserDto userDto, RolesDto rolesDto, OfferDto offerDto);

    Optional<UserDto> findByUuid(Long uuid);

    List<UserDto> getAll();
}
