package com.servlet.project;

import com.dependencies.Main;
import org.json.JSONObject;

import javax.jws.soap.InitParam;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 20.12.15.
 */
@WebServlet("/authorization")
public class Authorization extends HttpServlet  {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String login = req.getParameter("login");
        String passwd = req.getParameter("password");
        String current_login = null;
        String token = null;

        if(login != null && passwd != null)
        {
            current_login = Main.currentSession.cheakUser(login, passwd);

            if(current_login != null)
            {
                token = Main.currentSession.createSession(current_login);
                Cookie ck = new Cookie("token", token);
                resp.addCookie(ck);

                resp.sendRedirect("/service/index.jsp");
//
//                req.setAttribute("name", current_login);
//                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }else
            {
                resp.sendRedirect("/service/model/modelsOutConstruction.jsp");
            }

        }

    }

}
