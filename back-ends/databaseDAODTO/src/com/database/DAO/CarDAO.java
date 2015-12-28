package com.database.DAO;

import com.database.logic.Car;
import com.database.logic.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 24.12.15.
 */
public interface CarDAO {
    public Integer addCar(Car car) throws SQLException;
    public boolean updateCar(Car car) throws SQLException;
    public Car getCarById(int id) throws SQLException;
    public List getAllCars() throws SQLException;
    public boolean deleteCar(Car car) throws SQLException;
    public  List getCarsByMark(int markId) throws SQLException;
    public  List getAllCarsLimit(int limit, int offset) throws SQLException;
}
