package ru.web.laba_web2.dtos;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserDto {
    private Long uuid;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private boolean isActive;

    private RolesDto role;

    private String imageUrl;

    private LocalDate created;

    private LocalDate modified;

    private OfferDto offer;

    public UserDto() {

    }

    public UserDto(String username, String password, String firstName, String lastName, boolean isActive, RolesDto role, String imageUrl, LocalDate created, LocalDate modified, OfferDto offer) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.role = role;
        this.imageUrl = imageUrl;
        this.created = created;
        this.modified = modified;
        this.offer = offer;
    }
}
