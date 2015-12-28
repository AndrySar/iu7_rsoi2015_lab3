package com.servlet.project;


import com.dependencies.CookieWork;
import com.dependencies.Main;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        String login = null;
//        String token = CookieWork.getValueCookie(request, "token");
//        if(token != null) {
//            login = Main.currentSession.checkToken(token);
//        }
//
//        request.setAttribute("name", login);
//        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = null;
        String token = CookieWork.getValueCookie(request, "token");
        if(token != null) {
            login = Main.currentSession.checkToken(token);
        }

        request.setAttribute("name", login);
        request.getRequestDispatcher("index.jsp").forward(request, response);



    }

}