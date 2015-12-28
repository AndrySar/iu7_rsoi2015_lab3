package com.servlet.project;

import com.dependencies.CookieWork;
import com.dependencies.Main;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 27.12.15.
 */
public class Exit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            CookieWork.deleteCookie(response, "token");
            response.sendRedirect("/service/index.jsp");
    }
}
