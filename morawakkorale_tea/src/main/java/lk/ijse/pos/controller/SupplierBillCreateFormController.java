package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.LeafStockBO;
import lk.ijse.pos.bo.custom.PaymentBO;
import lk.ijse.pos.bo.custom.SupplierBO;
import lk.ijse.pos.dto.PaymentDTO;
import lk.ijse.pos.dto.SupplierDTO;
import lk.ijse.pos.dto.Supplier_BillDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Month;
import java.util.*;

public class SupplierBillCreateFormController implements Initializable {

    //components from supplier payment form
    @FXML
    private ComboBox<Month> cmbMonthSelect;

    @FXML
    private Label lblMonth;

    @FXML
    private Label lblSupBagCount;

    @FXML
    private Label lblSupLastLeafValue;

    @FXML
    private Label lblSupLastPayment;

    @FXML
    private Label lblSupTotalLeafValue;

    @FXML
    private Label lblSupplierId;

    @FXML
    private Label lblSupplierName;

    @FXML
    private TextField txtMonthlyRate;

    @FXML
    private TextField txtSupplierIdSearch;

    private boolean condition = true;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    LeafStockBO leafStockBO = (LeafStockBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LEAF_STOCK);

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    //show selected month all supplier payment details
    public void showReport(ActionEvent actionEvent) throws SQLException, FileNotFoundException, JRException {

        if(!condition||txtSupplierIdSearch.getText().isEmpty()||txtMonthlyRate.getText().isEmpty()||cmbMonthSelect.getValue()==null){

            new Alert(Alert.AlertType.ERROR, "Input Details First").show();

        }else {

            int month = cmbMonthSelect.getValue().getValue();

            ArrayList<String> supplierIds = supplierBO.getAllIds();
            List<Supplier_BillDTO> supplier = new ArrayList<>();

            for (String id : supplierIds) {

                int idd = Integer.parseInt(id);
                int totalValue = leafStockBO.getSupplierValues(Integer.parseInt(id), month, "value");
                int bagCount = leafStockBO.getSupplierValues(Integer.parseInt(id), month, "bag_count");
                int rate = Integer.parseInt(txtMonthlyRate.getText());
                Double payment = (double) ((totalValue - bagCount) * rate);

                supplier.add(new Supplier_BillDTO(
                        idd,
                        totalValue,
                        bagCount,
                        payment
                ));
            }

            File file = ResourceUtils.getFile("/home/kaviyakv/Desktop/Morawakkorale_Tea/morawakkorale_tea/src/main/resources/reports/supplierBills.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(supplier);
            Map<String, Object> map = new HashMap<>();
            map.put("CreatedBy", "Kasun Kavinda - GDSE65");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);
            JasperViewer.viewReport(jasperPrint, false);
        }
    }

