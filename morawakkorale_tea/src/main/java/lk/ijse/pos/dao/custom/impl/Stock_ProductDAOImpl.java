package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.Stock_ProductDAO;
import lk.ijse.pos.dto.Stock_ProductDTO;
import lk.ijse.pos.entity.Stock_Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Stock_ProductDAOImpl implements Stock_ProductDAO {

    @Override
    public ArrayList<Stock_Product> getAll() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM Stock_Product";
        ArrayList<Stock_Product> stock_product = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()){
            stock_product.add(new Stock_Product(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            ));

        }
        return stock_product;

    }

    @Override
    public boolean add(Stock_Product entity) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Stock_Product VALUES (?,?,?)";
        return SQLUtil.execute(sql,entity.getId_product(),entity.getId_stock(),entity.getLeaf_value());

    }

    @Override
    public boolean update(Stock_Product entity) throws SQLException, ClassNotFoundException {
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

        String sql = "DELETE FROM Stock_Product WHERE id_product = ?";
        return SQLUtil.execute(sql,id);

    }

    @Override
    public Stock_Product search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM Stock_Product WHERE id_product = ?";
        ResultSet resultSet = SQLUtil.execute(sql,id);

        if(resultSet.next()){

            String product_id = resultSet.getString(1);
            String stock_id = resultSet.getString(2);
            Integer leaf_value = resultSet.getInt(3);

            return new Stock_Product(product_id,stock_id,leaf_value);
        }
        return null;

    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }
}
