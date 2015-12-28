package com.servlet.project;

import com.dependencies.Main;

import javax.jws.soap.InitParam;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 20.12.15.
 */
@WebServlet(urlPatterns = {"/regis", "/auth"})
//@WebServlet("/regis")
public class Crossing extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        switch (req.getServletPath())
        {
            case "/regis":
                req.getRequestDispatcher("regis_auth.jsp").forward(req, resp);
                break;
            case "/auth":

                break;
            default:
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                break;
        }


    }

}
