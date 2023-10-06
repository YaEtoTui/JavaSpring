package org.example.app.advice;

import org.example.app.exceptions.NotFoundFileException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundFileAdvice {
    @ExceptionHandler(NotFoundFileException.class)
    public String handleError(Model model, NotFoundFileException exception) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "errors/exceptions_shabl";
    }
}
