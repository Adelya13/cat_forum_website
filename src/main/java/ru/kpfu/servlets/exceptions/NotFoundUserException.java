package ru.kpfu.servlets.exceptions;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException() {
    }

    public NotFoundUserException(String message) {
        super(message);
    }
}
