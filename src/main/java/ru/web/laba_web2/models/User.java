package ru.web.laba_web2.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends TimeClass implements Serializable{

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private boolean isActive;

//    private String email;

    private Roles role;

    private String imageUrl;

    private Set<Offer> offers;

    public User(LocalDateTime created,
                LocalDateTime modified,
                String username,
                String password,
                String firstName,
                String lastName,
                boolean isActive,
                Roles role,
                String imageURL) {
        super(created, modified);
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.role = role;
        this.imageUrl = imageURL;
//        this.email = email;
    }

    public User() {

    }

    @Column(name = "username", length = 20, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", length = 20, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "firstName", length = 15, nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastName", length = 15, nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "isActive", nullable = false)
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "roles_uuid", referencedColumnName = "uuid", nullable = false)
    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Column(name = "imageUrl", length = 255)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "seller", cascade = CascadeType.REMOVE)
    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }
//    @Column(name = "email", length = 255)
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    @Override
    public String toString() {
        return firstName + ' ' + lastName;
    }

}
