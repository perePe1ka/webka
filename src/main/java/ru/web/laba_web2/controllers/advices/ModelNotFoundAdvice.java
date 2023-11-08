package ru.web.laba_web2.controllers.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.web.laba_web2.controllers.exceptions.BrandNotFoundException;
import ru.web.laba_web2.controllers.exceptions.ModelNotFoundException;

@ControllerAdvice
public class ModelNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ModelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String modelNotFoundHandler(ModelNotFoundException ex) {
        return ex.getMessage();
    }

}
