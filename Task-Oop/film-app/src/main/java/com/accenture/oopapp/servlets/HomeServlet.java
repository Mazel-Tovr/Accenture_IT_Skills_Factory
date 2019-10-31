package com.accenture.oopapp.servlets;

import com.accenture.oopapp.mysqldatabase.UserTable;
import com.accenture.oopapp.mysqldatabase.interfaces.UserOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet
{
    private static UserOperation userOperation = UserTable.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String nickName = req.getParameter("nickName");
        String passWord = req.getParameter("passWord");
        System.out.println(nickName + "\t" + passWord);
        System.out.println(userOperation.isUserExist(nickName));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
