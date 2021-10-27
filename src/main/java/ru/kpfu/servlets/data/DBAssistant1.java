package ru.kpfu.servlets.data;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import ru.kpfu.servlets.inside.AdminInformation;
import ru.kpfu.servlets.inside.Properties;
import ru.kpfu.servlets.servies.SecurityService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;



public class DBAssistant1 {

    public static final String ADMIN = AdminInformation.USER;
    public static final String PASSWORD = AdminInformation.PASSWORD;

    private String username;
    private String password;
    private String email;
    private User user;
    private Connection con;

    public DBAssistant1(){
        getDriver();

    }
    public DBAssistant1(String username, String email, String password) {
        getDriver();
        this.username = username;
        this.password = password;
        this.email = email;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/webapp_db",ADMIN,PASSWORD);
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
    }

    public DBAssistant1(String email, String password) {
        getDriver();
        this.password = password;
        this.email = email;
    }

    public boolean sentDatatoDB(HttpServletResponse resp){

        try(PreparedStatement st1 = con.prepareStatement("select email from public.user where email=?");
            PreparedStatement st2 = con.prepareStatement("insert into public.user (username,email,password) values(?,?,?)")){
            int i =1;
            st1.setString(i,email);
            st1.execute();
            try(ResultSet result = st1.getResultSet()) {
                if (result.next()) {
                    return false;
                }
                else{
                    st2.setString(i++,username);
                    st2.setString(i++,email);
                    st2.setString(i++,password);
                    st2.execute();

                    return true;
                }
            }
        }
        catch (SQLException e) {
            try {
                resp.getWriter().println(Arrays.toString(e.getStackTrace()));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }
        return false;
    }

    public User findUserById(HttpServletResponse resp, HttpServletRequest req,int id){

        try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/webapp_db",ADMIN,PASSWORD)){
            try(PreparedStatement st = con.prepareStatement("select * from public.user where id=?")){

                st.setInt(1,id);
                st.execute();
                try(ResultSet result = st.getResultSet()){
                    if(result.next()){

                        //User user = new User(result.getString("email"),result.getString("password"),
                         //       result.getInt("id"),result.getString("username"));
                        return null;

                    }
                }
                catch (SQLException e) {
                    try {
                        resp.getWriter().println(Arrays.toString(e.getStackTrace()));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
            catch (SQLException e) {
                try {
                    resp.getWriter().println(Arrays.toString(e.getStackTrace()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public boolean findDatainDB(HttpServletResponse resp, HttpServletRequest req){
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/webapp_db",ADMIN,PASSWORD)){
            try(PreparedStatement st = con.prepareStatement("select * from public.user where email=?")){

                st.setString(1,email);
                st.execute();
                return getResult(st,resp, req, password);
            }
            catch (SQLException e) {
                try {
                    resp.getWriter().println(Arrays.toString(e.getStackTrace()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }


    public boolean getResult(PreparedStatement st, HttpServletResponse resp, HttpServletRequest req, String password){
        try(ResultSet result = st.getResultSet()){
            if(result.next()){
                String pass = result.getString("password");
                if(password.equals(pass)){
                    username = result.getString("username");
                    int id = result.getInt("id");
                    //user = new User(email,pass,id,username);
                    //System.out.println(user);
                    return true;
                }
            }
            return false;

        }
        catch (SQLException e) {
            try {
                resp.getWriter().println(Arrays.toString(e.getStackTrace()));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public User getUser() {
        return user;
    }

    private void getDriver(){
        String driverName = Properties.DRIVER_DB;
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
