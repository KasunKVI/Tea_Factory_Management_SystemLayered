package lk.ijse.pos.model;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.StockDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddStockModel {

//    public static boolean addStockToDatabase(List supplier_stock, StockDTO stock) throws SQLException {
//
//        Connection con=null;
//        try {
//
//            con = DBConnection.getInstance().getConnection();
//            con.setAutoCommit(false);
//
//            boolean stockAdd = StockModel.addStockToDatabase(stock);
//            if(stockAdd){
//                boolean addSupplierVal = Supplier_StockModel.addSupplierValuesToDatabase(supplier_stock);
//                if(addSupplierVal){
//                    con.commit();
//                    return true;
//                }
//            }
//            return false;
//
//        } catch (SQLException throwable) {
//            throwable.printStackTrace();
//            con.rollback();
//            return false;
//        } finally {
//        System.out.println("finally");
//        con.setAutoCommit(true);
//      }
//    }
}
