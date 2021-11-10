package ru.kpfu.servlets.controllers;

import ru.kpfu.servlets.entities.Post;
import ru.kpfu.servlets.entities.Tag;
import ru.kpfu.servlets.entities.User;
import ru.kpfu.servlets.exceptions.NotFoundBodyInPostException;
import ru.kpfu.servlets.exceptions.NotFoundTagException;
import ru.kpfu.servlets.servies.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Optional;


@WebServlet("/forum")
public class ForumServlet extends HttpServlet {

    private UserService userService;
    private PostService postService;
    private ShowService showService;
    private TagService tagService;
    private User user;

    @Override
    public void init(){
        this.userService = new UserService();
        this.postService = new PostService();
        this.showService = new ShowService();
        this.tagService = new TagService();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email= (String) session.getAttribute("email");
        Optional<User> userOptional = userService.findByEmail(email);
        if (userOptional.isPresent()) {
            user = userOptional.get();
            show(req);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/forum.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message ="";
        show(req);

        req.setAttribute("email", user.getEmail());
        if(req.getParameter("searchBtn") != null){
            try{
                String tagName = req.getParameter("searchTag");
                long id = tagService.findTagByName(tagName);
                Tag tag = new Tag(id,tagName);
                showService.showPostsByTag(tag,req,postService);
                resp.sendRedirect(req.getContextPath()+"/forum?tag=" +URLEncoder.encode(tag.getTagName(),"UTF-8"));
                return;

            }
            catch (NotFoundTagException e){
                message = "К сожалению такого тега еще нет";
            }


        }

        showService.showAllPost(req,postService);

        req.setAttribute("message",message);
        getServletContext().getRequestDispatcher("/WEB-INF/view/forum.jsp").forward(req, resp);
    }

    private void show(HttpServletRequest req){
        if((req.getParameter("tag") != null)){
            try {
                String tagName = req.getParameter("tag");
                long id = tagService.findTagByName(tagName);
                Tag tag = new Tag(id,tagName);
                req.setAttribute("tag",tag.getTagName());
                showService.showPostsByTag(tag,req,postService);
            }
            catch (NotFoundTagException e){
                req.setAttribute("message","Такого тега не существует");
            }

        }
        else{
            showService.showAllPost(req,postService);
        }
    }


}