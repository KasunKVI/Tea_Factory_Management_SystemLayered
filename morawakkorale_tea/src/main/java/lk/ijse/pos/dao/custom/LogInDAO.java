package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dto.LogInDTO;
import lk.ijse.pos.entity.User;

import java.sql.SQLException;

public interface LogInDAO extends CrudDAO<User> {

    public String checkUser(User entity) throws SQLException;
}
