package ru.web.laba_web2.controllers.exceptions;

public class RolesNotFoundException extends RuntimeException {
    public RolesNotFoundException(String uuid) {
        super("Не можем найти Пользователя с uuid: " + uuid);
    }
}
