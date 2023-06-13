package lk.ijse.pos.model;

import lk.ijse.pos.dto.LogInDTO;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInModel {



//    public static String checkUser(LogInDTO login) throws SQLException {
//
//        String sql = "SELECT * FROM User WHERE id = ?";
//        ResultSet resultSet = SQLUtil.execute(sql,login.getId());
//
//        if(resultSet.next()) {
//
//            String id = resultSet.getString(1);
//            String password = resultSet.getString(2);
//
//            return password;
//        }
//       return null;
//    }

//    public static UserDTO getUserDetails(String id) throws SQLException {
//
//        String sql = "SELECT * FROM User WHERE id = ?";
//        ResultSet resultSet = SQLUtil.execute(sql,id);
//
//        while (resultSet.next()){
//
//            String uId =resultSet.getString(1);
//            String pass = resultSet.getString(2);
//            String contact = resultSet.getString(3);
//            String name = resultSet.getString(4);
//            String email = resultSet.getString(5);
//            String position = resultSet.getString(6);
//
//            return new UserDTO(uId,pass,contact,name,email,position);
//        }
//        return null;
//    }

//    public static boolean updateUser(String id, String contact, String name, String email) throws SQLException {
//
//
//        String sql = "UPDATE User SET contact = ?, name = ?, email = ? WHERE id = ?";
//        return SQLUtil.execute(sql,contact,name,email,id);
//    }
}
