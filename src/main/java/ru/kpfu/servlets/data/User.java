package ru.kpfu.servlets.data;

import java.util.Objects;

public class User {
    //public static String USERNAME;
    private String email;
    private String pass;
    private String username;


    public User(String email, String pass, String username) {
        this.email = email;
        this.pass = pass;
        this.username = username;
    }



    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(pass, user.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, pass);
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getUsername() {
        return username;
    }
}
