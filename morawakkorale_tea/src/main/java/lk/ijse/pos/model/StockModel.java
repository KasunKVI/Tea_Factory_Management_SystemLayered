package lk.ijse.pos.model;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.StockDTO;
import lk.ijse.pos.dao.SQLUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockModel {

//    public static boolean addStockToDatabase(StockDTO stock) throws SQLException {
//
//        String sql = "INSERT INTO Stock VALUES (?,?,?,?,?)";
//        return SQLUtil.execute(sql,stock.getStock_id(),stock.getDate(),stock.getValue(),stock.getTransporter_id(),"unpaid");
//
//    }


//    public static String generateStockId() throws SQLException {
//
//        Connection con = DBConnection.getInstance().getConnection();
//
//        String sql = "SELECT stock_id FROM Stock ORDER BY stock_id DESC LIMIT 1";
//
//        ResultSet resultSet = con.createStatement().executeQuery(sql);
//
//        if(resultSet.next()) {
//
//            return splitOrderId(resultSet.getString(1));
//
//        }
//
//        return splitOrderId(null);
//    }

//    public static String splitOrderId(String currentOrderId) {
//
//
//        if(currentOrderId != null) {
//
//            String[] strings = currentOrderId.split("S");
//
//            int id = Integer.parseInt(strings[1]);
//
//            id++;
//
//            String num= String.valueOf(id);
//
//            String formattedId = "";
//
//            if(num.length()==1||num.length()==2) {
//
//                formattedId = String.format("%03d", id);
//
//            }else{
//
//                formattedId= String.valueOf(id);
//
//            }
//            return "S"+formattedId;
//        }
//        return "S001";
//    }

//    public static List<String> getSupplierValue() throws SQLException {
//
//        String sql = "SELECT stock_id FROM Stock WHERE value > 0";
//        List<String> stock_ids = new ArrayList<>();
//        ResultSet resultSet = SQLUtil.execute(sql);
//
//        while (resultSet.next()){
//            stock_ids.add(resultSet.getString(1));
//
//        }
//        return stock_ids;
//    }

//    public static boolean deleteTransporterDetails(int transporterId) throws SQLException {
//
//        String sql = "UPDATE Stock SET transporter_id = null WHERE transporter_id = ?";
//        return SQLUtil.execute(sql,transporterId);
//
//    }

//    public static String getStockValue(String stock_id) throws SQLException {
//
//        String sql = "SELECT value FROM Stock WHERE stock_id = ?";
//        ResultSet resultSet = SQLUtil.execute(sql,stock_id);
//
//        String st_id=null;
//
//        while (resultSet.next()) {
//            st_id = resultSet.getString(1);
//        }
//        return st_id;
//    }

//    public static boolean updateStock(String stock_id, int leaf_value) throws SQLException {
//
//        String sql = "UPDATE Stock SET value = (value - ?) WHERE stock_id = ?";
//        return SQLUtil.execute(sql,leaf_value,stock_id);
//
//    }

//    public static int getTransporterValues(int id, int month) throws SQLException {
//
//        String sql = "SELECT value FROM Stock WHERE MONTH(date) = ? AND transporter_id = ? AND status = ?";
//        ResultSet resultSet = SQLUtil.execute(sql,month,id,"unpaid");
//
//        int count = 0;
//
//        while (resultSet.next()){
//            count+=resultSet.getInt(1);
//        }
//
//        return count;
//    }

//    public static boolean addPayment(int id, int month) throws SQLException {
//
//        String sql = "UPDATE Stock SET status = ? WHERE MONTH(date) = ? AND transporter_id = ?";
//        return SQLUtil.execute(sql,"paid",month,id);
//    }


//    public static boolean isExist(int date, int month) throws SQLException {
//
//        String sql = "SELECT value FROM Stock WHERE MONTH(date) = ? AND DAY(date) = ?";
//        ResultSet resultSet = SQLUtil.execute(sql,month,date);
//
//        if (resultSet.next()){
//            return true;
//        }else {
//            return false;
//        }
   // }
}
