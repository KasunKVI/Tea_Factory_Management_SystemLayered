package lk.ijse.pos.model;

import lk.ijse.pos.dto.CartDTO;
import lk.ijse.pos.dto.ProductDTO;
import lk.ijse.pos.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {

//    public static boolean addProductToDatabase(ProductDTO product) throws SQLException {
//
//        String sql = "INSERT INTO Product VALUES (?,?,?,?,?)";
//        return SQLUtil.execute(sql,product.getId(),product.getMade_date(),product.getQty_on_hand(),product.getType(),product.getUnit_price());
//    }

//    public static ProductDTO searchProductFromDatabase(String id) throws SQLException {
//
//        String sql = "SELECT * FROM Product WHERE product_id = ?";
//        ResultSet resultSet = SQLUtil.execute(sql,id);
//
//        if(resultSet.next()){
//
//            String product_id = resultSet.getString(1);
//            LocalDate made_date = resultSet.getDate(2).toLocalDate();
//            Integer qty = resultSet.getInt(3);
//            String type = resultSet.getString(4);
//            Double unit_price = resultSet.getDouble(5);
//
//            return new ProductDTO(product_id,made_date,qty,type,unit_price);
//        }
//        return null;
//    }

//    public static int getProductCountCount(String type) throws SQLException {
//
//        String sql = "SELECT product_id FROM Product WHERE type = ?";
//        ResultSet resultSet = SQLUtil.execute(sql,type);
//
//        int count=0;
//
//        while (resultSet.next()){
//            count++;
//        }
//        return count;
//    }

//    public static boolean addEditedProductToDatabase(ProductDTO product) throws SQLException {
//
//        String sql = "UPDATE Product SET  made_date = ?, qty_on_hand = ?, type = ? WHERE product_id =?";
//        return SQLUtil.execute(sql,product.getMade_date(),product.getQty_on_hand(),product.getType(),product.getId());
//    }

  //  public static List<ProductDTO> getAll() throws SQLException {

//        String sql = "SELECT * FROM Product";
//
//        List<ProductDTO> product = new ArrayList<>();
//        ResultSet resultSet = SQLUtil.execute(sql);
//
//        while (resultSet.next()){
//            product.add(new ProductDTO(
//                    resultSet.getString(1),
//                    resultSet.getDate(2).toLocalDate(),
//                    resultSet.getInt(3),
//                    resultSet.getString(4),
//                    resultSet.getDouble(5)
//            ));
//
//        }
//        return product;
//    }

//    public static int getProductCount(String s) throws SQLException {
//
//        String sql = "SELECT product_id FROM Product WHERE type = ?";
//        ResultSet resultSet = SQLUtil.execute(sql,s);
//
//        int count = 0;
//
//        while (resultSet.next()){
//
//            count++;
//
//        }
//        return count;
//    }

//    public static List<String> getAllIds() throws SQLException {
//
//        String sql = "SELECT product_id FROM Product WHERE qty_on_hand > 0";
//        List<String> ids = new ArrayList<>();
//
//        ResultSet resultSet = SQLUtil.execute(sql);
//
//        while (resultSet.next()){
//            ids.add(resultSet.getString(1));
//        }
//        return ids;
//    }

//    public static ProductDTO getAll(String item_id) throws SQLException {
//
//        String sql = "SELECT * FROM Product WHERE product_id = ?";
//        ResultSet resultSet = SQLUtil.execute(sql,item_id);
//
//        if(resultSet.next()){
//
//            String product_id = resultSet.getString(1);
//            LocalDate made_date = resultSet.getDate(2).toLocalDate();
//            Integer qty = resultSet.getInt(3);
//            String type = resultSet.getString(4);
//            Double unit_price = resultSet.getDouble(5);
//
//            return new ProductDTO(product_id,made_date,qty,type,unit_price);
//        }
//        return null;
//    }

//    public static boolean updateProductCount(List<CartDTO> cartDTOS) throws SQLException {
//        for (CartDTO dto : cartDTOS) {
//            if(!updateQty(dto)) {
//                return false;
//            }
//        }
//        return true;
//    }

//    private static boolean updateQty(CartDTO dto) throws SQLException {
//
//        String sql = "UPDATE Product SET qty_on_hand = (qty_on_hand - ?) WHERE product_id = ?";
//        return SQLUtil.execute(sql,dto.getQty(),dto.getId());
//    }

//    public static int getProductQty(String txt) {
//
//        String sql = "SELECT qty_on_hand FROM Product WHERE product_id = ?";
//
//        int qty = 0;
//        try {
//            ResultSet resultSet = SQLUtil.execute(sql,txt);
//
//            while (resultSet.next()){
//                qty=resultSet.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return qty;
//    }
//
//    public static boolean isExist(String text) throws SQLException {
//        String sql = "SELECT type FROM Product WHERE product_id = ?";
//        ResultSet resultSet = SQLUtil.execute(sql,text);
//
//        if (resultSet.next()){
//            return true;
//        }else {
//            return false;
//        }
//    }
}
