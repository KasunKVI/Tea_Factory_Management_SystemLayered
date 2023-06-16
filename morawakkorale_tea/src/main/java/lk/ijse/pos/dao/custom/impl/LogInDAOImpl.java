package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.LogInDAO;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LogInDAOImpl implements LogInDAO {

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(User entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE User SET contact = ?, name = ?, email = ? WHERE id = ?";
        return SQLUtil.execute(sql,entity.getContact(),entity.getName(),entity.getEmail(),entity.getId());

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public int getCount(String type) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM User WHERE id = ?";
        ResultSet resultSet = SQLUtil.execute(sql,id);

        while (resultSet.next()){

            String uId =resultSet.getString(1);
            String pass = resultSet.getString(2);
            String contact = resultSet.getString(3);
            String name = resultSet.getString(4);
            String email = resultSet.getString(5);
            String position = resultSet.getString(6);

            return new User(uId,pass,contact,name,email,position);
        }
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
    public String checkUser(User entity) throws SQLException {

        String sql = "SELECT * FROM User WHERE id = ?";
        ResultSet resultSet = SQLUtil.execute(sql,entity.getId());

        if(resultSet.next()) {

            String id = resultSet.getString(1);
            String password = resultSet.getString(2);

            return password;
        }
        return null;

    }
}
