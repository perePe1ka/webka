package ru.web.laba_web2.dtos;

import lombok.Data;
import ru.web.laba_web2.constants.Role;

import java.util.UUID;


@Data
public class RolesDto {
    private UUID uuid;

    private Role role;


    public RolesDto() {

    }

    public RolesDto(Role role) {
        this.role = role;
    }
}
