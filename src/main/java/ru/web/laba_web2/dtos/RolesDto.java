package ru.web.laba_web2.dtos;




public class RolesDto {
    private int id;

    private Role role;


    public RolesDto(Role role) {
        this.role = role;
    }

    public RolesDto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
