package ru.web.laba_web2.controllers.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String uuid) {
        super("Could not find User " + uuid);
    }
}
