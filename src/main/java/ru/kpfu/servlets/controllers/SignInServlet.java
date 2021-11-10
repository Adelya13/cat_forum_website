package ru.kpfu.servlets.controllers;


import ru.kpfu.servlets.entities.User;
import ru.kpfu.servlets.exceptions.NotFoundUserException;
import ru.kpfu.servlets.exceptions.WrongPasswordException;
import ru.kpfu.servlets.servies.UserService;
import ru.kpfu.servlets.validators.EmailValidator;
import ru.kpfu.servlets.validators.PassValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {

    private UserService service;

    @Override
    public void init(){
        this.service = new UserService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        getServletContext().getRequestDispatcher("/WEB-INF/view/signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String message;

        EmailValidator emailValidator = new EmailValidator(email);
        PassValidator passValidator = new PassValidator(password);

        if(req.getParameter("signinbtn") != null){

            if(emailValidator.checkMail() && passValidator.checkPassword()) {
                try{
                    User user = service.signin(email,password);
                    req.getSession().setAttribute("email", user.getEmail());
                    resp.sendRedirect(req.getContextPath()+"/profile");
                    return;
                }
                catch (NotFoundUserException e){
                    message = "Пользователь не найден";
                }
                catch(WrongPasswordException e){
                    message="Не верный пароль";
                }

            } else message ="Данные не коректны!";

            req.setAttribute("message",message);
            getServletContext().getRequestDispatcher("/WEB-INF/view/signin.jsp").forward(req, resp);



        }
        else if(req.getParameter("loginbtn") != null){

            resp.sendRedirect(req.getContextPath()+"/login");

        }
    }
}



