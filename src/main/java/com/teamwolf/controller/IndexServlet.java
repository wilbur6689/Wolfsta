package com.teamwolf.controller;

import com.google.gson.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet(urlPatterns = "/")
public class IndexServlet extends HttpServlet
{

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        PrintWriter page = response.getWriter();

        response.setStatus(200);

        response.setHeader("Content-Type", "text/html");

        Gson objectMaker = new Gson();


        page.println("<html> <head> <title>Hello World</title> </head> <body>");

        page.println("<p>Hello friend. I am here to show that this project is a web app");

        page.println("<blockquote>");
        page.print( "request.getMethod(): \"" + request.getMethod() + "\"<br/>");
        page.print( "request.getQueryString(): \"" + request.getQueryString() + "\"<br/>");



        page.println("</blockquote> <blockquote><br/>");

        page.println("</blockquote>");

        page.println("</body></html>");



    }
}
