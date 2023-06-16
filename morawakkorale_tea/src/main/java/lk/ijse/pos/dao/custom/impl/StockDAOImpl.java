package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.StockDAO;
import lk.ijse.pos.entity.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAOImpl implements StockDAO {
    @Override
    public ArrayList<Stock> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public boolean add(Stock entity) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Stock VALUES (?,?,?,?,?)";
        return SQLUtil.execute(sql,entity.getStock_id(),entity.getDate(),entity.getValue(),entity.getTransporter_id(),"unpaid");

    }

    @Override
    public boolean update(Stock entity) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE Stock SET value = (value - ?) WHERE stock_id = ?";
        return SQLUtil.execute(sql,entity.getValue(),entity.getStock_id());
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


        String sql = "SELECT stock_id FROM Stock ORDER BY stock_id DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);
        if(resultSet.next()) {

            return splitOrderId(resultSet.getString(1));

        }

        return splitOrderId(null);

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public Stock search(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {

        String sql = "SELECT stock_id FROM Stock WHERE value > 0";
        ArrayList<String> stock_ids = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()){
            stock_ids.add(resultSet.getString(1));

        }
        return stock_ids;

    }

    @Override
    public boolean isExist(int date, int month) throws SQLException {

        String sql = "SELECT value FROM Stock WHERE MONTH(date) = ? AND DAY(date) = ?";
        ResultSet resultSet = SQLUtil.execute(sql,month,date);

        if (resultSet.next()){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public String getStockValue(String stock_id) throws SQLException {

        String sql = "SELECT value FROM Stock WHERE stock_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql,stock_id);

        String st_id=null;

        while (resultSet.next()) {
            st_id = resultSet.getString(1);
        }
        return st_id;

    }

    @Override
    public int getTransporterValues(int id, int month) throws SQLException {

        String sql = "SELECT value FROM Stock WHERE MONTH(date) = ? AND transporter_id = ? AND status = ?";
        ResultSet resultSet = SQLUtil.execute(sql,month,id,"unpaid");

        int count = 0;

        while (resultSet.next()){
            count+=resultSet.getInt(1);
        }

        return count;

    }

    @Override
    public boolean addPayment(int id, int month) throws SQLException {

        String sql = "UPDATE Stock SET status = ? WHERE MONTH(date) = ? AND transporter_id = ?";
        return SQLUtil.execute(sql,"paid",month,id);
    }

    @Override
    public boolean deleteTransporterDetails(int transporterId) throws SQLException {

        String sql = "UPDATE Stock SET transporter_id = null WHERE transporter_id = ?";
        return SQLUtil.execute(sql,transporterId);

    }

    public static String splitOrderId(String currentOrderId) {


        if(currentOrderId != null) {

            String[] strings = currentOrderId.split("S");

            int id = Integer.parseInt(strings[1]);

            id++;

            String num= String.valueOf(id);

            String formattedId = "";

            if(num.length()==1||num.length()==2) {

                formattedId = String.format("%03d", id);

            }else{

                formattedId= String.valueOf(id);

            }
            return "S"+formattedId;
        }
        return "S001";
    }
}
