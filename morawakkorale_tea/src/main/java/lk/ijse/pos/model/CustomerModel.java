package lk.ijse.pos.model;

import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {

//    public static boolean addCustomerToDatabase(CustomerDTO customer) throws SQLException {
//
//        String sql = "INSERT INTO Customer VALUES(?,?,?,?,?) ";
//        return SQLUtil.execute(sql, customer.getId(), customer.getName(), customer.getOrigin(), customer.getContact(),customer.getEmployee_id());
//
//    }

//    public static int getCustomerCount() throws SQLException {
//
//        String sql = "SELECT cust_id FROM Customer";
//        ResultSet resultSet = SQLUtil.execute(sql);
//
//        int count=0;
//
//        while (resultSet.next()){
//            count++;
//        }
//
//        return count;
//    }

//    public static List<CustomerDTO> getAll() throws SQLException {
//
//        String sql = "SELECT * FROM Customer";
//
//        List<CustomerDTO>  customer = new ArrayList<>();
//
//        ResultSet resultSet = SQLUtil.execute(sql);
//
//        while (resultSet.next()){
//            customer.add(new CustomerDTO(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getString(5)
//            ));
//
//        }
//        return customer;
//    }

//    public static CustomerDTO searchCustomerFromDatabase(String id) throws SQLException {
//
//        String sql = "SELECT * FROM Customer WHERE cust_id = ?";
//        ResultSet resultSet = SQLUtil.execute(sql,id);
//
//        if(resultSet.next()){
//
//            String cust_id= resultSet.getString(1);
//            String name=resultSet.getString(2);
//            String origin=resultSet.getString(3);
//            String contact=resultSet.getString(4);
//            String employee_id=resultSet.getString(5);
//
//            return new CustomerDTO(cust_id,name,origin,contact,employee_id);
//        }
//        return null;
//    }

//    public static boolean addEditedCustomersToDatabase(CustomerDTO customer) throws SQLException {
//
//        String sql = "UPDATE Customer SET name = ?, contact = ?, employee_id = ? WHERE cust_id = ?";
//        return SQLUtil.execute(sql,customer.getName(),customer.getContact(),customer.getEmployee_id(),customer.getId());
//
//    }

//    public static boolean deleteCustomerFromDatabase(String customerId) throws SQLException {
//
//        String sql = "DELETE FROM Customer WHERE cust_id = ?";
//        return SQLUtil.execute(sql,customerId);
//    }

//    public static List<String> getAllIds() throws SQLException {
//
//        String sql = "SELECT cust_id FROM Customer";
//        List<String> ids = new ArrayList<>();
//
//        ResultSet resultSet = SQLUtil.execute(sql);
//
//        while (resultSet.next()){
//            ids.add(resultSet.getString(1));
//        }
//        return ids;
//    }
//
//    public static boolean isExist(String text) throws SQLException {
//
//        String sql = "SELECT name FROM Customer WHERE cust_id = ?";
//        ResultSet resultSet = SQLUtil.execute(sql,text);
//
//        if (resultSet.next()){
//            return true;
//        }else {
//            return false;
//        }
//    }
}
