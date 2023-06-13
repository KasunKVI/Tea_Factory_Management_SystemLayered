package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.Impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory==null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{

        CUSTOMER,PRODUCT,ADD_PRODUCT,SUPPLIER,LEAF_STOCK,PAYMENT,PLACE_ORDER,TRANSPORTER,LOG_IN

    }

    public SuperBO getBO(BOTypes type){

        switch (type){

            case CUSTOMER:
                return new CustomerBOImpl();
            case ADD_PRODUCT:
                return new AddProductBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case LEAF_STOCK:
                return new LeafStockBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            case TRANSPORTER:
                return new TransporterBOImpl();
            case LOG_IN:
                return new LogInBOImpl();
            default:
                return null;
        }
    }
}
