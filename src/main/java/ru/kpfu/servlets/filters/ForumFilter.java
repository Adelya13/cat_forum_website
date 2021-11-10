package ru.kpfu.servlets.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/forum")
public class ForumFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        Object email = session.getAttribute("email");
        if(email != null) {
            chain.doFilter(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/signin");
        }

    }

}
