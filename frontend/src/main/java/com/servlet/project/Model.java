package com.servlet.project;

import com.database.DTO.FactoryDTO;
import com.database.container.CarContainer;
import com.database.logic.*;
import com.dependencies.CookieWork;
import com.dependencies.Main;
import com.dependencies.SolveCar;
import com.dependencies.SolveModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 28.12.15.
 */
public class Model extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = null;
        String token = CookieWork.getValueCookie(req, "token");
        if(token != null) {
            login = Main.currentSession.checkToken(token);
        }

        if(login != null) {
            try {

                switch (req.getServletPath()) {
                    case "/model/delete":
                        String id = req.getParameter("id");
                        if (id != null) {
                            SolveModel.deleteModel(Integer.parseInt(id));

                            CarContainer cars = SolveCar.getCarsByModel(Integer.parseInt(id));

                            for (com.database.logic.Car item : cars.getCars()) {
                                SolveCar.deletCar(item.getId());
                            }
                        }

                        resp.sendRedirect("/service/model/modelsOutConstruction.jsp");
                        break;
                    case "/model":
                        req.getRequestDispatcher("regis_auth.jsp").forward(req, resp);
                        break;
                    default:
                        req.getRequestDispatcher("index.jsp").forward(req, resp);
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        resp.sendRedirect("/service/model/modelsOutConstruction.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String login = null;
        String token = CookieWork.getValueCookie(req, "token");
        if(token != null) {
            login = Main.currentSession.checkToken(token);
        }

        if(login != null) {
            String name = req.getParameter("name");
            String contry = req.getParameter("contry");

            String data = FactoryDTO.getInstance().getModelDTO().convertToJsonstring(
                    new Mark(name, contry));
            int code = SolveModel.setModel(data);

            if (code == 201) {
                // req.getRequestDispatcher("/action/actionSuc.jsp").forward(req, resp);
                resp.sendRedirect("/service/action/actionSuc.jsp");
            } else {
                resp.sendRedirect("/service/action/actionError.jsp");
                //req.getRequestDispatcher("/action/actionError.jsp").forward(req, resp);
            }
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       req.getServletPath();
    }
}
