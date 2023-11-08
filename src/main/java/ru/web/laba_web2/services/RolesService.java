package ru.web.laba_web2.services;

import ru.web.laba_web2.models.Roles;
import ru.web.laba_web2.services.dtos.RolesDto;

import java.util.List;
import java.util.Optional;

public interface RolesService<String> {
    void register(String role);

//    Roles create(RolesDto rolesDto);
    void deleteByUuid(String uuid);

    Optional<RolesDto> findByUuid(String uuid);

    List<RolesDto> getAll();

    void editRoles(RolesDto rolesDto);

    Roles findByRole(String role);
}
