package ru.web.laba_web2.controllers.exceptions;

public class BrandNotFoundException extends RuntimeException{
    public BrandNotFoundException(String uuid) {
        super("Could not find Brand " + uuid);
    }
}
