package ru.web.laba_web2.controllers.exceptions;

public class OfferNotFoundException extends RuntimeException {
    public OfferNotFoundException(String uuid) {
        super("Could not find Offer " + uuid);
    }
}
