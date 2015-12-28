package com.database.DAO.Impl;

/**
 * Created by user on 28.11.15.
 */

import com.database.DAO.SessionDAO;
import com.database.logic.Session;
import com.database.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionDAOImpl implements SessionDAO {

    public boolean addSession(Session _session) throws SQLException {
        org.hibernate.Session session = null;
        Transaction tx = null;
        boolean fl = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(_session);
            tx.commit();

            fl = true;
        } catch (HibernateException e) {
            //JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            if(tx != null) tx.rollback();

            e.printStackTrace();
            fl = false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return fl;
    }

    public boolean updateSession(Session _session) throws SQLException {
        org.hibernate.Session session = null;
        Transaction tx = null;
        boolean fl = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(_session);
            tx.commit();

            fl = true;
        } catch (HibernateException e) {
            //JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            if(tx != null) tx.rollback();

            e.printStackTrace();
            fl = false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return fl;
    }

    public Session getSessionById(int session_id) throws SQLException {
        org.hibernate.Session session = null;
        Session _session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            _session = (Session) session.get(Session.class, session_id);
        } catch (HibernateException e) {
            // JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return _session;
    }

    public List<Session> getAllSessions() throws SQLException {
        org.hibernate.Session session = null;
        List<Session> _session = new ArrayList<Session>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            _session = session.createCriteria(Session.class).list();
        } catch (HibernateException e) {
            // JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return _session;
    }

    public boolean deleteSession(Session _session) throws SQLException {
        org.hibernate.Session session = null;
        Transaction tx = null;
        boolean fl = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(_session);
            tx.commit();

            fl = true;
        } catch (HibernateException e) {
            // JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            if(tx != null) tx.rollback();

            e.printStackTrace();
            fl = false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return fl;
    }

    public Session getSessionByToken(String token)
    {
        org.hibernate.Session session = null;
        List<Session> sl = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Query query = session.createSQLQuery("select * from session where access_token = :value1").addEntity(Session.class);
            sl = query.setString("value1", token).list();
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

        if(sl != null)
            if(sl.size() > 0)
                return sl.get(0);
            else
                return null;
        else
            return null;
    }

//    public List<Session> getSessionByUser(User user) throws SQLException {
//        org.hibernate.Session session = null;
//
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//
//            Query query = session.createSQLQuery("select * from users where login = :value1 AND passwd = :value2").addEntity(User.class);
//            user = query.setString("value1", login).setString("value2", passwd).list();
//            session.getTransaction().commit();
//
//        } catch (HibernateException e) {
//            // JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
//            e.printStackTrace();
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
//    }
}
