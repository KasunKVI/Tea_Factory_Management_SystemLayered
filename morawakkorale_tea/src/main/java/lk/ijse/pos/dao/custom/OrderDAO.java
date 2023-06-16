package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Orders;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Orders> {

    public int getMonthlyOrderCount(int month) throws SQLException,ClassNotFoundException;

    public double getSales(String type) throws SQLException;


}
