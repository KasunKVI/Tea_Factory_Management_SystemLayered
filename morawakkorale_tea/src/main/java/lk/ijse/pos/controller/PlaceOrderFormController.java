package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.AddProductBO;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.bo.custom.PaymentBO;
import lk.ijse.pos.bo.custom.PlaceOrderBO;
import lk.ijse.pos.dto.*;
import lk.ijse.pos.view.tdm.PlaceOrderTM;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class PlaceOrderFormController implements Initializable {

    //components from placeOrder form
    @FXML
    private TableColumn<?, ?> clmItemId;

    @FXML
    private TableColumn<?, ?> clmItemQty;

    @FXML
    private TableColumn<?, ?> clmItemTotal;

    @FXML
    private TableColumn<?, ?> clmItemType;

    @FXML
    private TableColumn<?, ?> clmItemUnitPrice;

    @FXML
    private ComboBox<String> cmbOrderCustomerIds;

    @FXML
    private ComboBox<String> cmbOrderItemIds;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblProductMadeDate;

    @FXML
    private Label lblOrderProductType;

    @FXML
    private Label lblOrderTotalPrice;

    @FXML
    private Label lblProductQtyOnHand;

    @FXML
    private Label lblProductUnitPrice;

    @FXML
    private TableView<PlaceOrderTM> tblOrderCart;

    @FXML
    private TextField txtOrderProductQty;


    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACE_ORDER);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    AddProductBO addProductBO = (AddProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADD_PRODUCT);
    //observableList for initialize table
    ObservableList<PlaceOrderTM> obList = FXCollections.observableArrayList();

    public void discardPlaceOrderForm(ActionEvent actionEvent) {
        clearItemDetails();
        cmbOrderCustomerIds.setValue(null);
    }

    //add selected product to cart table
    public void addProductToCart(ActionEvent actionEvent) {

        if(cmbOrderItemIds.getValue()==null||cmbOrderCustomerIds.getValue()==null) {

            new Alert(Alert.AlertType.ERROR, "Please Select Item And Customer First").show();

        }else if(txtOrderProductQty.getText().isEmpty()) {

            new Alert(Alert.AlertType.ERROR, "Please add quantity").show();
            txtOrderProductQty.requestFocus();

        }else {

            int qtyTxt = Integer.parseInt(txtOrderProductQty.getText());
            int qtyLbl = Integer.parseInt(lblProductQtyOnHand.getText());

            if (qtyTxt > qtyLbl || qtyTxt == 0) {

                new Alert(Alert.AlertType.ERROR, "Please Enter Valid Quantity").show();
                txtOrderProductQty.requestFocus();

            } else {


                String item_id = cmbOrderItemIds.getValue();
                String type = lblOrderProductType.getText();
                Double unit_price = Double.valueOf(lblProductUnitPrice.getText());
                Integer qty = qtyTxt;
                Double total = unit_price * qty;

                PlaceOrderTM placeOrderTM = new PlaceOrderTM(item_id, type, unit_price, qty, total);

                obList.add(placeOrderTM);
                tblOrderCart.setItems(obList);
                tblOrderCart.refresh();

               // cmbOrderItemIds.getItems().remove(cmbOrderItemIds.getValue().indexOf(item_id));
                cmbOrderItemIds.getSelectionModel().clearSelection();
                calculateNetTotal();
                clearItemDetails();
            }

        }

    }

    //place order and update database
    public void placeOrder(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        if(tblOrderCart.getItems().isEmpty()){

            new Alert(Alert.AlertType.ERROR, " Please add item to your cart first").show();

        }else {

            int pay = paymentBO.getPaymentId();
            int payId = pay + 1;

            PaymentDTO payment = new PaymentDTO(payId, 0, "Order Payment", Double.parseDouble(lblOrderTotalPrice.getText()), (cmbOrderCustomerIds.getValue()) + "-->" + (LocalDate.now()), null, null);
            try {
                paymentBO.addPayment(payment);
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }


            String id = lblOrderId.getText();
            String customer_id = cmbOrderCustomerIds.getValue();

            ArrayList<Order_ProductDTO>  orderProductDTOS= new ArrayList<>();
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {

                PlaceOrderTM tm = obList.get(i);

                Order_ProductDTO orderProductDTO = new Order_ProductDTO(tm.getItem_id(),id,tm.getType(),tm.getQty());
                orderProductDTOS.add(orderProductDTO);
            }

            String orderTotalPrice = lblOrderTotalPrice.getText();
            orderTotalPrice = orderTotalPrice.replace(".", "");

            int orderPrice = Integer.parseInt(orderTotalPrice);

            OrderDTO orderDTO = new OrderDTO(id,LocalDate.now(),orderPrice,customer_id,payId);

            boolean isPlaced = placeOrderBO.placeOrder(orderDTO,orderProductDTOS);

            //PlaceOrderModel.placeOrder(id, customer_id, payId, lblOrderTotalPrice.getText(), cartDTOS);

            if (isPlaced) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
                tblOrderCart.getItems().clear();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Not Placed!").show();
            }
        }

    }

    //show all orders report
    public void showReport(ActionEvent actionEvent) throws FileNotFoundException, JRException, SQLException, ClassNotFoundException {


                ArrayList<Order_PaymentDTO> orders = placeOrderBO.getAllOrders();
                ArrayList<Order_PaymentDTO> order_payments = new ArrayList<>();

                for (Order_PaymentDTO order_payment : orders) {

                    String id = order_payment.getOrder_id();
                    LocalDate date = order_payment.getDate();
                    int total = order_payment.getTotal();
                    String customer = order_payment.getCustomer();

                    order_payments.add(new Order_PaymentDTO(
                            id,
                            date,
                            total,
                            customer
                    ));
                }

                File file = ResourceUtils.getFile("/home/kaviyakv/Desktop/Morawakkorale_Tea/morawakkorale_tea/src/main/resources/reports/orderDetails.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(order_payments);
                Map<String, Object> map = new HashMap<>();
                map.put("CreatedBy", "Kasun Kavinda - GDSE65");
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);
                JasperViewer.viewReport(jasperPrint, false);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initializeCustomerIdsBox();
        initializeItemIdsBox();
        setCellValueFactory();
        generateNextOrderId();

    }

    private void setCellValueFactory() {

        clmItemId.setCellValueFactory(new PropertyValueFactory<>("Item_id"));
        clmItemType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        clmItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("Unit_price"));
        clmItemQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        clmItemTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
    }

    //initialize item ids to combo box
    private void initializeItemIdsBox() {

        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            ArrayList<String> itIds = placeOrderBO.getAllIds();

            for(String ids:itIds){
                obList.add(ids);
            }
            cmbOrderItemIds.setItems(obList);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    //initialize customer ids to combo box
    private void initializeCustomerIdsBox() {

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            ArrayList<String> cIds = customerBO.getAllIds();

            for(String ids : cIds){

                obList.add(ids);
            }

            cmbOrderCustomerIds.setItems(obList);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    //set values to labels when selected product
    public void selectedProductId(ActionEvent actionEvent) {

        String item_id = cmbOrderItemIds.getValue();

        try {

            ProductDTO product = addProductBO.searchProduct(item_id);

            if (product!=null) {

                Optional<PlaceOrderTM> optOrderDetail = tblOrderCart.getItems().stream().filter(detail -> detail.getItem_id().equals(item_id)).findFirst();
                lblProductQtyOnHand.setText((optOrderDetail.isPresent() ? product.getQty_on_hand() - optOrderDetail.get().getQty() : product.getQty_on_hand()) + "");

                lblOrderProductType.setText(product.getType());
                lblProductUnitPrice.setText(String.valueOf(product.getUnit_price()));
              //  lblProductQtyOnHand.setText(String.valueOf(product.getQty_on_hand()));
                lblProductMadeDate.setText(String.valueOf(product.getMade_date()));

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    //calculate order total
    private void calculateNetTotal() {
        double netTotal = 0.0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {

            double total  = (Double) clmItemTotal.getCellData(i);
            netTotal += total;

        }
        lblOrderTotalPrice.setText(String.valueOf(netTotal));
    }

    //generate order id
    public void generateNextOrderId(){

        try {

            String order_id = placeOrderBO.generateOrderId();
            lblOrderId.setText(order_id);
            lblOrderDate.setText(String.valueOf(LocalDate.now()));

        } catch (SQLException throwable) {

            throwable.printStackTrace();

        }

    }

    //check product qty
    public void enterProductQty(KeyEvent keyEvent) {


        if(cmbOrderItemIds.getValue()==null||!txtOrderProductQty.getText().matches(Regex.quantity())) {
            FontChanger.setTextColorRed(txtOrderProductQty);
        }else {
            int qtyTxt = Integer.parseInt(txtOrderProductQty.getText());
            int qtyLbl = Integer.parseInt(lblProductQtyOnHand.getText());

            if (qtyTxt > qtyLbl || qtyTxt == 0) {

                FontChanger.setTextColorRed(txtOrderProductQty);
            } else {
                FontChanger.setTextBlack(txtOrderProductQty);
            }
        }
    }

    //clear order form
    public void clearItemDetails(){

        lblOrderProductType.setText("");
        lblProductMadeDate.setText("");
        lblProductUnitPrice.setText("");
        lblProductQtyOnHand.setText("");
        txtOrderProductQty.clear();

    }
}
