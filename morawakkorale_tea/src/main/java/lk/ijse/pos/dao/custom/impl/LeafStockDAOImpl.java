package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.bo.custom.LeafStockBO;
import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.LeafStockDAO;
import lk.ijse.pos.dto.Supplier_StockDTO;
import lk.ijse.pos.entity.Supplier_Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeafStockDAOImpl implements LeafStockDAO {
    @Override
    public ArrayList<Supplier_Stock> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Supplier_Stock entity) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Supplier_Stock(sup_id,stock_id,value,bag_count,date,status) VALUES (?,?,?,?,?,?)";
        return SQLUtil.execute(sql, entity.getSup_id(), entity.getStock_id(), entity.getValue(),  entity.getBag_count(), entity.getDate(),"unpaid");

    }

    @Override
    public boolean update(Supplier_Stock entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public int getCount(String type) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM Supplier_Stock WHERE sup_id = ?";
        return SQLUtil.execute(sql, id);

    }

    @Override
    public Supplier_Stock search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return null;
    }

    @Override
    public boolean addLeafStock(List<Supplier_Stock> supplier_stock) throws SQLException, ClassNotFoundException {

        for (Supplier_Stock stock : supplier_stock) {
            if (!add(stock)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getSupplierValues(int id, int month, String value) throws SQLException {

        String sql = "SELECT "+value+" FROM Supplier_Stock WHERE MONTH(date) = ? AND sup_id = ? AND status = ?";
        ResultSet resultSet = SQLUtil.execute(sql,month,id,"unpaid");

        int count = 0;

        while (resultSet.next()){
            count+=resultSet.getInt(1);
        }
        return count;

    }

    @Override
    public boolean addPayment(int id, int month) throws SQLException {

        String sql = "UPDATE Supplier_Stock SET status = ? WHERE MONTH(date) = ? AND sup_id = ?";
        return SQLUtil.execute(sql,"paid",month,id);

    }
}
