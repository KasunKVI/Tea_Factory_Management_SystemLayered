package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {


    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException ;

    public ArrayList<String> getEmployeeIds() throws SQLException,ClassNotFoundException;

    public int getCustomerCount() throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllIds()throws SQLException;

}
