package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.StockDTO;
import lk.ijse.pos.dto.Supplier_StockDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LeafStockBO extends SuperBO {


    public boolean addLeafStock(ArrayList<Supplier_StockDTO> supplierStockDTOS, StockDTO dto2) throws SQLException;

    public boolean deleteLeafData(String id) throws SQLException, ClassNotFoundException;

    public boolean isStockExist(int Date, int Month) throws SQLException, ClassNotFoundException;

    public String generateStockId() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllStockIds() throws SQLException;

    public String getStockValue(String stock_id) throws SQLException;

    public int getSupplierValues(int id, int month, String value) throws SQLException;

    public boolean addPayment(int id, int month) throws SQLException;

    public int getTransporterValues(int id, int month) throws SQLException;

    public boolean addStockPayment(int id, int month) throws SQLException;

    public boolean deleteTransporterDetails(int transporterId) throws SQLException;

}
