package com.database.DAO.Impl;

/**
 * Created by user on 28.11.15.
 */
import com.database.DAO.UserDAO;
import com.database.logic.User;
import com.database.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO {

    public boolean addUser(User user) throws SQLException {
        Transaction tx = null;
        Session session = null;
        boolean fl = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            fl = true;
        }
        catch (HibernateException e) {
            //JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            if(tx != null) tx.rollback();

            e.printStackTrace();
            fl = false;
        } finally {
            if (session != null && session.isOpen()) {
                session.clear();
                session.close();
            }
        }

        return fl;
    }

    public boolean updateUser(User user) throws SQLException {
        Session session = null;
        Transaction tx = null;
        boolean fl = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();

            fl = true;
        } catch (HibernateException e) {
            //JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            if(tx != null) tx.rollback();

            e.printStackTrace();
            fl = false;

        } finally {
            if (session != null && session.isOpen()) {
                session.clear();
                session.close();
            }
        }
        return fl;
    }

    public User getUserById(int user_id) throws SQLException {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = (User) session.get(User.class, user_id);
        } catch (HibernateException e) {
           // JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

    public List<User> getAllUsers() throws SQLException {
        Session session = null;
        List<User> users = new ArrayList<User>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            users = session.createCriteria(User.class).list();
        } catch (HibernateException e) {
           // JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return users;
    }

    public boolean deleteUser(User user) throws SQLException {
        Session session = null;
        Transaction tx = null;
        boolean fl = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();

            fl = true;
        } catch (HibernateException e) {
           // JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            if(tx != null) tx.rollback();

            e.printStackTrace();
            fl = false;

        } finally {
            if (session != null && session.isOpen()) {
                session.clear();
                session.close();
            }
        }
        return fl;
    }

    public User getUserByLogin(String login)
    {
        Transaction tx = null;
        Session session = null;
        List user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Query query = session.createSQLQuery("select * from users where login like :value").addEntity(User.class);
            user = query.setString("value", login).list();
            tx.commit();

        } catch (HibernateException e) {
            // JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            if(tx != null) tx.rollback();

            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        if(user.isEmpty())
            return null;
        else
            return (User)user.get(user.size() - 1);
    }

    public User getUserByLoginByPasswd(String login, String passwd)
    {
        Transaction tx = null;
        Session session = null;
        List user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Query query = session.createSQLQuery("select * from users where login = :value1 AND passwd = :value2").addEntity(User.class);
            user = query.setString("value1", login).setString("value2", passwd).list();
            tx.commit();

        } catch (HibernateException e) {
            // JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            if(tx != null) tx.rollback();

            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        if(user.isEmpty())
            return null;
        else
            return (User)user.get(user.size() - 1);
    }

}
