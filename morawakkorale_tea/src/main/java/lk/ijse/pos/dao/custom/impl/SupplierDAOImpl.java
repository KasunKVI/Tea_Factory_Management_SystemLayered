package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.SupplierDAO;
import lk.ijse.pos.dto.SupplierDTO;
import lk.ijse.pos.entity.Supplier;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM Supplier";

        ArrayList<Supplier> supplier = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()){
            supplier.add(new Supplier(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));

        }
        return supplier;

    }

    @Override
    public boolean add(Supplier entity) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Supplier VALUES (?,?,?,?,?,?)";
        return SQLUtil.execute(sql, entity.getSup_id(),entity.getName(),entity.getContact(),entity.getReg_date(),entity.getAddress(),entity.getStatus());

    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE Supplier SET name = ?, contact = ?, address = ? WHERE sup_id = ?";
        return SQLUtil.execute(sql,entity.getName(),entity.getContact(),entity.getAddress(),entity.getSup_id());

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT name FROM Supplier WHERE sup_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql,id);

        if (resultSet.next()){
            return false;
        }else {
            return true;
        }

    }

    @Override
    public int getCount(String type) throws SQLException, ClassNotFoundException {

        int Year= Integer.parseInt(type);
        String sql = "SELECT sup_id FROM Supplier WHERE YEAR(reg_date) = ?";
        ResultSet resultSet = SQLUtil.execute(sql,Year);

        int count=0;

        while (resultSet.next()){
            count++;
        }
        return count;

    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM Supplier WHERE sup_id = ?";
        return SQLUtil.execute(sql,id);

    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM Supplier WHERE sup_id=?";
        ResultSet resultSet= SQLUtil.execute(sql,id);

        if(resultSet.next()){
            Integer sup_id= Integer.valueOf(resultSet.getString(1));
            String name=resultSet.getString(2);
            String contact=resultSet.getString(3);
            Date reg_date=resultSet.getDate(4);
            String address=resultSet.getString(5);
            String status=resultSet.getString(6);

            return new Supplier(sup_id,name,contact,reg_date,address,status);
        }
        return null;

    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {

        String sql = "SELECT sup_id FROM Supplier";
        ResultSet resultSet= SQLUtil.execute(sql);

        int count=0;

        while (resultSet.next()){
            count++;
        }
        return count;

    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {

        String sql = "SELECT sup_id FROM Supplier";
        ResultSet resultSet = SQLUtil.execute(sql);

        ArrayList<String> ids = new ArrayList<>();

        while (resultSet.next()){
            ids.add(String.valueOf(resultSet.getInt(1)));
        }
        return ids;

    }

    @Override
    public int getNewSupplierCount(int year, int month) throws SQLException {

        String sql = "SELECT sup_id FROM Supplier WHERE YEAR(reg_date) = ? AND MONTH(reg_date) = ?";
        ResultSet resultSet = SQLUtil.execute(sql,year,month);

        int count=0;

        while (resultSet.next()){
            count++;
        }
        return count;

    }


}
