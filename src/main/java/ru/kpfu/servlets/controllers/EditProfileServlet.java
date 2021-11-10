package ru.kpfu.servlets.controllers;

import ru.kpfu.servlets.entities.Cat;
import ru.kpfu.servlets.entities.DbFile;
import ru.kpfu.servlets.entities.Post;
import ru.kpfu.servlets.entities.User;
import ru.kpfu.servlets.exceptions.DuplicateDataException;
import ru.kpfu.servlets.exceptions.NotFoundBodyInPostException;
import ru.kpfu.servlets.servies.CatService;
import ru.kpfu.servlets.servies.FileService;
import ru.kpfu.servlets.servies.PostService;
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

@WebServlet("/edit")
@MultipartConfig
public class EditProfileServlet extends HttpServlet {
    private UserService userService;
    private User user;
    private FileService fileService;

    @Override
    public void init(){
        this.userService = new UserService();
        this.fileService = new FileService();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("status") != null){
            if(req.getParameter("status").equals("1")){
                req.setAttribute("message", "Пост был отправлен");
            }
        }
        HttpSession session = req.getSession();
        String email= (String) session.getAttribute("email");
        Optional<User> userOptional = userService.findByEmail(email);

        if (userOptional.isPresent()) {
            user = userOptional.get();
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());


        }

        getServletContext().getRequestDispatcher("/WEB-INF/view/editprofile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message ="";

        req.setAttribute("username", user.getUsername());
        req.setAttribute("email", user.getEmail());

        if(req.getParameter("editphoto")!= null){
            Part part = req.getPart("file");
            DbFile dbFile = fileService.upload(part.getSubmittedFileName(), part.getInputStream());
            resp.sendRedirect(req.getContextPath()+"/edit");
            return;
        }
        if(req.getParameter("editbtn") != null){
            String username =  req.getParameter("username");
            String email = req.getParameter("email");
            try{
                if(!user.getUsername().equals(username) || !user.getEmail().equals(email)){
                    Part part = req.getPart("userAvatar");
                    long num = (long) (Math.random() * 10000);
                    //fileService.upload(num+"_"+part.getSubmittedFileName(), part.getInputStream());
                    User newuser = new User(email,username,user.getId());
                    userService.updateUser(user,newuser);
                    req.getSession().setAttribute("email", newuser.getEmail());
                }

                resp.sendRedirect(req.getRequestURI()+"?status=1");
                return;
            }
            catch (DuplicateDataException e){
                message= "Такой email существует!";
            }
        }

        req.setAttribute("message",message);
        getServletContext().getRequestDispatcher("/WEB-INF/view/editprofile.jsp").forward(req, resp);
    }
}
