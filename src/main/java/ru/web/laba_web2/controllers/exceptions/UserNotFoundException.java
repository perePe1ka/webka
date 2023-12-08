package ru.web.laba_web2.controllers.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String uuid) {
        super("Не можем найти Роль с uuid: " + uuid);
    }
}
