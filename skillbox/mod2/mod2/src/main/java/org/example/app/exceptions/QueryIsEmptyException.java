package org.example.app.exceptions;

public class QueryIsEmptyException extends RuntimeException {
    public QueryIsEmptyException(String message) {
        super(message);
    }
}
