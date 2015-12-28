package com.database.DAO;

import com.database.logic.Mark;
import com.database.logic.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 24.12.15.
 */
public interface MarkDAO {
    public Integer addMark(Mark mark) throws SQLException;
    public boolean updateMark(Mark mark) throws SQLException;
    public Mark getMarkById(int id) throws SQLException;
    public List getAllMarks() throws SQLException;
    public boolean deleteMark(Mark mark) throws SQLException;
    public  List getAllMarksLimit(int limit, int offset) throws SQLException;
}
