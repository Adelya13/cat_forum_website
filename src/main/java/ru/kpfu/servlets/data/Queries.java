package ru.kpfu.servlets.data;

public class Queries {

    public static String ADD_USER = "insert into public.user (username,email,password) values(?,?,?)";

    public static String FIND_USER_BY_EMAIL = "select * from public.user where email=?";

    public static String FIND_USER_BY_ID = "select * from public.user where id=?";


}
