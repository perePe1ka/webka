package ru.web.laba_web2.services.dtos;

import ru.web.laba_web2.constants.Role;



public class RolesDto {
    private String uuid;

    private Role role;


    public RolesDto() {

    }

    public RolesDto(Role role) {
        this.role = role;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
