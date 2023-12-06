package ru.web.laba_web2.controllers.exceptions;

public class OfferNotFoundException extends RuntimeException {
    public OfferNotFoundException(String uuid) {
        super("Не можем найти Оффер с uuid:  " + uuid);
    }
}
