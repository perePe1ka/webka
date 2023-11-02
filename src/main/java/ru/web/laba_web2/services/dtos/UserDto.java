package ru.web.laba_web2.services.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class UserDto {
    private String uuid;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private boolean isActive;

    private RolesDto role;

    private String imageUrl;


    public UserDto() {

    }

    public UserDto(String username, String password, String firstName, String lastName, boolean isActive, RolesDto role, String imageUrl) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.role = role;
        this.imageUrl = imageUrl;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    @NotNull
    @NotEmpty
    @Length(min = 2, message = "Ошибка, введите минимум 2 символа")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotNull
    @NotEmpty
    @Length(min = 2, message = "Ошибка, введите минимум 2 символа")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @NotNull
    @NotEmpty
    @Length(min = 2, message = "Ошибка, введите минимум 2 символа")
    public String getFirstName() {
        return firstName;
    }
    @NotNull
    @NotEmpty
    @Length(min = 2, message = "Ошибка, введите минимум 2 символа")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public RolesDto getRole() {
        return role;
    }

    public void setRole(RolesDto role) {
        this.role = role;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
