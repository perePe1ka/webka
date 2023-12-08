package ru.web.laba_web2.controllers.exceptions;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String uuid) {
        super("Не можем найти Модель с uuid: " + uuid);
    }
}
