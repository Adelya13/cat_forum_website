package ru.kpfu.servlets.exceptions;

public class NotFoundTagException extends RuntimeException {
    public NotFoundTagException() {
    }

    public NotFoundTagException(String message) {
        super(message);
    }
}
