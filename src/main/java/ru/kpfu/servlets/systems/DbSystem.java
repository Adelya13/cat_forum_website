package ru.kpfu.servlets.systems;

import ru.kpfu.servlets.inside.AdminInformation;
import ru.kpfu.servlets.inside.Properties;

import java.sql.*;

public class DbSystem {

    private final Connection con;
    private final String url = "jdbc:postgresql://localhost:5432/webapp_db";


    public DbSystem() {
        getDriver();
        con = createConnection(url, AdminInformation.USER, AdminInformation.PASSWORD);
    }

    public Connection getCon() {
        return con;
    }

    private void getDriver(){
        String driverName = Properties.DRIVER_DB;
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection(String url, String user, String password) {

        try {
            return  DriverManager.getConnection(url,user,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }
}
