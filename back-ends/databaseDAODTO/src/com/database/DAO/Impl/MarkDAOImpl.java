package com.database.DAO.Impl;

import com.database.DAO.MarkDAO;
import com.database.logic.Car;
import com.database.logic.Mark;

import com.database.util.HibernateUtil;
import net.sf.ehcache.search.expression.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.test.cache.infinispan.functional.Citizen;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 24.12.15.
 */
public class MarkDAOImpl implements MarkDAO{

    public Integer addMark(Mark mark) throws SQLException{
        org.hibernate.Session session = null;
        Transaction tx = null;
        Integer modelID = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            modelID = (Integer)session.save(mark);

            tx.commit();
        } catch (HibernateException e) {
            //JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            if(tx != null) tx.rollback();

            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return modelID;
    }

    public boolean updateMark(Mark mark) throws SQLException{
        org.hibernate.Session session = null;
        Transaction tx = null;
        boolean fl = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(mark);
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
    public Mark getMarkById(int mark_id) throws SQLException{
        org.hibernate.Session session = null;
        Mark mark = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            mark = (Mark) session.get(Mark.class, mark_id);
        } catch (HibernateException e) {
            // JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return mark;
    }

    public List getAllMarks() throws SQLException{
        org.hibernate.Session session = null;
        List<Mark> marks = new ArrayList<Mark>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            marks = session.createCriteria(Mark.class).list();
        } catch (HibernateException e) {
            // JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return marks;
    }

    public boolean deleteMark(Mark mark) throws SQLException{
        org.hibernate.Session session = null;
        Transaction tx = null;
        boolean fl = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(mark);
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

    public  List getAllMarksLimit(int limit, int offset) throws SQLException {
        org.hibernate.Session session = null;
        List<Mark> marks = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            org.hibernate.Criteria criteria = session.createCriteria(Mark.class);
            criteria.setFirstResult(offset);
            criteria.setMaxResults(limit);

//            Query query = session.createQuery(Mark.class);
//            query.setFirstResult(offset);
//            query.setMaxResults(limit);

            marks = criteria.list();
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

        if(marks != null)
            return marks;
        else
            return null;
    }



}
