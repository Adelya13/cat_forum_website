package ru.kpfu.servlets.exceptions;

public class IllegalPasswordException extends RuntimeException {

    public IllegalPasswordException() {
    }

    public IllegalPasswordException(String message) {
        super(message);
    }
}
