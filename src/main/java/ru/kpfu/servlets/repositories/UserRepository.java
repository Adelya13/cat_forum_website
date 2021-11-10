package ru.kpfu.servlets.repositories;

import org.mindrot.jbcrypt.BCrypt;
import ru.kpfu.servlets.data.Queries;
import ru.kpfu.servlets.entities.DbFile;
import ru.kpfu.servlets.entities.User;
import ru.kpfu.servlets.exceptions.DuplicateDataException;
import ru.kpfu.servlets.exceptions.NotFoundUserException;
import ru.kpfu.servlets.exceptions.WrongPasswordException;
import ru.kpfu.servlets.repositories.interfaces.IUserRepository;
import ru.kpfu.servlets.systems.DbSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class UserRepository implements IUserRepository {

    Connection con;

    public UserRepository() {
        DbSystem system = new DbSystem();
        con = system.getCon();
    }


    @Override
    public void login(String username, String email, String password) throws DuplicateDataException {
        try(PreparedStatement st1 = con.prepareStatement(Queries.FIND_USER_BY_EMAIL);
            PreparedStatement st2 = con.prepareStatement(Queries.ADD_USER)){
            int i =1;
            st1.setString(i,email);
            st1.execute();
            try(ResultSet result = st1.getResultSet()) {
                if(result.next()) {
                    throw new DuplicateDataException();
                }
                else{
                    String hashPassword = BCrypt.hashpw(password,BCrypt.gensalt());
                    st2.setString(i++,username);
                    st2.setString(i++,email);
                    st2.setString(i++,hashPassword);

                    st2.execute();

                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User signin(String email, String password) throws WrongPasswordException, NotFoundUserException {
        try(PreparedStatement st = con.prepareStatement(Queries.FIND_USER_BY_EMAIL)){

            st.setString(1,email);
            st.execute();


            try(ResultSet result = st.getResultSet()){
                if(result.next()){
                    String hashPass = result.getString("password");

                    if(BCrypt.checkpw(password,hashPass)){
                        String username = result.getString("username");
                        long id = result.getLong("id");
                        User user = new User(email,hashPass,username);
                        user.setId(id);
                        return user;
                    }
                    else throw new WrongPasswordException();
                }
                throw new NotFoundUserException();
            }
            catch (SQLException e) {
                throw new NotFoundUserException();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Optional<User> findByEmail(String email) {
        //FileRepository fileRepository = new FileRepository();
        try(PreparedStatement st = con.prepareStatement(Queries.FIND_USER_BY_EMAIL)){

            st.setString(1, String.valueOf(email));
            st.execute();

            try(ResultSet result = st.getResultSet()){
                if(result.next()){
                    String username = result.getString("username");
                    String pass = result.getString("password");
                    long id = (long) result.getLong("id");
                    //long avatarId = result.getLong("avatar");
                    User user = new User(email,pass,username);
                    user.setId(id);
                    //user.setAvatar(fileRepository.findById(avatarId).get());
                    return Optional.of(user);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateUser(User user, User newuser) {
        try(PreparedStatement st1 = con.prepareStatement(Queries.FIND_USER_BY_EMAIL);
            PreparedStatement st2 = con.prepareStatement(Queries.UPDATE_USER)){
            if(newuser.getUsername().equals("")){
                newuser.setUsername(user.getUsername());
            }
            if(newuser.getEmail().equals("")){
                newuser.setEmail(user.getEmail());
            }
            if(!user.getEmail().equals(newuser.getEmail())){
                st1.setString(1,newuser.getEmail());
                st1.execute();
                try(ResultSet result = st1.getResultSet()) {
                    if (result.next()) {
                        throw new DuplicateDataException();
                    }
                }
            }
            st2.setString(1, newuser.getUsername());
            st2.setString(2, newuser.getEmail());
            st2.setLong(3, newuser.getId());

            st2.execute();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateAvatar(User user, DbFile dbFile) {
        try(PreparedStatement st = con.prepareStatement(Queries.UPDATE_AVATAR)){

            st.setLong(1, dbFile.getId());
            st.setLong(2,user.getId());
            st.execute();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) throws DuplicateDataException {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        //FileRepository fileRepository = new FileRepository();
        try(PreparedStatement st = con.prepareStatement(Queries.FIND_USER_BY_ID)){

            st.setLong(1, id);
            st.execute();

            try(ResultSet result = st.getResultSet()){
                if(result.next()){
                    String username = result.getString("username");
                    String email = result.getString("email");
                    String pass = result.getString("password");
                    long avatarId = result.getLong("avatar");
                    User user = new User(email,pass,username);
                    user.setId(id);
                    //user.setAvatar(fileRepository.findById(avatarId).get());
                    return Optional.of(user);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
