package lk.ijse.pos.dao.custom;


import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Order_Product;
import lk.ijse.pos.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO extends CrudDAO<Product> {

    public boolean updateOrderProduct(ArrayList<Order_Product> orderProducts) throws SQLException,ClassNotFoundException;

}
