package ru.web.laba_web2.services;

import ru.web.laba_web2.services.dtos.RolesDto;
import ru.web.laba_web2.models.Roles;

import java.util.List;
import java.util.Optional;

public interface RolesService<String> {
    Roles create(RolesDto rolesDto);
    void deleteByUuid(String uuid);

    Optional<RolesDto> findByUuid(String uuid);

    List<RolesDto> getAll();

    void editRoles(RolesDto rolesDto);
}
