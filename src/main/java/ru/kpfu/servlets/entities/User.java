package ru.kpfu.servlets.entities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class User {
    private long id;
    private String email;
    private String pass;
    private String username;
    private DbFile avatar;
    ArrayList<Cat> cats;
    ArrayList<Post> posts;




    public User(String email, String username, long id) {
        this.email = email;
        this.username = username;
        this.id = id;
    }
    public User(String email, String pass, String username) {
        this.email = email;
        this.pass = pass;
        this.username = username;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DbFile getAvatar() {
        return avatar;
    }

    public void setAvatar(DbFile avatar) {
        this.avatar = avatar;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
