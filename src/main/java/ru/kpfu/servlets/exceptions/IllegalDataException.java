package ru.kpfu.servlets.exceptions;

public class IllegalDataException  extends RuntimeException {
    public IllegalDataException() {
    }

    public IllegalDataException(String message) {
        super(message);
    }
}
