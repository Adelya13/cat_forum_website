package ru.kpfu.servlets.servies;

import ru.kpfu.servlets.data.RegistrationSystem;
import ru.kpfu.servlets.data.User;
import ru.kpfu.servlets.exceptions.DuplicateDataException;
import ru.kpfu.servlets.exceptions.IllegalPasswordException;
import ru.kpfu.servlets.exceptions.NotFoundUserException;
import ru.kpfu.servlets.exceptions.WrongPasswordException;
import ru.kpfu.servlets.inside.AdminInformation;
import ru.kpfu.servlets.inside.Properties;

import java.util.Optional;


public class RegistrationService implements IRegistrationService {

    @Override
    public void logIn(String username, String email, String password) throws DuplicateDataException {
        RegistrationSystem registrationSystem = new RegistrationSystem();
        registrationSystem.addUser(username,email,password);
    }

    @Override
    public void signIn(String email, String password){
        RegistrationSystem registrationSystem = new RegistrationSystem();
        registrationSystem.findUserByEmail(email,password);

    }
}
