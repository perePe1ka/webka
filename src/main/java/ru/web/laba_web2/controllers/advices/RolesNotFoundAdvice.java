package ru.web.laba_web2.controllers.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.web.laba_web2.controllers.exceptions.BrandNotFoundException;
import ru.web.laba_web2.controllers.exceptions.RolesNotFoundException;

@ControllerAdvice
public class RolesNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(RolesNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String rolesNotFoundHandler(RolesNotFoundException ex) {
        return ex.getMessage();
    }

}
