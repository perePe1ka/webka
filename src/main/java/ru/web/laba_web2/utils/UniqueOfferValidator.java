package ru.web.laba_web2.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.web.laba_web2.repositories.OfferRepository;

public class UniqueOfferValidator implements ConstraintValidator<UniqueOffer, String> {
    private final OfferRepository offerRepository;

    public UniqueOfferValidator(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return offerRepository.findByDescription(value).isEmpty();
    }
}
