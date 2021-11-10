package ru.kpfu.servlets.servies;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.kpfu.servlets.data.DBAssistant1;
import ru.kpfu.servlets.entities.User;

public class SecurityService {
    private static final String id ="id";
    private static User user;


    public static User getUser(HttpServletRequest req,HttpServletResponse resp){
        HttpSession session = req.getSession();
        if(isSigned(req,resp)){
           return user;
        }
        return null;

    }

    public static boolean isSigned(HttpServletRequest req,HttpServletResponse resp) {
        if(req.getSession().getAttribute("email") != null){
            //req.setAttribute("username",user.getUsername());
            return true;
        }
//        Cookie[] cookies = req.getCookies();
//        if(cookies != null){
//            for (Cookie cookie : cookies){
//                if(cookie.getName().equals(id)){
//                    DBAssistant query = new DBAssistant();
//                    int c = Integer.parseInt(cookie.getValue());
//                    System.out.println(c);
//                    user = query.findUserById(resp,req, Integer.parseInt(cookie.getValue()));
//                    //System.out.println(user);
//                    req.setAttribute("username",user.getUsername());
//                    req.getSession().setAttribute("user", user);
//                    return true;
//                }
//            }
//        }
        return false;
    }

    public static boolean signIn(HttpServletRequest req, HttpServletResponse resp, String email, String password) {




        DBAssistant1 query = new DBAssistant1(email,password);
        if(query.findDatainDB(resp,req)) {
            user = query.getUser();
            System.out.println(user);
            req.setAttribute("email",user.getEmail());
            req.setAttribute("username",user.getUsername());
            //Cookie cookie = new Cookie(id,String.valueOf(user.getId()));
            //resp.addCookie(cookie);
            System.out.println(req.getSession().getAttribute("email"));
            return true;
        }

        return false;
    }

    public static void signOut(HttpServletRequest req) {
        req.getSession().removeAttribute("user");
    }
}

