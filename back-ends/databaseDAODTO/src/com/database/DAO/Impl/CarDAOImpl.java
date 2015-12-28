package com.database.DAO.Impl;

/**
 * Created by user on 24.12.15.
 */
import com.database.logic.Car;
import com.database.DAO.CarDAO;
import com.database.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {

    public Integer addCar(Car car) throws SQLException {

        org.hibernate.Session session = null;
        Transaction tx = null;
        Integer carID = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            carID = (Integer)session.save(car);
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
        return carID;
    }

    public boolean updateCar(Car car) throws SQLException {

        org.hibernate.Session session = null;
        Transaction tx = null;
        boolean fl = false;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(car);
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
    public Car getCarById(int car_id) throws SQLException {
        org.hibernate.Session session = null;
        Car car = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            car = (Car) session.get(Car.class, car_id);
        } catch (HibernateException e) {
            // JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return car;
    }

    public List getAllCars() throws SQLException {
        org.hibernate.Session session = null;
        List<Car> cars = new ArrayList<Car>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            cars = session.createCriteria(Car.class).list();
        } catch (HibernateException e) {
            // JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);

            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cars;
    }

    public boolean deleteCar(Car car) throws SQLException {
        org.hibernate.Session session = null;
        Transaction tx = null;
        boolean fl = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(car);
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

    public  List getCarsByMark(int markId) throws SQLException {
        org.hibernate.Session session = null;
        List<Car> cars = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Query query = session.createSQLQuery("select * from car where mark_id = :value1").addEntity(Car.class);
            cars = query.setInteger("value1", markId).list();
            tx.commit();

        } catch (HibernateException e) {
            if(tx != null) tx.rollback();

            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        if(cars != null)
                return cars;
        else
            return null;
    }

    public  List getAllCarsLimit(int limit, int offset) throws SQLException {
        org.hibernate.Session session = null;
        List<Car> cars = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            org.hibernate.Criteria criteria = session.createCriteria(Car.class);
            criteria.setFirstResult(offset);
            criteria.setMaxResults(limit);

            cars = criteria.list();
            session.getTransaction().commit();

        } catch (HibernateException e) {
            // JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            if(tx != null) tx.rollback();

            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        if(cars != null)
            return cars;
        else
            return null;
    }

}
