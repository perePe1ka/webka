package ru.web.laba_web2.viewModel;

import java.util.UUID;

public class UserProfileViewModel {
    private UUID uuid;
    private String username;

    private String email;

    private String imageUrl;
    private String firstName;
    private String lastName;

    public UserProfileViewModel(UUID uuid, String username, String email, String firstName, String lastName, String imageUrl) {
        this.uuid = uuid;
        this.imageUrl = imageUrl;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
