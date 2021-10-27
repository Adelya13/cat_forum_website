package ru.kpfu.servlets.data;

import ru.kpfu.servlets.exceptions.*;

import java.sql.SQLException;

public interface IRegistrationSystem {
    void addUser(String username, String email, String password) throws DuplicateDataException;
    User findUserByEmail(String email, String password) throws WrongPasswordException, NotFoundUserException;

    User findUserByID(Long id) throws WrongPasswordException, NotFoundUserException;



}
