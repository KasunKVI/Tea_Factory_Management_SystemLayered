package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Order_Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderProductDAO extends CrudDAO<Order_Product> {
   public boolean addOrder(ArrayList<Order_Product> orderProducts) throws SQLException,ClassNotFoundException;

   public ArrayList<String> getAllIds(String type) throws SQLException;
}
