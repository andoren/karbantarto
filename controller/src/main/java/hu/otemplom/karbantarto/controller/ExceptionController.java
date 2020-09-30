package hu.otemplom.karbantarto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import hu.otemplom.karbantarto.service.Exceptions.UserService.*;
import java.io.IOException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(DuplicateUserException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String serverIOError(DuplicateUserException e){
        if(e.getMessage().equals("username")) return "A megadott felhasználó név már foglalt! Kérem próbáljon egy másikat.";
        else return "A megadott e-mail cím már foglalt! Kérem próbáljon egy másikat.";
    }

}
