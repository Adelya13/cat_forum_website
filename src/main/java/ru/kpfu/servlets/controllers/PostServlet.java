package ru.kpfu.servlets.controllers;

import ru.kpfu.servlets.entities.Cat;
import ru.kpfu.servlets.entities.Comment;
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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet(name = "/post", urlPatterns = "/post")
public class PostServlet extends HttpServlet {

    private PostService postService;
    private ShowService showService;
    private CommentService commentService;
    private Post post;
    private User user;


    @Override
    public void init(){
        this.postService = new PostService();
        this.commentService = new CommentService();
        this.showService = new ShowService();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("status") != null){
            if(req.getParameter("status").equals("1")){
                req.setAttribute("message", "Пост был отправлен");
            }
        }
        long id = Long.parseLong(req.getParameter("id"));
        Optional<Post> postOptional = postService.findById(id);

        if(postOptional.isPresent()){
            post = postOptional.get();
            user = post.getAuthor();
            setPostAttributes(req);
            showService.showComments(post,req,commentService);

        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/post.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message ="";
        setPostAttributes(req);
        req.setAttribute("email", user.getEmail());
        req.setAttribute("post", post);
        showService.showComments(post,req,commentService);
        if(req.getParameter("addComment") != null){

            if(req.getParameter("writeComment") != null){
                String text = req.getParameter("writeComment");
                Comment comment = new Comment(user,post,text);
                commentService.createComment(comment);

                resp.sendRedirect(req.getContextPath()+"/post?id="+post.getId()+"&&status=1");
                return;

            }
            else{
                message = "Комментарий не должен быть пустым!";
            }


        }
        req.setAttribute("message",message);
        getServletContext().getRequestDispatcher("/WEB-INF/view/post.jsp").forward(req, resp);
    }



    private void setPostAttributes(HttpServletRequest req){
        req.setAttribute("tittle", post.getName());
        req.setAttribute("authorName", post.getAuthor().getUsername());
        req.setAttribute("postBody", post.getTextBody());
        req.setAttribute("tags",post.getTags());
        req.setAttribute("authorId",post.getAuthorId());
    }


}