    //search supplier from database with selected id
    public SupplierDTO searchedSupplierId(ActionEvent actionEvent) {

        if(!condition){

            new Alert(Alert.AlertType.ERROR, "Input Valid Details").show();

        }else {

            String id = txtSupplierIdSearch.getText();

            try {

                SupplierDTO supplier = supplierBO.searchSupplier(id);

                if (supplier == null) {

                    new Alert(Alert.AlertType.ERROR, "There is no supplier in this id").show();
                    FontChanger.setSearchBarRed(txtSupplierIdSearch);
                    txtSupplierIdSearch.requestFocus();
                }

                return supplier;
            } catch (SQLException throwable) {

                throwable.printStackTrace();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        return null;
    }

    //set supplier details to labels with selected month
    public void selectedMonthOfSupplier(ActionEvent actionEvent) {

        if(!condition||txtSupplierIdSearch.getText().isEmpty()){

            new Alert(Alert.AlertType.ERROR, "Input Id First").show();

        }else {


            SupplierDTO supplier = searchedSupplierId(actionEvent);

            int month = cmbMonthSelect.getValue().getValue();
            int id = Integer.parseInt(txtSupplierIdSearch.getText());

            try {

                int totalValue = leafStockBO.getSupplierValues(id, month, "value");
                int bagCount = leafStockBO.getSupplierValues(id, month, "bag_count");

                lblSupplierName.setText(supplier.getName());
                lblSupplierId.setText(String.valueOf(supplier.getId()));
                lblMonth.setText(String.valueOf(cmbMonthSelect.getValue()));
                lblSupTotalLeafValue.setText(String.valueOf(totalValue));
                lblSupBagCount.setText(String.valueOf(bagCount));
                lblSupLastLeafValue.setText(String.valueOf(totalValue - bagCount));

            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }

    }

    //initialize comboBox with months
    public void initializedMonthBox() {

        ObservableList<Month> obList = FXCollections.observableArrayList();
        List<Month> months = new ArrayList<>();

        for (int month = 1; month <= 12; month++) {

            months.add(Month.of(month));
        }

        for (Month month : months) {

            obList.add(month);
        }

        cmbMonthSelect.setItems(obList);

    }

    //add supplier payment to database
    public void correctSupplierMonthlyPayment(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if(!condition||txtSupplierIdSearch.getText().isEmpty()||cmbMonthSelect.getValue()==null||txtMonthlyRate.getText().isEmpty()||lblSupLastPayment.getText().equals("0.0")){
            new Alert(Alert.AlertType.ERROR, "Input Details First").show();
        }else {

            int pay = paymentBO.getPaymentId();
            int payId = pay + 1;

            PaymentDTO payment = new PaymentDTO(payId, Integer.parseInt(txtMonthlyRate.getText()), "Supplier Bill", Double.parseDouble(lblSupLastPayment.getText()), (lblSupplierName.getText()) + "-->" + (lblMonth.getText()), Integer.parseInt(lblSupplierId.getText()), null);
            try {

                int month = cmbMonthSelect.getValue().getValue();
                int id = Integer.parseInt(txtSupplierIdSearch.getText());

                paymentBO.addPayment(payment);
                leafStockBO.addPayment(id,month);
                ButtonType yes = new ButtonType("payment", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("report", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Supplier payment added successful. Do yo want to add another supplier payment or see report", yes, no).showAndWait();

                if (result.orElse(no) == yes) {

                    clearForm();

                }

            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }


    //validate supplier id from search bar
    public void searchSupplierId(KeyEvent keyEvent) {

        if (!txtSupplierIdSearch.getText().matches(Regex.idRegEx())){
            condition=false;
            FontChanger.setSearchBarRed(txtSupplierIdSearch);
        }else {
            FontChanger.setSearchBarBlack(txtSupplierIdSearch);
            condition = true;
            ActionEvent actionEvent=new ActionEvent();
            searchedSupplierId(actionEvent);
        }
    }

    //validate supplier rate
    public void enterMonthlySupplierRate(KeyEvent keyEvent) {

        if (!txtMonthlyRate.getText().matches(Regex.valueRegEx())){
            condition=false;
            FontChanger.setTextColorRed(txtMonthlyRate);
        }else {
            FontChanger.setTextBlack(txtMonthlyRate);
            condition = true;
            double last = Integer.parseInt(lblSupLastLeafValue.getText());
            double rate = Integer.parseInt(txtMonthlyRate.getText());
            lblSupLastPayment.setText(String.valueOf(rate * last));

        }
    }

    //check if month selected
    public void checkMonth(MouseEvent mouseEvent) {

        if(cmbMonthSelect.getValue()==null){
            new Alert(Alert.AlertType.ERROR, "Select Month First").show();
        }
        if(txtSupplierIdSearch.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Input Id First").show();
        }
    }

    //clear bill form
    public void clearForm(){

        txtSupplierIdSearch.clear();
        txtMonthlyRate.clear();
        lblSupplierName.setText("");
        lblSupplierId.setText("");
        lblSupTotalLeafValue.setText("");
        lblSupBagCount.setText("");
        lblSupLastLeafValue.setText("");
        lblSupLastPayment.setText("");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initializedMonthBox();

    }
}
