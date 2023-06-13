package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.ProductDTO;
import lk.ijse.pos.dto.Stock_ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddProductBO extends SuperBO {

    public ArrayList<ProductDTO> getAllProducts() throws SQLException, ClassNotFoundException;

    public boolean addProduct(ProductDTO dto,Stock_ProductDTO dto1) throws SQLException, ClassNotFoundException ;

    public boolean updateProduct(ProductDTO dto) throws SQLException, ClassNotFoundException ;

    public ProductDTO searchProduct(String id) throws SQLException, ClassNotFoundException;

    public ArrayList<Stock_ProductDTO> getAllStock_Products() throws SQLException, ClassNotFoundException;

    public Stock_ProductDTO searchStock_Product(String id) throws SQLException, ClassNotFoundException;

    public int getProductCount(String product) throws SQLException, ClassNotFoundException ;

    public boolean exist(String id) throws SQLException, ClassNotFoundException;

}
