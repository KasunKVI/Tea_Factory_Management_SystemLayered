package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dto.Supplier_StockDTO;
import lk.ijse.pos.entity.Supplier_Stock;

import java.sql.SQLException;
import java.util.List;

public interface LeafStockDAO extends CrudDAO<Supplier_Stock> {

    public boolean addLeafStock(List<Supplier_Stock> supplier_stock) throws SQLException, ClassNotFoundException;

    public int getSupplierValues(int id, int month, String value) throws SQLException;

    public boolean addPayment(int id, int month) throws SQLException;
}
