package lk.ijse.pos.model;

import lk.ijse.pos.dto.SupplierDTO;
import lk.ijse.pos.dao.SQLUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {

//    public static boolean addSupplierToDatabase(SupplierDTO supplier) throws SQLException {
//
//        String sql = "INSERT INTO Supplier VALUES (?,?,?,?,?,?)";
//        return SQLUtil.execute(sql, supplier.getId(),supplier.getName(),supplier.getContact(),supplier.getReg_date(),supplier.getAddress(),supplier.getStatus());
//
//
//    }

//    public static SupplierDTO searchSupplierFromDatabase(String id) throws SQLException {
//
//        String sql = "SELECT * FROM Supplier WHERE sup_id=?";
//        ResultSet resultSet= SQLUtil.execute(sql,id);
//
//        if(resultSet.next()){
//            Integer sup_id= Integer.valueOf(resultSet.getString(1));
//            String name=resultSet.getString(2);
//            String contact=resultSet.getString(3);
//            Date reg_date=resultSet.getDate(4);
//            String address=resultSet.getString(5);
//            String status=resultSet.getString(6);
//
//            return new SupplierDTO(sup_id,name,contact,reg_date,address,status);
//        }
//    return null;
//    }

//    public static boolean addEditedSupplierToDatabase(SupplierDTO supplier) throws SQLException {
//
//        String sql = "UPDATE Supplier SET name = ?, contact = ?, address = ? WHERE sup_id = ?";
//        return SQLUtil.execute(sql,supplier.getName(),supplier.getContact(),supplier.getAddress(),supplier.getId());
//    }

//    public static int getSuppliersCount() throws SQLException {
//
//        String sql = "SELECT sup_id FROM Supplier";
//        ResultSet resultSet= SQLUtil.execute(sql);
//
//        int count=0;
//
//        while (resultSet.next()){
//            count++;
//        }
//        return count;
//    }

//    public static int getNewSupplierCount(int yr, int i) throws SQLException {
//
//        String sql = "SELECT sup_id FROM Supplier WHERE YEAR(reg_date) = ? AND MONTH(reg_date) = ?";
//        ResultSet resultSet = SQLUtil.execute(sql,yr,i);
//
//        int count=0;
//
//        while (resultSet.next()){
//            count++;
//        }
//        return count;
//    }

//    public static int getNewSupplierCountYear(String s) throws SQLException {
//
//        int year= Integer.parseInt(s);
//        String sql = "SELECT sup_id FROM Supplier WHERE YEAR(reg_date) = ?";
//        ResultSet resultSet = SQLUtil.execute(sql,year);
//
//        int count=0;
//
//        while (resultSet.next()){
//            count++;
//        }
//        return count;
//    }

//    public static List<SupplierDTO> getAll() throws SQLException {
//
//        String sql = "SELECT * FROM Supplier";
//
//        List<SupplierDTO> supplier = new ArrayList<>();
//
//        ResultSet resultSet = SQLUtil.execute(sql);
//
//        while (resultSet.next()){
//                supplier.add(new SupplierDTO(
//                        resultSet.getInt(1),
//                        resultSet.getString(2),
//                        resultSet.getString(3),
//                        resultSet.getDate(4),
//                        resultSet.getString(5),
//                        resultSet.getString(6)
//                ));
//
//        }
//        return supplier;
//    }

//    public static boolean deleteSupplierFromDatabase(int supplierId) throws SQLException {
//
//        String sql = "DELETE FROM Supplier WHERE sup_id = ?";
//        return SQLUtil.execute(sql,supplierId);
//    }

//    public static List<Integer> getAllIds() throws SQLException {
//
//        String sql = "SELECT sup_id FROM Supplier";
//        ResultSet resultSet = SQLUtil.execute(sql);
//
//        ArrayList<String> ids = new ArrayList<>();
//
//        while (resultSet.next()){
//            ids.add(String.valueOf(resultSet.getInt(1)));
//        }
//        return ids;
//    }

//    public static boolean isExist(String txtSupIdStock) throws SQLException {
//        String sql = "SELECT name FROM Supplier WHERE sup_id = ?";
//        ResultSet resultSet = SQLUtil.execute(sql,txtSupIdStock);
//
//        if (resultSet.next()){
//            return false;
//        }else {
//            return true;
//        }
//    }
}
