package org.example.app.exceptions;

public class NotFoundFileException extends RuntimeException {

    public NotFoundFileException(String message) {
        super(message);
    }
}
