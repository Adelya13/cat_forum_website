package ru.kpfu.servlets.exceptions;

public class IllegalEmailException  extends RuntimeException {

    public IllegalEmailException() {
    }

    public IllegalEmailException(String message) {
        super(message);
    }
}
