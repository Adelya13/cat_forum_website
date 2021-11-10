package ru.kpfu.servlets.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {

        if (req.getCharacterEncoding() == null){
            req.setCharacterEncoding("UTF-8");
        }
        chain.doFilter(req,resp);

    }
}
