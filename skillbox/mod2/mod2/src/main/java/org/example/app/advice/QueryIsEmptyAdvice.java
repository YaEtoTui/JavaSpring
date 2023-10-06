package org.example.app.advice;

import org.example.app.exceptions.QueryIsEmptyException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class QueryIsEmptyAdvice {
    @ExceptionHandler(QueryIsEmptyException.class)
    public String handleError(Model model, QueryIsEmptyException exception) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "errors/exceptions_shabl";
    }
}
