package ru.kpfu.servlets.exceptions;

public class NotFoundBodyInPostException extends RuntimeException {

    public NotFoundBodyInPostException() {
    }

    public NotFoundBodyInPostException(String message) {
        super(message);
    }
}
