package lk.ijse.pos.dao;

import lk.ijse.pos.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)? daoFactory=new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{

        CUSTOMER,PRODUCT,SUPPLIER,TRANSPORTER,STOCK_PRODUCT,QUERY_DAO,LEAF_STOCK,STOCK,PAYMENT,ORDER,ORDER_PRODUCT,EMPLOYEE,LOG_IN

    }

    public SuperDAO getDAO(DAOTypes type){

        switch (type){

            case CUSTOMER:
                return new CustomerDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case TRANSPORTER:
                return new TransporterDAOImpl();
            case STOCK_PRODUCT:
                return new Stock_ProductDAOImpl();
            case QUERY_DAO:
                return new QueryDAOImpl();
            case LEAF_STOCK:
                return new LeafStockDAOImpl();
            case STOCK:
                return new StockDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_PRODUCT:
                return new OrderProductDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case LOG_IN:
                return new LogInDAOImpl();
            default:
                return null;
        }

    }
}
