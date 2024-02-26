package ru.web.laba_web2.viewModel;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import ru.web.laba_web2.utils.UniqueEmail;
import ru.web.laba_web2.utils.UniqueUsername;

public class UserRegistrationViewModel {
    @UniqueUsername
    private String username;

    private String firstName;
    private String lastName;
    @UniqueEmail
    private String email;

    private String password;

    private String confirmPassword;

    public UserRegistrationViewModel() {
    }

    public UserRegistrationViewModel(String username, String firstName, String lastName, String email, String password, String confirmPassword) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @NotEmpty(message = "Имя пользователя не может быть пустым")
    @Size(min = 2, max = 40, message = "Имя пользователя минимум 2 символа")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 40, message = "Имя минимум 2 символа")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotEmpty(message = "Фамилия не может быть пустым")
    @Size(min = 2, max = 40, message = "Фамилия минимум 2 символа")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotEmpty(message = "Почта не может быть пустым")
    @Size(min = 2, max = 40, message = "Почта минимум 2 символа")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 2, max = 40, message = "Пароль минимум 2 символа")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 2, max = 40, message = "Пароль минимум 2 символа")

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
