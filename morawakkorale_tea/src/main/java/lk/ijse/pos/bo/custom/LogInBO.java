package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.LogInDTO;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.entity.User;

import java.sql.SQLException;

public interface LogInBO extends SuperBO {

    public String checkUser(LogInDTO login) throws SQLException;

    public UserDTO search(String id) throws SQLException, ClassNotFoundException;

    public boolean updateUser(UserDTO dto) throws SQLException, ClassNotFoundException;

}
