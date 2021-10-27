package ru.kpfu.servlets.controllers;

import ru.kpfu.servlets.data.DBAssistant1;
import ru.kpfu.servlets.exceptions.DuplicateDataException;
import ru.kpfu.servlets.exceptions.NotFoundUserException;
import ru.kpfu.servlets.exceptions.WrongPasswordException;
import ru.kpfu.servlets.servies.RegistrationService;
import ru.kpfu.servlets.validators.EmailValidator;
import ru.kpfu.servlets.validators.PassValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private RegistrationService service;

    @Override
    public void init() throws ServletException {
        this.service = new RegistrationService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String username = req.getParameter("username");

        String message;

        EmailValidator emailValidator = new EmailValidator(email);
        PassValidator passValidator = new PassValidator(password);

        if(req.getParameter("loginbtn") != null){

            if(emailValidator.checkMail() && passValidator.checkPassword()) {

                try{
                    service.logIn(username,email,password);
                    resp.sendRedirect("/signIn");
                    return;
                }
                catch (DuplicateDataException e){
                    message = "Такой пользователь уже существует";
                }


//                DBAssistant1 query = new DBAssistant1(username,email,password);
//                if( query.sentDatatoDB(resp)){
//                    req.setAttribute("message","You are registered");
//                    getServletContext().getRequestDispatcher("/WEB-INF/view/signin.jsp").forward(req, resp);
//                    return;
//                }
//                message = "Such user is already registered";

            } else message ="Данные не коректны!";

            req.setAttribute("message",message );

            getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);

        }
        else if(req.getParameter("signinbtn") != null){

            resp.sendRedirect(req.getContextPath()+"/signin");


        }


    }



}



