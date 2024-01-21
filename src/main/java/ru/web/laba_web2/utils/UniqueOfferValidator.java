package ru.web.laba_web2.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.web.laba_web2.repositories.IOfferRepository;

public class UniqueOfferValidator implements ConstraintValidator<UniqueOffer, String> {
    private final IOfferRepository offerRepository;

    public UniqueOfferValidator(IOfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return offerRepository.findByDescription(value).isEmpty();
    }
}
