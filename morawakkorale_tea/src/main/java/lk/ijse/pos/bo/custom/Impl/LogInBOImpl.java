package lk.ijse.pos.bo.custom.Impl;

import lk.ijse.pos.bo.custom.LogInBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.LogInDAO;
import lk.ijse.pos.dto.LogInDTO;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.entity.User;

import java.sql.SQLException;

public class LogInBOImpl implements LogInBO {

    LogInDAO logInDAO = (LogInDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOG_IN);
    @Override
    public String checkUser(LogInDTO login) throws SQLException {

        return logInDAO.checkUser(new User(login.getId(),login.getPassword(),"","","",""));

    }

    @Override
    public UserDTO search(String id) throws SQLException, ClassNotFoundException {

        User user = logInDAO.search(id);
        return new UserDTO(user.getId(),user.getPassword(),user.getContact(),user.getName(),user.getEmail(),user.getPosition());
    }

    @Override
    public boolean updateUser(UserDTO dto) throws SQLException, ClassNotFoundException {

        return logInDAO.update(new User(dto.getId(), dto.getPassword(), dto.getContact(), dto.getName(), dto.getEmail(), dto.getPosition()));
    }
}
