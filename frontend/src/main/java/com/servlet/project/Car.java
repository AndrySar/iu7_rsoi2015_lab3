package com.servlet.project;

import com.database.DTO.FactoryDTO;
import com.database.logic.Mark;
import com.dependencies.CookieWork;
import com.dependencies.Main;
import com.dependencies.SolveCar;
import com.dependencies.SolveModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 28.12.15.
 */
public class Car extends HttpServlet {

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
                    case "/car/delete":
                        String id = req.getParameter("id");
                        SolveCar.deletCar(Integer.parseInt(id));
                        resp.sendRedirect("/service/car/carsOutConstruction.jsp");
                        break;
                    case "/cars":

                        String carid = req.getParameter("id");
                        String model = req.getParameter("model");
                        String power = req.getParameter("power");
                        String year = req.getParameter("year");
                        String color = req.getParameter("color");
                        String cost = req.getParameter("cost");
                        String markId = req.getParameter("markId");

                        com.database.logic.Car car = new com.database.logic.Car(model,
                                Integer.parseInt(power),
                                year, color, Integer.parseInt(cost),
                                Integer.parseInt(markId));
                        car.setId(Integer.parseInt(carid));

                        String data = FactoryDTO.getInstance().getCarDTO().convertToJsonstring(car);

                        int code = SolveCar.updateCar(data);

                        if (code == 201) {
                            // req.getRequestDispatcher("/action/actionSuc.jsp").forward(req, resp);
                            resp.sendRedirect("/service/action/actionSuc.jsp");
                        } else {
                            resp.sendRedirect("/service/action/actionError.jsp");
                            //req.getRequestDispatcher("/action/actionError.jsp").forward(req, resp);
                        }
                        break;

                    default:
                        req.getRequestDispatcher("/service/index.jsp").forward(req, resp);
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        resp.sendRedirect("/service/car/carsOutConstruction.jsp");
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

            String model = req.getParameter("model");
            String power = req.getParameter("power");
            String year = req.getParameter("year");
            String color = req.getParameter("color");
            String cost = req.getParameter("cost");
            String markId = req.getParameter("markId");

            String data = FactoryDTO.getInstance().getCarDTO().convertToJsonstring(
                    new com.database.logic.Car(model,
                            Integer.parseInt(power),
                            year, color, Integer.parseInt(cost),
                            Integer.parseInt(markId)));

            int code = SolveCar.setCar(data);

            if (code == 201) {
                // req.getRequestDispatcher("/action/actionSuc.jsp").forward(req, resp);
                resp.sendRedirect("/service/action/actionSuc.jsp");
            } else {
                resp.sendRedirect("/service/action/actionError.jsp");
                //req.getRequestDispatcher("/action/actionError.jsp").forward(req, resp);
            }
        }
        resp.sendRedirect("/service/car/carsOutConstruction.jsp");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getServletPath();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String login = null;
        String token = CookieWork.getValueCookie(req, "token");
        if(token != null) {
            login = Main.currentSession.checkToken(token);
        }

        if(login != null) {

            String id = req.getParameter("id");
            String model = req.getParameter("model");
            String power = req.getParameter("power");
            String year = req.getParameter("year");
            String color = req.getParameter("color");
            String cost = req.getParameter("cost");
            String markId = req.getParameter("markId");

            com.database.logic.Car car = new com.database.logic.Car(model,
                    Integer.parseInt(power),
                    year, color, Integer.parseInt(cost),
                    Integer.parseInt(markId));
            car.setId(Integer.parseInt(id));

            String data = FactoryDTO.getInstance().getCarDTO().convertToJsonstring(car);

            int code = SolveCar.updateCar(data);

            if (code == 201) {
                // req.getRequestDispatcher("/action/actionSuc.jsp").forward(req, resp);
                resp.sendRedirect("/service/action/actionSuc.jsp");
            } else {
                resp.sendRedirect("/service/action/actionError.jsp");
                //req.getRequestDispatcher("/action/actionError.jsp").forward(req, resp);
            }
        }

        resp.sendRedirect("/service/car/carsOutConstruction.jsp");
    }
}
