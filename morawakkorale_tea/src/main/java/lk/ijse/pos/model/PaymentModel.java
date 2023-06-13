package lk.ijse.pos.model;

import lk.ijse.pos.dto.PaymentDTO;
import lk.ijse.pos.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentModel {

//
//    public static boolean deleteTransporterPaymentFromDatabase(String pay_id) throws SQLException {
//
//        String sql = "DELETE FROM Payment WHERE pay_id = ?";
//        return SQLUtil.execute(sql,pay_id);
//    }

//    public static boolean addPayment(PaymentDTO payment) throws SQLException {
//
//        String sql = "INSERT INTO Payment VALUES (?,?,?,?,?,?,?)";
//        return SQLUtil.execute(sql,payment.getId(),String.valueOf(payment.getRate()),payment.getType(),payment.getValue(),payment.getDescription(),payment.getSupp_id(),payment.getTrp_id());
//    }

//    public static int getPaymentId() throws SQLException {
//
//        String sql  = "SELECT pay_id FROM Payment ORDER BY pay_id DESC LIMIT 1";
//        ResultSet resultSet = SQLUtil.execute(sql);
//
//        int pay_id=0;
//
//        while (resultSet.next()){
//            pay_id=resultSet.getInt(1);
//        }
//      return pay_id;
//    }
}
