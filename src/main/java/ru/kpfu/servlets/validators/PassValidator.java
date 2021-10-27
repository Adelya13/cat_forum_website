package ru.kpfu.servlets.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassValidator {
    private String pass;

    public PassValidator(String password) {
        this.pass = password;
    }
    public boolean checkPassword(){
        Pattern p = Pattern.compile("[1-9,a-z,A-Z,-,\\.,,\\*]+");
        Matcher m = p.matcher(pass);

        if(pass.equals("123") || pass.equals("abc")){
            return false;
        }
        else if (m.matches()){
            return true;
        }
        else return false;
    }

}
