package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.TransporterDTO;
import lk.ijse.pos.entity.Transporter;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TransporterBO extends SuperBO {

    public ArrayList<TransporterDTO> getAllTransporters() throws SQLException, ClassNotFoundException;
    public int getTransporterCount() throws SQLException, ClassNotFoundException;

    public TransporterDTO searchTransporter(String id) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllIds() throws SQLException;

    public String getRoute(Integer id) throws SQLException;

    public boolean addTransporter(TransporterDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateTransporter(TransporterDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public boolean exist(String id) throws SQLException, ClassNotFoundException;

}
