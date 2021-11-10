package ru.kpfu.servlets.servies.interfaces;

import ru.kpfu.servlets.entities.DbFile;
import ru.kpfu.servlets.entities.User;
import ru.kpfu.servlets.exceptions.DuplicateDataException;
import ru.kpfu.servlets.exceptions.NotFoundUserException;
import ru.kpfu.servlets.exceptions.WrongPasswordException;
import ru.kpfu.servlets.servies.interfaces.IService;

import java.util.Optional;

public interface IUserService extends IService<User> {
    Optional<User> findByEmail(String email);
    void login(String username, String email, String password) throws DuplicateDataException;
    User signin(String email, String password) throws WrongPasswordException, NotFoundUserException;
    void updateUser(User user, User newuser);
    void updateAvatar(User user, DbFile dbFile);
}
