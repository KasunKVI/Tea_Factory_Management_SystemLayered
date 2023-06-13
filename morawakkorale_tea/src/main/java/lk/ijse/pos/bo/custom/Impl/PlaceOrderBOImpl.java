package lk.ijse.pos.bo.custom.Impl;

import lk.ijse.pos.bo.custom.PlaceOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderProductDAO;
import lk.ijse.pos.dao.custom.ProductDAO;
import lk.ijse.pos.dao.custom.QueryDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.Order_PaymentDTO;
import lk.ijse.pos.dto.Order_ProductDTO;
import lk.ijse.pos.entity.Order_Product;
import lk.ijse.pos.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY_DAO);

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);

    OrderProductDAO orderProductDAO = (OrderProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_PRODUCT);
    @Override
    public boolean placeOrder(OrderDTO dto, ArrayList<Order_ProductDTO> list) throws SQLException, ClassNotFoundException {

        ArrayList<Order_Product> orderProducts = new ArrayList<>();

        for (Order_ProductDTO orderProductDTO : list){
            orderProducts.add(new Order_Product(orderProductDTO.getId_product(),orderProductDTO.getId_order(),orderProductDTO.getType(),orderProductDTO.getQty()));
        }



        Connection con = null;

        try {

            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = orderDAO.add(new Orders(dto.getOrder_id(),dto.getDate(),dto.getTotal(),dto.getCustomer_id(),dto.getPayment_id()));

            if(!isSaved) {

                con.rollback();
                return false;

            }
                boolean isUpdate = productDAO.updateOrderProduct(orderProducts);

                if (!isUpdate) {

                    con.rollback();
                    return false;

                }
                    boolean isAdd = orderProductDAO.addOrder(orderProducts);

                    if (!isAdd) {

                        con.rollback();
                        return false;

                    }
                    con.commit();
                    return true;


        } catch (SQLException throwable) {
            throwable.printStackTrace();
            con.rollback();
            return false;
        } finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public int getMonthlyOrderCount(int month) throws SQLException, ClassNotFoundException {

        return orderDAO.getMonthlyOrderCount(month);

    }

    @Override
    public double getSales(String type) throws SQLException {

        return orderDAO.getSales(type);

    }

    @Override
    public ArrayList<Order_PaymentDTO> getAllOrders() throws SQLException, ClassNotFoundException {

        ArrayList<Orders> orders = orderDAO.getAll();
        ArrayList<Order_PaymentDTO> orderPaymentDTOS = new ArrayList<>();

        for (Orders orders1 :orders){

            orderPaymentDTOS.add(new Order_PaymentDTO(orders1.getOrder_id(),orders1.getDate(),orders1.getTotal(),orders1.getCustomer_id()));
        }
        return orderPaymentDTOS;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {

        return productDAO.getAllIds();

    }

    @Override
    public String generateOrderId() throws SQLException {

        return orderDAO.generateOrderId();

    }
}
