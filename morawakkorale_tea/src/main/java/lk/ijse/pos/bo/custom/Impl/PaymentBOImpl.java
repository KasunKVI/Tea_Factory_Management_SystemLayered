package lk.ijse.pos.bo.custom.Impl;

import lk.ijse.pos.bo.custom.PaymentBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.PaymentDAO;
import lk.ijse.pos.dto.PaymentDTO;
import lk.ijse.pos.entity.Payment;

import java.sql.SQLException;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public int getPaymentId() throws SQLException, ClassNotFoundException {

        return paymentDAO.getPaymentId();

    }

    @Override
    public boolean addPayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {

        return paymentDAO.add(new Payment(dto.getId(),dto.getRate(),dto.getType(),dto.getValue().intValue(),dto.getDescription(),dto.getSupp_id(),dto.getTrp_id()));

    }

    @Override
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException {

        return paymentDAO.delete(id);

    }
}
