package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Transporter;

import java.sql.SQLException;

public interface TransporterDAO extends CrudDAO<Transporter> {

    public String getRoute(Integer id) throws SQLException;



}
