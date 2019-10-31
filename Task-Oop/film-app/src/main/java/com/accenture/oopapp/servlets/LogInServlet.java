package com.accenture.oopapp.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LogInServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException
    {
        String nickName = httpServletRequest.getParameter("nickName");
        String passWord = httpServletRequest.getParameter("passWord");
        System.out.println(nickName + "\t" + passWord);
        httpServletResponse.getWriter().print("Hello from servlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}