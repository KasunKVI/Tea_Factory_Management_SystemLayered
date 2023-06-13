package lk.ijse.pos.model;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.Order_PaymentDTO;
import lk.ijse.pos.dao.SQLUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {
//    public static int getMonthlyOrderCount(int i) throws SQLException {
//
//        int year = LocalDate.now().getYear();
//
//        String sql = "SELECT order_id FROM Orders WHERE YEAR(date) = ? AND MONTH(date) = ?";
//        ResultSet resultSet = SQLUtil.execute(sql,year,i);
//
//        int count=0;
//
//        while (resultSet.next()){
//            count++;
//        }
//        return count;
//    }

//    public static String generateOrderId() throws SQLException {
//
//            Connection con = DBConnection.getInstance().getConnection();
//
//            String sql = "SELECT order_id FROM Orders ORDER BY order_id DESC LIMIT 1";
//
//            ResultSet resultSet = con.createStatement().executeQuery(sql);
//
//            if(resultSet.next()) {
//
//                return splitOrderId(resultSet.getString(1));
//
//            }
//
//            return splitOrderId(null);
//        }
//
//        public static String splitOrderId(String currentOrderId) {
//
//
//            if(currentOrderId != null) {
//
//                String[] strings = currentOrderId.split("O");
//
//                int id = Integer.parseInt(strings[1]);
//
//                id++;
//
//                String num= String.valueOf(id);
//
//                String formattedId = "";
//
//                if(num.length()==1||num.length()==2) {
//
//                    formattedId = String.format("%03d", id);
//
//                }else{
//
//                    formattedId= String.valueOf(id);
//
//                }
//                return "O"+formattedId;
//            }
//            return "O001";
//        }

//    public static boolean placeOrder(String id, String text, String customer_id, int payId) throws SQLException {
//
//        String sql = "INSERT INTO Orders VALUES (?,?,?,?,?)";
//        return SQLUtil.execute(sql,id,LocalDate.now(),text,customer_id,payId);
//    }

//    public static double getSaleValue(String s) {
//
//        double sale=0.0;
//
//        try {
//
//            List<String> ids = Order_ProductModel.getIds(s);
//
//            for(String id : ids){
//
//
//                String sql = "SELECT total FROM Orders WHERE order_id = ?";
//                ResultSet resultSet = SQLUtil.execute(sql,id);
//
//                while (resultSet.next()) {
//                    sale += resultSet.getInt(1);
//                }
//
//            }
//
//        } catch (SQLException er) {
//            er.printStackTrace();
//        }
//
//        return sale;
//
//    }

//    public static List<Order_PaymentDTO> getAllIds() throws SQLException {
//
//        String sql = "SELECT order_id,date,total,customer_id FROM Orders";
//        List<Order_PaymentDTO> orders = new ArrayList<>();
//
//        ResultSet resultSet = SQLUtil.execute(sql);
//
//        while (resultSet.next()){
//            orders.add(new Order_PaymentDTO(
//                    resultSet.getString(1),
//                    resultSet.getDate(2).toLocalDate(),
//                    resultSet.getInt(3),
//                    resultSet.getString(4)
//            ));
//
//        }
//        return orders;
//    }
}
