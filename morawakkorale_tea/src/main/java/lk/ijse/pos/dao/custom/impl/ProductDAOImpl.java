package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.ProductDAO;
import lk.ijse.pos.dto.CartDTO;
import lk.ijse.pos.dto.ProductDTO;
import lk.ijse.pos.entity.Order_Product;
import lk.ijse.pos.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public ArrayList<Product> getAll() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM Product";

        ArrayList<Product> product = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()){
            product.add(new Product(
                    resultSet.getString(1),
                    resultSet.getDate(2).toLocalDate(),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)
            ));

        }
        return product;

    }

    @Override
    public boolean add(Product entity) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Product VALUES (?,?,?,?,?)";
        return SQLUtil.execute(sql,entity.getProduct_id(),entity.getMade_date(),entity.getQty_on_hand(),entity.getType(),entity.getUnit_price());
    }

    @Override
    public boolean update(Product entity) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE Product SET  made_date = ?, qty_on_hand = ?, type = ? WHERE product_id =?";
        return SQLUtil.execute(sql,entity.getMade_date(),entity.getQty_on_hand(),entity.getType(),entity.getProduct_id());

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT type FROM Product WHERE product_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql,id);

        if (resultSet.next()){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public int getCount(String type) throws SQLException, ClassNotFoundException {

        String sql = "SELECT product_id FROM Product WHERE type = ?";
        ResultSet resultSet = SQLUtil.execute(sql,type);

        int count=0;

        while (resultSet.next()){
            count++;
        }
        return count;

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Product search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM Product WHERE product_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql,id);

        if(resultSet.next()){

            String product_id = resultSet.getString(1);
            LocalDate made_date = resultSet.getDate(2).toLocalDate();
            Integer qty = resultSet.getInt(3);
            String type = resultSet.getString(4);
            Double unit_price = resultSet.getDouble(5);

            return new Product(product_id,made_date,qty,type,unit_price);
        }
        return null;

    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {

        String sql = "SELECT product_id FROM Product WHERE qty_on_hand > 0";
        ArrayList<String> ids = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()){
            ids.add(resultSet.getString(1));
        }
        return ids;

    }

    @Override
    public boolean updateOrderProduct(ArrayList<Order_Product> orderProducts) throws SQLException, ClassNotFoundException {

        for (Order_Product dto : orderProducts) {
            if(!updateQty(dto)) {
                return false;
            }
        }
        return true;

    }
    private static boolean updateQty(Order_Product dto) throws SQLException {

        String sql = "UPDATE Product SET qty_on_hand = (qty_on_hand - ?) WHERE product_id = ?";
        return SQLUtil.execute(sql,dto.getQty(),dto.getId_product());
    }
}
