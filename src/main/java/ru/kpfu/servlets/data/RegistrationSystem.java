package ru.kpfu.servlets.data;

import ru.kpfu.servlets.exceptions.*;
import ru.kpfu.servlets.inside.AdminInformation;
import ru.kpfu.servlets.inside.Properties;

import java.sql.*;

public class RegistrationSystem implements IRegistrationSystem {

    private final Connection con;
    private final String url = "jdbc:postgresql://localhost:5432/webapp_db";


    public RegistrationSystem() {
        getDriver();
        con = createConnection(url, AdminInformation.USER, AdminInformation.PASSWORD);
    }

    @Override
    public void addUser(String username, String email, String password) throws DuplicateDataException{

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
                    st2.setString(i++,username);
                    st2.setString(i++,email);
                    st2.setString(i++,password);
                    st2.execute();

                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserByEmail(String email, String password) throws WrongPasswordException, NotFoundUserException {

        try(PreparedStatement st = con.prepareStatement(Queries.FIND_USER_BY_EMAIL)){

            st.setString(1,email);
            st.execute();

            try(ResultSet result = st.getResultSet()){
                if(result.next()){
                    String pass = result.getString("password");
                    if(password.equals(pass)){
                        String username = result.getString("username");
                        User user = new User(email,pass,username);
                        //System.out.println(user);
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
    public User findUserByID(Long id) throws IllegalPasswordException, IllegalEmailException, NotFoundUserException {
        return null;
    }

    private void getDriver(){
        String driverName = Properties.DRIVER_DB;
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection createConnection(String url, String user, String password) {

        try {
            return  DriverManager.getConnection(url,user,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }
}
