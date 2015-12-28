package com.database.DAO;

/**
 * Created by user on 28.11.15.
 */

import com.database.logic.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    public boolean addUser(User user) throws SQLException;
    public boolean updateUser(User user) throws SQLException;
    public User getUserById(int id) throws SQLException;
    public List getAllUsers() throws SQLException;
    public boolean deleteUser(User user) throws SQLException;
    public User getUserByLogin(String login) throws SQLException;
    public User getUserByLoginByPasswd(String login, String passwd) throws SQLException;

}
