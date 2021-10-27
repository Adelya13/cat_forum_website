package ru.kpfu.servlets.servies;


import ru.kpfu.servlets.exceptions.DuplicateDataException;
import ru.kpfu.servlets.exceptions.WrongPasswordException;

public interface IRegistrationService {
    void logIn(String username, String email, String password) throws DuplicateDataException;
    void signIn(String email, String password) throws WrongPasswordException;
}
