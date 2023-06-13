package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {

    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;

    public boolean addSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException ;

    public SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException;

    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;

    public int getNewSupplierCount(String year) throws SQLException, ClassNotFoundException;

    public int getSupplierCount() throws SQLException, ClassNotFoundException;

    public  int getNewSupplierCount(int year, int month) throws SQLException;

    public ArrayList<String> getAllIds() throws SQLException;

    public boolean existSupplier(String id) throws SQLException, ClassNotFoundException;
}
