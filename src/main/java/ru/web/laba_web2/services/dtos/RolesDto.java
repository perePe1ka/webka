package ru.web.laba_web2.services.dtos;

import ru.web.laba_web2.constants.Role;
import ru.web.laba_web2.viewModel.BaseViewModel;


public class RolesDto extends BaseViewModel {
    private Role role;


    public RolesDto() {

    }
    public RolesDto(Role role) {
        this.role = role;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
