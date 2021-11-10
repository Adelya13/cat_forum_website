package ru.kpfu.servlets.exceptions;

public class NotFoundPostException extends RuntimeException{
    public NotFoundPostException() {
    }

    public NotFoundPostException(String message) {
        super(message);
    }
}
