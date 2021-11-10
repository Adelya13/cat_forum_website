package ru.kpfu.servlets.controllers;


import ru.kpfu.servlets.entities.DbFile;
import ru.kpfu.servlets.entities.Post;
import ru.kpfu.servlets.entities.User;
import ru.kpfu.servlets.exceptions.NotFoundBodyInPostException;
import ru.kpfu.servlets.servies.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserService userService;
    private PostService postService;
    private CatService catService;
    private ShowService showService;
    private User user;

    @Override
    public void init(){
        this.userService = new UserService();
        this.postService = new PostService();
        this.catService = new CatService();
        this.showService = new ShowService();

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
            showService.showPostsByUserId(user,req,postService);
            showService.showCats(user,req,catService);
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());

        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message ="";
        req.setAttribute("username", user.getUsername());
        req.setAttribute("email", user.getEmail());

        showService.showPostsByUserId(user,req,postService);
        showService.showCats(user,req,catService);

        if(req.getParameter("add_post") != null){
            String tittle =  req.getParameter("tittle");
            String body = req.getParameter("write_post");
            String tags = req.getParameter("tags");
            try{
                Post post = new Post(body,user,tags,tittle);
                postService.createPost(post);
                resp.sendRedirect(req.getRequestURI()+"?status=1");
                return;
            }
            catch (NotFoundBodyInPostException e){
                message= "Пожалуйста напишите пост или задайте вопрос!";

            }
        }
        if(req.getParameter("addCatBtn") != null){
            resp.sendRedirect(req.getContextPath()+"/addCat");
            return;
        }
        req.setAttribute("message",message);
        getServletContext().getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(req, resp);
    }


}
