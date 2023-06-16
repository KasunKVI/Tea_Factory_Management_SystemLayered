package lk.ijse.pos.bo.custom.Impl;

import lk.ijse.pos.bo.custom.SupplierBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.SupplierDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.SupplierDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {


        ArrayList<SupplierDTO> allSuppliers= new ArrayList<>();
        ArrayList<Supplier> all = supplierDAO.getAll();
        for (Supplier s : all) {
            allSuppliers.add(new SupplierDTO(s.getSup_id(),s.getName(),s.getContact(),s.getReg_date(),s.getAddress(),s.getStatus()));
        }
        return allSuppliers;

    }

    @Override
    public boolean addSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {

        return supplierDAO.add(new Supplier(dto.getId(),dto.getName(),dto.getContact(),dto.getReg_date(),dto.getAddress(),dto.getStatus()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {

        return supplierDAO.update(new Supplier(dto.getId(),dto.getName(),dto.getContact(),dto.getReg_date(),dto.getAddress(),dto.getStatus()));

    }

    @Override
    public SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException {

        Supplier supplier = supplierDAO.search(id);
        return new SupplierDTO(supplier.getSup_id(),supplier.getName(),supplier.getContact(),supplier.getReg_date(),supplier.getAddress(),supplier.getStatus());

    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {

        return supplierDAO.delete(id);

    }

    @Override
    public int getNewSupplierCount(String year) throws SQLException, ClassNotFoundException {

        return supplierDAO.getCount(year);

    }

    @Override
    public int getSupplierCount() throws SQLException, ClassNotFoundException {

       return supplierDAO.getCount();

    }

    @Override
    public int getNewSupplierCount(int year, int month) throws SQLException {

        return supplierDAO.getNewSupplierCount(year,month);

    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {

        return supplierDAO.getAllIds();

    }

    @Override
    public boolean existSupplier(String id) throws SQLException, ClassNotFoundException {

        return supplierDAO.exist(id);

    }
}
