package ru.web.laba_web2.controllers.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.web.laba_web2.controllers.exceptions.BrandNotFoundException;

@ControllerAdvice
class BrandNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(BrandNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String brandNotFoundHandler(BrandNotFoundException ex) {
        return ex.getMessage();
    }
}
