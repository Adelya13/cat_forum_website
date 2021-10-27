package ru.kpfu.servlets.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private String email;

    public EmailValidator(String email) {
        this.email = email;
    }

    public boolean checkMail(){
        Pattern p = Pattern.compile("\\w+@\\w+\\..+$");
        Matcher m = p.matcher(email);
        if(m.matches()){
            return true;
        }
        else return false;
    }
}
