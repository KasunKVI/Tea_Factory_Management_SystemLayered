package lk.ijse.pos.model;

import lk.ijse.pos.dto.Supplier_BillDTO;
import lk.ijse.pos.dto.Supplier_StockDTO;
import lk.ijse.pos.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Supplier_StockModel {

//    public static boolean addSupplierValuesToDatabase(Supplier_StockDTO supplier_stock) throws SQLException {
//
//        String sql = "INSERT INTO Supplier_Stock(sup_id,stock_id,value,bag_count,date,status) VALUES (?,?,?,?,?,?)";
//        return SQLUtil.execute(sql, supplier_stock.getSup_id(), supplier_stock.getStock_id(), supplier_stock.getValue(), supplier_stock.getBag_count(), supplier_stock.getDate(),"unpaid");
//
//    }


//    public static boolean deleteSupplierFromDatabase(int supplierId) throws SQLException {
//
//        String sql = "DELETE FROM Supplier_Stock WHERE sup_id = ?";
//        return SQLUtil.execute(sql, supplierId);
//    }
//
//    public static boolean addSupplierValuesToDatabase(List<Supplier_StockDTO> supplier_stock) throws SQLException {
//
//        for (Supplier_StockDTO stock : supplier_stock) {
//            if (!addSupplierValuesToDatabase(stock)) {
//                return false;
//            }
//        }
//        return true;
//    }

//    public static List<Supplier_BillDTO> getAll() throws SQLException {
//
//        String sql = "SELECT sup_id,value,bag_count,payment_ FROM Supplier_Stock";
//        ResultSet resultSet = SQLUtil.execute(sql);
//
//        List<Supplier_BillDTO> supplier_bills = new ArrayList<>();
//
//        while (resultSet.next()) {
//            supplier_bills.add(new Supplier_BillDTO(
//                    resultSet.getInt(1),
//                    resultSet.getInt(2),
//                    resultSet.getInt(3),
//                    resultSet.getDouble(4)
//            ));
//        }
//        return supplier_bills;
//    }

//    public static int getSupplierValues(Integer id, int month, String value) throws SQLException {
//
//        String sql = "SELECT "+value+" FROM Supplier_Stock WHERE MONTH(date) = ? AND sup_id = ? AND status = ?";
//        ResultSet resultSet = SQLUtil.execute(sql,month,id,"unpaid");
//
//        int count = 0;
//
//        while (resultSet.next()){
//            count+=resultSet.getInt(1);
//        }
//        return count;
//    }

//    public static boolean addPayment(int id, int month) throws SQLException {
//
//        String sql = "UPDATE Supplier_Stock SET status = ? WHERE MONTH(date) = ? AND sup_id = ?";
//        return SQLUtil.execute(sql,"paid",month,id);
//    }
}
