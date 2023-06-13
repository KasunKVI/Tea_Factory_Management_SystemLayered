package lk.ijse.pos.bo.custom.Impl;

import lk.ijse.pos.bo.custom.AddProductBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ProductDAO;
import lk.ijse.pos.dao.custom.QueryDAO;
import lk.ijse.pos.dao.custom.StockDAO;
import lk.ijse.pos.dao.custom.Stock_ProductDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.ProductDTO;
import lk.ijse.pos.dto.Stock_ProductDTO;
import lk.ijse.pos.entity.Product;
import lk.ijse.pos.entity.Stock;
import lk.ijse.pos.entity.Stock_Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AddProductBOImpl implements AddProductBO {

    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    Stock_ProductDAO stock_productDAO = (Stock_ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK_PRODUCT);

    StockDAO stockDAO = (StockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK);
    @Override
    public ArrayList<ProductDTO> getAllProducts() throws SQLException, ClassNotFoundException {

        ArrayList<ProductDTO> allProducts = new ArrayList<>();
        ArrayList<Product> all = productDAO.getAll();
        for (Product p : all) {
            allProducts.add(new ProductDTO(p.getProduct_id(),p.getMade_date(),p.getQty_on_hand(),p.getType(),p.getUnit_price()));
        }
        return allProducts;

    }

    @Override
    public boolean addProduct(ProductDTO dto, Stock_ProductDTO dto1) throws SQLException, ClassNotFoundException {

        Connection con = null;

        try {

            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSave = stockDAO.update(new Stock(dto1.getStock_id(), LocalDate.now(),dto1.getLeaf_value(),105,""));

            if (!isSave){

                con.rollback();
                return false;

            }

                boolean isAdd = productDAO.add(new Product(dto.getId(), dto.getMade_date(),dto.getQty_on_hand(),dto.getType(),dto.getUnit_price()));

                if(!isAdd) {

                    con.rollback();
                    return false;

                }

                    boolean isDone = stock_productDAO.add(new Stock_Product(dto1.getProduct_id(), dto1.getStock_id(), dto1.getLeaf_value()));

                    if(!isDone) {

                        con.rollback();
                        return false;

                    }

                        con.commit();
                        return true;

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            con.rollback();
            return false;
        }finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public boolean updateProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {

        return productDAO.update(new Product(dto.getId(), dto.getMade_date(),dto.getQty_on_hand(), dto.getType(), dto.getUnit_price()));

    }


    @Override
    public ProductDTO searchProduct(String id) throws SQLException, ClassNotFoundException {

        if (id == null) {
            return null;
        }else {
            Product product = productDAO.search(id);
            return new ProductDTO(product.getProduct_id(), product.getMade_date(), product.getQty_on_hand(), product.getType(), product.getUnit_price());
        }

    }

    @Override
    public ArrayList<Stock_ProductDTO> getAllStock_Products() throws SQLException, ClassNotFoundException {

        ArrayList<Stock_ProductDTO> allProducts = new ArrayList<>();
        ArrayList<Stock_Product> all = stock_productDAO.getAll();
        for (Stock_Product p : all) {
            allProducts.add(new Stock_ProductDTO(p.getId_product(),p.getId_stock(),p.getLeaf_value()));
        }
        return allProducts;

    }


    @Override
    public Stock_ProductDTO searchStock_Product(String id) throws SQLException, ClassNotFoundException {

        Stock_Product stockProduct = stock_productDAO.search(id);
        return new Stock_ProductDTO(stockProduct.getId_product(),stockProduct.getId_stock(),stockProduct.getLeaf_value());

    }

    @Override
    public int getProductCount(String product) throws SQLException, ClassNotFoundException {

        return productDAO.getProductCount(product);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {

       return productDAO.exist(id);

    }

}
