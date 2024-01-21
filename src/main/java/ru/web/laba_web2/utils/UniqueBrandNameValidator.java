package ru.web.laba_web2.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.web.laba_web2.repositories.IBrandRepository;

public class UniqueBrandNameValidator implements ConstraintValidator<UniqueBrandName, String> {
    private final IBrandRepository brandRepository;

    public UniqueBrandNameValidator(IBrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return brandRepository.findByName(value).isEmpty();
    }
}
