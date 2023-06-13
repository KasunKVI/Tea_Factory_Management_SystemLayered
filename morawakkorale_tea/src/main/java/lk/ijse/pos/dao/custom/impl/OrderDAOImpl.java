package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderProductDAO;
import lk.ijse.pos.dto.Order_PaymentDTO;
import lk.ijse.pos.entity.Orders;
import lk.ijse.pos.model.Order_ProductModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    OrderProductDAO orderProductDAO = (OrderProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_PRODUCT);

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {

        String sql = "SELECT* FROM Orders";
        ArrayList<Orders> orders = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()) {
            orders.add(new Orders(
                    resultSet.getString(1),
                    resultSet.getDate(2).toLocalDate(),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            ));

        }
        return orders;

    }

    @Override
    public boolean add(Orders entity) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Orders VALUES (?,?,?,?,?)";
        return SQLUtil.execute(sql, entity.getOrder_id(), entity.getDate(), entity.getTotal(), entity.getCustomer_id(), entity.getPayment_id());

    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Orders search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return null;
    }

    @Override
    public int getMonthlyOrderCount(int month) throws SQLException, ClassNotFoundException {


        int year = LocalDate.now().getYear();

        String sql = "SELECT order_id FROM Orders WHERE YEAR(date) = ? AND MONTH(date) = ?";
        ResultSet resultSet = SQLUtil.execute(sql, year, month);

        int count = 0;

        while (resultSet.next()) {
            count++;
        }
        return count;

    }

    @Override
    public double getSales(String type) throws SQLException {

        double sale = 0.0;

        try {

            List<String> ids = orderProductDAO.getAllIds(type);

            for (String id : ids) {


                String sql = "SELECT total FROM Orders WHERE order_id = ?";
                ResultSet resultSet = SQLUtil.execute(sql, id);

                while (resultSet.next()) {
                    sale += resultSet.getInt(1);
                }

            }

        } catch (SQLException er) {
            er.printStackTrace();
        }

        return sale;

    }

    @Override
    public String generateOrderId() throws SQLException {

        String sql = "SELECT order_id FROM Orders ORDER BY order_id DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);

        if (resultSet.next()) {

            return splitOrderId(resultSet.getString(1));

        }

        return splitOrderId(null);
    }

    public static String splitOrderId(String currentOrderId) {


        if (currentOrderId != null) {

            String[] strings = currentOrderId.split("O");

            int id = Integer.parseInt(strings[1]);

            id++;

            String num = String.valueOf(id);

            String formattedId = "";

            if (num.length() == 1 || num.length() == 2) {

                formattedId = String.format("%03d", id);

            } else {

                formattedId = String.valueOf(id);

            }
            return "O" + formattedId;
        }
        return "O001";
    }
}
