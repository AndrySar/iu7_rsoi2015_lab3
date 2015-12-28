package com.database.DAO;

import com.database.logic.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 28.11.15.
 */
public interface SessionDAO {
    public boolean addSession(Session session) throws SQLException;
    public boolean updateSession(Session session) throws SQLException;
    public Session getSessionById(int id) throws SQLException;
    public List getAllSessions() throws SQLException;
    public boolean deleteSession(Session session) throws SQLException;
    public Session getSessionByToken(String token) throws SQLException;
//    public List getSessionByUser(User user) throws SQLException;


}
