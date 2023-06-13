package lk.ijse.pos.bo.custom.Impl;

import lk.ijse.pos.bo.custom.LeafStockBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.LeafStockDAO;
import lk.ijse.pos.dao.custom.QueryDAO;
import lk.ijse.pos.dao.custom.StockDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.StockDTO;
import lk.ijse.pos.dto.Supplier_StockDTO;
import lk.ijse.pos.entity.Stock;
import lk.ijse.pos.entity.Supplier_Stock;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class LeafStockBOImpl implements LeafStockBO {

    LeafStockDAO leafStockDAO = (LeafStockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LEAF_STOCK);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY_DAO);

    StockDAO stockDAO = (StockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK);
    @Override
    public boolean addLeafStock(ArrayList<Supplier_StockDTO> supplierStockDTOS, StockDTO dto) throws SQLException {

        ArrayList<Supplier_Stock> supplierStocks = new ArrayList<>();

        for (Supplier_StockDTO s:supplierStockDTOS){
            supplierStocks.add(new Supplier_Stock(s.getSup_id(),s.getStock_id(),s.getValue(),s.getBag_count(),s.getDate(),"unpaid"));
        }

        //return queryDAO.addLeafStock(supplierStocks,new Stock(dto.getStock_id(),dto.getDate(),dto.getValue(),dto.getTransporter_id(),"unpaid"));

        Connection con=null;
        try {

            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean stockAdd =stockDAO.add(new Stock(dto.getStock_id(),dto.getDate(),dto.getValue(),dto.getTransporter_id(),"unpaid"));
            if(!stockAdd) {

                con.rollback();
                return false;

            }

            boolean addSupplierVal = leafStockDAO.addLeafStock(supplierStocks);

                if(!addSupplierVal) {

                    con.rollback();
                    return false;

                }

                con.commit();
                return true;


        } catch (SQLException throwable) {
            throwable.printStackTrace();
            con.rollback();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public boolean deleteLeafData(String id) throws SQLException, ClassNotFoundException {

        return leafStockDAO.delete(id);

    }

    @Override
    public boolean isStockExist(int Date, int Month) throws SQLException, ClassNotFoundException {

        return stockDAO.isExist(Date,Month);

    }

    @Override
    public String generateStockId() throws SQLException, ClassNotFoundException {

        return stockDAO.generateNewID();

    }

    @Override
    public ArrayList<String> getAllStockIds() throws SQLException {

        return stockDAO.getAllIds();

    }

    @Override
    public String getStockValue(String stock_id) throws SQLException {

        return stockDAO.getStockValue(stock_id);

    }

    @Override
    public int getSupplierValues(int id, int month, String value) throws SQLException {

        return leafStockDAO.getSupplierValues(id,month,value);

    }

    @Override
    public boolean addPayment(int id, int month) throws SQLException {

        return leafStockDAO.addPayment(id,month);

    }

    @Override
    public int getTransporterValues(int id, int month) throws SQLException {

        return stockDAO.getTransporterValues(id,month);

    }

    @Override
    public boolean addStockPayment(int id, int month) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteTransporterDetails(int transporterId) throws SQLException {

        return stockDAO.deleteTransporterDetails(transporterId);

    }
}
