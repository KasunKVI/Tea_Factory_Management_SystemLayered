package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SuperDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeDAO extends SuperDAO {

    public ArrayList<String> getEmployeeIds() throws SQLException,ClassNotFoundException;
}
