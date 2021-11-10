package ru.kpfu.servlets.exceptions;

import java.io.IOException;

public class NotFoundFileException extends IOException {
    public NotFoundFileException(String message) {
        super(message);
    }

    public NotFoundFileException() {
    }
}
