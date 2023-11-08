package ru.web.laba_web2.controllers.exceptions;

public class ModelNotFoundException extends RuntimeException{
    public ModelNotFoundException(String uuid) {
        super("Could not find Model " + uuid);
    }
}
