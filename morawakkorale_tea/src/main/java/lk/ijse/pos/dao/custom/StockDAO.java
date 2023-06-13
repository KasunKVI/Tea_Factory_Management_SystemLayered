package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Stock;

import java.sql.SQLException;

public interface StockDAO extends CrudDAO<Stock> {

    public boolean isExist(int date, int month) throws SQLException;

    public String generateNewID() throws SQLException, ClassNotFoundException;

    public String getStockValue(String stock_id) throws SQLException;

    public int getTransporterValues(int id, int month) throws SQLException;

    public boolean addPayment(int id, int month) throws SQLException;

    public boolean deleteTransporterDetails(int transporterId) throws SQLException;
}
