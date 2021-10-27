package ru.kpfu.servlets.controllers;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calc/*")
public class FirstServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String error = "";
        String num1 = req.getParameter("a");
        String num2 = req.getParameter("b");

        if ( num1 == null || num2 == null){

            error = "Numbers shouldn't be null ";
            resp.getWriter().print(generateOutput("none","",error));
            return;

        }
        else {
            double a = Double.parseDouble(num1);
            double b = Double.parseDouble(num2);

            if(Math.abs(b-0) < 0.0000000000000000001){
                error = "Divisor shouldn't be 0";
                resp.getWriter().print(generateOutput("none","",error));
                return;
            }

            else{
                int c1 = (int) (a/b*10000);
                double c =  c1*1.0/10000;
                resp.getWriter().print(generateOutput(String.valueOf(c),"Successful done",error));
                return;

            }


        }


    }
    protected String generateOutput(String c, String notice, String error){
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>First Servlet</title>\n" +
                "</head>\n" +
                "<body>\n");
        if(notice != null){
            sb.append("<div style=\"color:green;font-size:30px;\">" + notice + "</div>\n");
        }
        String cHtmlString;
        if(c != null && !c.isEmpty()) {
            cHtmlString = "value=\""+c+"\"";
        }
        else{
            cHtmlString ="";
        } 
        sb.append("  <form action=\"\" method=\"GET\">\n" +
                "      <input type=\"text\" name=\"t1\" " + cHtmlString + "><br>\n");
        if(!error.equals("")) {

                sb.append("<div style=\"color:red\"> " + error + " </div>");

        }
        sb.append(
                "  </form>\n" +
                        "</body>\n" +
                        "</html>\n");

        return sb.toString();
    }
}
