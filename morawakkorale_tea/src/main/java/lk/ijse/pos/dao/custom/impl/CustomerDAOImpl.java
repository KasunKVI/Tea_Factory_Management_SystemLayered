package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM Customer";

        ArrayList<Customer> customer = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()){
            customer.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));

        }
        return customer;
    }

    @Override
    public boolean add(Customer entity) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Customer VALUES(?,?,?,?,?) ";
        return SQLUtil.execute(sql, entity.getCust_id(), entity.getName(), entity.getOrigin(), entity.getContact(),entity.getEmployee_id());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE Customer SET name = ?, contact = ?, employee_id = ? WHERE cust_id = ?";
        return SQLUtil.execute(sql,entity.getName(),entity.getContact(),entity.getEmployee_id(),entity.getCust_id());

    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM Customer WHERE cust_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql,id);

        if(resultSet.next()){

            String cust_id= resultSet.getString(1);
            String name=resultSet.getString(2);
            String origin=resultSet.getString(3);
            String contact=resultSet.getString(4);
            String employee_id=resultSet.getString(5);

            return new Customer(cust_id,name,origin,contact,employee_id);
        }
        return null;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {

        String sql = "SELECT cust_id FROM Customer";
        ResultSet resultSet = SQLUtil.execute(sql);

        int count=0;

        while (resultSet.next()){
            count++;
        }

        return count;

    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {

        String sql = "SELECT cust_id FROM Customer";
        ArrayList<String> ids = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()){
            ids.add(resultSet.getString(1));
        }
        return ids;

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM Customer WHERE cust_id = ?";
        return SQLUtil.execute(sql,id);

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT name FROM Customer WHERE cust_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql,id);

        return resultSet.next();

    }

    @Override
    public int getCount(String type) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }
}
