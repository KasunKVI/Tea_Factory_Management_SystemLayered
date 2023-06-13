package lk.ijse.pos.bo.custom.Impl;

import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.EmployeeDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {

        ArrayList<CustomerDTO> allCustomers= new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer c : all) {
            allCustomers.add(new CustomerDTO(c.getCust_id(), c.getName(), c.getOrigin(), c.getContact(), c.getEmployee_id()));
        }
        return allCustomers;
    }

    @Override
    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {

       return customerDAO.add(new Customer(dto.getId(),dto.getName(),dto.getOrigin(),dto.getContact(),dto.getEmployee_id()));

    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {

        return customerDAO.update(new Customer(dto.getId(),dto.getName(),dto.getOrigin(),dto.getContact(),dto.getEmployee_id()));

    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {

        Customer exist = customerDAO.search(id);
        return new CustomerDTO(exist.getCust_id(),exist.getName(),exist.getOrigin(),exist.getContact(),exist.getEmployee_id());

    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {

        return customerDAO.delete(id);

    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {

        return customerDAO.exist(id);

    }

    @Override
    public ArrayList<String> getEmployeeIds() throws SQLException, ClassNotFoundException {

       return employeeDAO.getEmployeeIds();

    }

    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {

        return customerDAO.getCount();

    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {

        return customerDAO.getAllIds();

    }
}
