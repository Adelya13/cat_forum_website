package ru.kpfu.servlets.servies;

import ru.kpfu.servlets.entities.DbFile;
import ru.kpfu.servlets.entities.User;
import ru.kpfu.servlets.exceptions.DuplicateDataException;
import ru.kpfu.servlets.exceptions.NotFoundUserException;
import ru.kpfu.servlets.exceptions.WrongPasswordException;
import ru.kpfu.servlets.repositories.UserRepository;
import ru.kpfu.servlets.servies.interfaces.IUserService;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService{

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    @Override
    public void save(User entity) {
        userRepository.save(entity);
    }

    @Override
    public void update(User entity) throws DuplicateDataException {
        userRepository.update(entity);
    }

    @Override
    public void delete(User entity) {
        userRepository.delete(entity);
    }

    @Override
    public Optional<User> findById(Long id) {

        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void login(String username, String email, String password) throws DuplicateDataException {
        userRepository.login(username, email, password);
    }

    @Override
    public User signin(String email, String password) throws WrongPasswordException, NotFoundUserException {
        return userRepository.signin(email, password);
    }

    @Override
    public void updateUser(User user, User newuser) {
        userRepository.updateUser(user,newuser);
    }

    @Override
    public void updateAvatar(User user, DbFile dbFile) {
        userRepository.updateAvatar(user,dbFile);
    }


}
