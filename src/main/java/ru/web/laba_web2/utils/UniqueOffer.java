package ru.web.laba_web2.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueOfferValidator.class)
public @interface UniqueOffer {
    String message() default "К сожалению такое же описание есть у другого бренда";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
