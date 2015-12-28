package com.database.DAO;

/**
 * Created by user on 28.11.15.
 */
import com.database.DAO.Impl.CarDAOImpl;
import com.database.DAO.Impl.MarkDAOImpl;
import com.database.DAO.Impl.SessionDAOImpl;
import com.database.DAO.Impl.UserDAOImpl;


public class Factory {

    private static UserDAO userDAO = null;
    private static SessionDAO sessionDAO = null;
    private static CarDAO carDAO = null;
    private static MarkDAO markDAO = null;
    private static Factory instance = null;


    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public UserDAO getUserDAO(){
        if (userDAO == null){
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }

    public SessionDAO getSessionDAO() {
        if (sessionDAO == null){
            sessionDAO = new SessionDAOImpl();
        }
        return sessionDAO;
    }

    public CarDAO getCarDAO() {
        if (carDAO == null){
            carDAO = new CarDAOImpl();
        }
        return carDAO;
    }

    public MarkDAO getMarkDAO() {
        if (markDAO == null){
            markDAO = new MarkDAOImpl();
        }
        return markDAO;
    }
}
