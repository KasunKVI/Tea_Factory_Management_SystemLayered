package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.PaymentDAO;
import lk.ijse.pos.dto.PaymentDTO;
import lk.ijse.pos.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {


    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public boolean add(Payment entity) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Payment VALUES (?,?,?,?,?,?,?)";
        return SQLUtil.execute(sql,entity.getPay_id(),entity.getRate(),entity.getType(),entity.getValue(),entity.getDescription(),entity.getSupp_id(),entity.getTrp_id());

    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
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
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM Payment WHERE pay_id = ?";
        return SQLUtil.execute(sql,id);

    }

    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {

        String sql  = "SELECT pay_id FROM Payment ORDER BY pay_id DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);

        int pay_id=0;

        while (resultSet.next()){
            pay_id=resultSet.getInt(1);
        }
        return pay_id;

    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public String getPaymentId(int Id) throws SQLException {

        String sql = "SELECT pay_id FROM Payment WHERE trp_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql,Id);

        String pay_id=null;

        while (resultSet.next()){
            pay_id=resultSet.getString(1);
        }
        return pay_id;

    }
}
