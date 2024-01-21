package ru.web.laba_web2.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.web.laba_web2.repositories.IUserRepository;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final IUserRepository userService;

    public UniqueUsernameValidator(IUserRepository userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext ctx) {
        return userService.findUserByUsername(username) == null;
    }
}
