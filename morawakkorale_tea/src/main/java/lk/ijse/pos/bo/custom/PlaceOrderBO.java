package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderBO extends SuperBO {

    public boolean placeOrder(OrderDTO dto, ArrayList<Order_ProductDTO> list) throws SQLException, ClassNotFoundException ;

    public int getMonthlyOrderCount(int month) throws SQLException,ClassNotFoundException;

    public double getSales(String type) throws SQLException;

    public ArrayList<Order_PaymentDTO> getAllOrders() throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllIds()throws SQLException;

    public String generateOrderId() throws SQLException, ClassNotFoundException;
}
