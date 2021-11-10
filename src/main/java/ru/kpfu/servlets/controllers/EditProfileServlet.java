package ru.kpfu.servlets.controllers;


import ru.kpfu.servlets.entities.User;
import ru.kpfu.servlets.exceptions.DuplicateDataException;
import ru.kpfu.servlets.servies.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/edit")
public class EditProfileServlet extends HttpServlet {
    private UserService userService;
    private User user;
   // private FileService fileService;

    @Override
    public void init(){
        this.userService = new UserService();
        //this.fileService = new FileService();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("status") != null){
            if(req.getParameter("status").equals("1")){
                req.setAttribute("message", "Изменения успешно внесены");
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
        req.getSession().setAttribute("email", user.getEmail());

//        if(req.getParameter("editphoto")!= null){
//            Part part = req.getPart("file");
//            DbFile dbFile = fileService.upload(part.getSubmittedFileName(), part.getInputStream());
//            resp.sendRedirect(req.getContextPath()+"/edit");
//            return;
//        }

        if(req.getParameter("upload") != null){
            String username =  req.getParameter("username");
            String email = req.getParameter("email");
            try{
                if(!user.getUsername().equals(username) || !user.getEmail().equals(email)){
                    User newuser = new User(email,username,user.getId());
                    userService.updateUser(user,newuser);
                    user = newuser;

                    resp.sendRedirect(req.getRequestURI()+"?status=1");
                    return;
                }

            }
            catch (DuplicateDataException e){
                message= "Такой email существует!";
            }
        }

        req.setAttribute("message",message);
        getServletContext().getRequestDispatcher("/WEB-INF/view/editprofile.jsp").forward(req, resp);
    }
}
