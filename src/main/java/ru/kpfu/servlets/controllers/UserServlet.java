package ru.kpfu.servlets.controllers;

import ru.kpfu.servlets.entities.User;
import ru.kpfu.servlets.servies.CatService;
import ru.kpfu.servlets.servies.PostService;
import ru.kpfu.servlets.servies.ShowService;
import ru.kpfu.servlets.servies.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "/user", urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    private UserService userService;
    private PostService postService;
    private CatService catService;
    private ShowService showService;
    private User user;
    private  String mainUserEmail;

    @Override
    public void init() throws ServletException {
        this.userService = new UserService();
        this.postService = new PostService();
        this.catService = new CatService();
        this.showService = new ShowService();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        mainUserEmail = (String) session.getAttribute("email");
        long id = Long.parseLong(req.getParameter("id"));

        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            user = userOptional.get();
            showService.showPostsByUserId(user,req,postService);
            showService.showCats(user,req,catService);
            req.setAttribute("guestUsername", user.getUsername());
            req.setAttribute("guestEmail", user.getEmail());

        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message ="";
        req.setAttribute("guestUsername", user.getUsername());
        req.setAttribute("guestEmail", user.getEmail());
        req.setAttribute("email",mainUserEmail);

        showService.showPostsByUserId(user,req,postService);
        showService.showCats(user,req,catService);

        getServletContext().getRequestDispatcher("/WEB-INF/view/user.jsp").forward(req, resp);
    }


}
