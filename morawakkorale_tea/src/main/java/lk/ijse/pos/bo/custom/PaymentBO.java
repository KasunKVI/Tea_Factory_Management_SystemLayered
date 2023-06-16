package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.PaymentDTO;

import java.sql.SQLException;

public interface PaymentBO extends SuperBO {

    public int getPaymentId() throws SQLException,ClassNotFoundException;

    public boolean addPayment(PaymentDTO dto) throws SQLException,ClassNotFoundException;

    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException;

    public String getPaymentId(int id) throws SQLException;
}
