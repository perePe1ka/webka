package ru.web.laba_web2.dtos;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.Cascade;
import ru.web.laba_web2.models.Offer;
import ru.web.laba_web2.models.Roles;

import java.sql.Date;

public class UserDto {
    private int id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private boolean isActive;

    private RolesDto role;

    private String imageUrl;

    private java.sql.Date created;

    private java.sql.Date modified;

    private OfferDto offer;


    public UserDto() {

    }

    public UserDto(String username, String password, String firstName, String lastName, boolean isActive, RolesDto role, String imageUrl, Date created, Date modified, OfferDto offer) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public OfferDto getOffer() {
        return offer;
    }

    public void setOffer(OfferDto offerDto) {
        this.offer = offerDto;
    }
}
