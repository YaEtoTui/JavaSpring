package org.example.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/exceptions")
    public String notFoundError() {
        return "errors/exceptions_shabl";
    }

    @GetMapping("/exception_404")
    public String notFoundError404() {
        return "errors/exception_404";
    }

}
