package ru.kpfu.servlets.controllers;

import ru.kpfu.servlets.entities.Cat;
import ru.kpfu.servlets.entities.Post;
import ru.kpfu.servlets.entities.User;
import ru.kpfu.servlets.exceptions.DuplicateDataException;
import ru.kpfu.servlets.exceptions.NotFoundBodyInPostException;
import ru.kpfu.servlets.servies.CatService;
import ru.kpfu.servlets.servies.PostService;
import ru.kpfu.servlets.servies.ShowService;
import ru.kpfu.servlets.servies.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet("/addCat")
public class AddCatServlet extends HttpServlet {
    private UserService userService;
    private CatService catService;
    private ShowService showService;
    private User user;

    @Override
    public void init(){
        this.userService = new UserService();
        this.catService = new CatService();
        this.showService = new ShowService();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email= (String) session.getAttribute("email");
        Optional<User> userOptional = userService.findByEmail(email);
        if (userOptional.isPresent()) {
            user = userOptional.get();
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());
            showService.showCats(user,req,catService);

        }

        getServletContext().getRequestDispatcher("/WEB-INF/view/addCat.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message ="";
        String catmessage= "";
        showService.showCats(user,req,catService);
        req.setAttribute("username", user.getUsername());
        req.setAttribute("email", user.getEmail());

        if(req.getParameter("addbtn") != null){
            String catname =  req.getParameter("catname");
            String description = req.getParameter("description");
            if(!catname.equals("")){
                System.out.println(user.getId());
                Cat cat = new Cat(catname,description,user);
                catService.save(cat);
                resp.sendRedirect(req.getContextPath()+"/addCat");
                return;
            }
            else {
                catmessage ="Пожалуйста введите корректные данные котика, а то он будет грустить";
            }

        }
        req.setAttribute("catmessage",catmessage);
        req.setAttribute("message",message);
        getServletContext().getRequestDispatcher("/WEB-INF/view/addCat.jsp").forward(req, resp);
    }

}
