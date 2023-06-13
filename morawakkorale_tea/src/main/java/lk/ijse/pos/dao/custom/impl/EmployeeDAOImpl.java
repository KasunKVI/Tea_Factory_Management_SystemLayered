package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.EmployeeDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<String> getEmployeeIds() throws SQLException, ClassNotFoundException {

        String sql = "SELECT emp_id FROM Employee";
        ArrayList<String> emp_ids = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()){
            emp_ids.add(resultSet.getString(1));

        }
        return emp_ids;

    }
}
