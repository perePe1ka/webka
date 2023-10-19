package ru.web.laba_web2.dtos;

import lombok.Data;
import java.util.UUID;


@Data
public class RolesDto {
    private Long uuid;

    private Role role;


    public RolesDto() {

    }

    public RolesDto(Role role) {
        this.role = role;
    }
}
