package lk.ijse.pos.bo.custom.Impl;

import lk.ijse.pos.bo.custom.TransporterBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.TransporterDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.TransporterDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Transporter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransporterBOImpl implements TransporterBO {

    TransporterDAO transporterDAO = (TransporterDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TRANSPORTER);

    @Override
    public ArrayList<TransporterDTO> getAllTransporters() throws SQLException, ClassNotFoundException {

        ArrayList<TransporterDTO> allTransporters= new ArrayList<>();
        ArrayList<Transporter> all = transporterDAO.getAll();
        for (Transporter t : all) {
            allTransporters.add(new TransporterDTO(t.getTp_id(),t.getName(),t.getContact(),t.getRoute(),t.getAddress()));
        }
        return allTransporters;
    }

    @Override
    public int getTransporterCount() throws SQLException, ClassNotFoundException {

        return transporterDAO.getCount();

    }

    @Override
    public TransporterDTO searchTransporter(String id) throws SQLException, ClassNotFoundException {

        Transporter transporter = transporterDAO.search(id);
        return new TransporterDTO(transporter.getTp_id(),transporter.getName(),transporter.getContact(),transporter.getRoute(),transporter.getAddress());

    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {

        return transporterDAO.getAllIds();
    }

    @Override
    public String getRoute(Integer id) throws SQLException {

        return transporterDAO.getRoute(id);

    }

    @Override
    public boolean addTransporter(TransporterDTO dto) throws SQLException, ClassNotFoundException {

        return transporterDAO.add(new Transporter(dto.getId(), dto.getName(), dto.getContact(), dto.getRoute(), dto.getAddress()));

    }

    @Override
    public boolean updateTransporter(TransporterDTO dto) throws SQLException, ClassNotFoundException {

        return transporterDAO.update(new Transporter(dto.getId(), dto.getName(), dto.getContact(), dto.getRoute(), dto.getAddress()));

    }

    @Override
    public String getTransporterPaymentId(int transporterId) throws SQLException {

        return transporterDAO.getTransporterPaymentId(transporterId);

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return transporterDAO.delete(id);

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {

        return transporterDAO.exist(id);

    }
}
