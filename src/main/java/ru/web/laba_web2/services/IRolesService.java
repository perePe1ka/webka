package ru.web.laba_web2.services;

import ru.web.laba_web2.services.dtos.RolesDto;

import java.util.List;
import java.util.Optional;

public interface IRolesService<String> {
    List<RolesDto> getAll();

    Optional<RolesDto> findByUuid(String uuid);

    void register(RolesDto rolesDto);
}
