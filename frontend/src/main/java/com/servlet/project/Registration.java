package com.servlet.project;



import com.database.DTO.FactoryDTO;
import com.database.logic.User;
import com.dependencies.Main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 20.12.15.
 */
@WebServlet("/registration")
public class Registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String login = req.getParameter("usernamesignup");
        String email = req.getParameter("emailsignup");
        String passwd = req.getParameter("passwordsignup");

        String data = FactoryDTO.getInstance().getUserDTO().convertToJsonstring(new User(login, passwd, email));

        int code = Main.currentSession.createUser(data);
        String state = null;

        if(code == 201)
        {
//            state = "Пользователь успешно зарегистрирован";
//            req.setAttribute("state", state);
//            req.getRequestDispatcher("successfuly.jsp").forward(req, resp);
            resp.sendRedirect("/service/action/actionSuc.jsp");
        }else
        {
            resp.sendRedirect("/service/action/actionError.jsp");
//            state = "Ошибка";
//            req.setAttribute("state", state);
//            req.getRequestDispatcher("successfuly.jsp").forward(req, resp);
        }

    }

}
