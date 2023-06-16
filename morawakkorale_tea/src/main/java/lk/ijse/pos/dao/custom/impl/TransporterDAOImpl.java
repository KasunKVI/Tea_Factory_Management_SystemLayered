package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.TransporterDAO;
import lk.ijse.pos.dto.TransporterDTO;
import lk.ijse.pos.entity.Transporter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransporterDAOImpl implements TransporterDAO {

    @Override
    public ArrayList<Transporter> getAll() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM Transporter";

        ArrayList<Transporter>  transporter = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()){
            transporter.add(new Transporter(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));

        }
        return transporter;

    }

    @Override
    public boolean add(Transporter entity) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Transporter(tp_id,name,contact,route,address) VALUES (?,?,?,?,?)";
        return SQLUtil.execute(sql, entity.getTp_id(),entity.getName(),entity.getContact(),entity.getRoute(),entity.getAddress());

    }

    @Override
    public boolean update(Transporter entity) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE Transporter SET name = ?, contact = ?, route = ?, address = ? WHERE tp_id = ?";
        return SQLUtil.execute(sql,entity.getName(),entity.getContact(),entity.getRoute(),entity.getAddress(),entity.getTp_id());

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT name FROM Transporter WHERE tp_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql,id);

        if (resultSet.next()){
            return false;
        }else {
            return true;
        }
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

        String sql = "DELETE FROM Transporter WHERE tp_id = ?";
        return SQLUtil.execute(sql,id);

    }

    @Override
    public Transporter search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM Transporter WHERE tp_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql,id);

        if (resultSet.next()){

            Integer tp_id=resultSet.getInt(1);
            String name=resultSet.getString(2);
            String contact=resultSet.getString(3);
            String route=resultSet.getString(4);
            String address=resultSet.getString(5);

            return new Transporter(tp_id,name,contact,route,address);
        }
        return null;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {


        String sql = "SELECT tp_id FROM Transporter";
        ResultSet resultSet = SQLUtil.execute(sql);

        int count = 0;

        while (resultSet.next()){
            count++;
        }
        return count;

    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {

        String sql = "SELECT tp_id FROM Transporter";
        ArrayList<String> idS = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()){

            idS.add(String.valueOf(resultSet.getInt(1)));
        }
        return idS;
    }

    @Override
    public String getRoute(Integer id) throws SQLException {

        String sql = "SELECT route FROM Transporter WHERE tp_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql,id);

        String route=null;

        while (resultSet.next()){

            route=resultSet.getString(1);
        }
        return route;

    }

    @Override
    public String getTransporterPaymentId(int transporterId) throws SQLException {

        String sql = "SELECT pay_id FROM Transporter WHERE tp_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql,transporterId);

        String pay_id=null;

        while (resultSet.next()){
            pay_id=resultSet.getString(1);
        }
        return pay_id;

    }
}
