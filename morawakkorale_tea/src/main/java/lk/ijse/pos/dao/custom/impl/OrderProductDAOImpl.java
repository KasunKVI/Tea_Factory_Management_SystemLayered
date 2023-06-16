package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.OrderProductDAO;
import lk.ijse.pos.dto.CartDTO;
import lk.ijse.pos.entity.Order_Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderProductDAOImpl implements OrderProductDAO {
    @Override
    public ArrayList<Order_Product> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public boolean add(Order_Product entity) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Order_Product VALUES (?,?,?,?)";
        return SQLUtil.execute(sql,entity.getId_product(),entity.getId_order(),entity.getType(),entity.getQty());

    }

    @Override
    public boolean update(Order_Product entity) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public int getCount(String type) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public Order_Product search(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public ArrayList<String> getAllIds(String type) throws SQLException {

        String sql = "SELECT id_order FROM Order_Product WHERE type = ?";
        ArrayList<String> ids = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql,type);

        while (resultSet.next()){
            ids.add(resultSet.getString(1));

        }
        return ids;

    }

    @Override
    public boolean addOrder(ArrayList<Order_Product> orderProducts) throws SQLException, ClassNotFoundException {

        for (Order_Product dto : orderProducts) {
            if(!add(dto)) {
                return false;
            }
        }
        return true;
    }


}
