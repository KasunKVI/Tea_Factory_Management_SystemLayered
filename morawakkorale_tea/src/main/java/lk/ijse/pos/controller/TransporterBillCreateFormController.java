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
import lk.ijse.pos.bo.custom.TransporterBO;
import lk.ijse.pos.dto.*;
import lk.ijse.pos.model.*;
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

public class TransporterBillCreateFormController implements Initializable {

    //components from transporter payments form
    @FXML
    private ComboBox<Month> cmbMonthSelect;

    @FXML
    private Label lblMonth;

    @FXML
    private Label lblTransporterId;


    @FXML
    private Label lblTransporterLastPayment;

    @FXML
    private Label lblTransporterName;

    @FXML
    private Label lblTransporterTotalLeafValue;

    @FXML
    private Label lblTransporterRoute;

    @FXML
    private TextField txtMonthlyRate;

    @FXML
    private TextField txtTransporterIdSearch;

    private boolean condition=true;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    TransporterBO transporterBO = (TransporterBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TRANSPORTER);

    LeafStockBO leafStockBO = (LeafStockBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LEAF_STOCK);

    //search transporter from database
    public TransporterDTO searchedTransporterId(ActionEvent actionEvent) {

        if(!condition){

            new Alert(Alert.AlertType.ERROR, "Input Valid Details").show();

        }else {

            String id = txtTransporterIdSearch.getText();

            try {

                TransporterDTO transporter = transporterBO.searchTransporter(id);

                if (transporter == null) {

                    new Alert(Alert.AlertType.ERROR, "There is no transporter in this id").show();
                    FontChanger.setSearchBarRed(txtTransporterIdSearch);
                    txtTransporterIdSearch.requestFocus();
                }

                return transporter;

            } catch (SQLException throwable) {

                throwable.printStackTrace();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
            return null;

    }

    //show transporters payment report from selected month
    public void showReport(ActionEvent actionEvent) throws SQLException, FileNotFoundException, JRException {

        if (!condition||txtTransporterIdSearch.getText().isEmpty()||txtMonthlyRate.getText().isEmpty()||cmbMonthSelect.getValue()==null){

            new Alert(Alert.AlertType.ERROR, "Input Details First").show();

        }else {

            int month = cmbMonthSelect.getValue().getValue();

            ArrayList<String> trpIds = transporterBO.getAllIds();
            ArrayList<TransporterBillDTO> transporter = new ArrayList<>();

            for (String id : trpIds) {

                int idd = Integer.parseInt(id);
                int totalValue = leafStockBO.getTransporterValues(idd,month);
                int rate = Integer.parseInt(txtMonthlyRate.getText());
                String route = transporterBO.getRoute(idd);
                Double payment = (double) (totalValue * rate);

                transporter.add(new TransporterBillDTO(
                        idd,
                        route,
                        totalValue,
                        payment
                ));

            }


            File file = ResourceUtils.getFile("/home/kaviyakv/Desktop/Morawakkorale_Tea/morawakkorale_tea/src/main/resources/reports/transporterBills.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(transporter);
            Map<String, Object> map = new HashMap<>();
            map.put("CreatedBy", "Kasun Kavinda - GDSE65");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);
            JasperViewer.viewReport(jasperPrint, false);
        }
    }

    //add transporter payment to database
    public void correctTransporterMonthlyPayment(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if(!condition||txtTransporterIdSearch.getText().isEmpty()||txtMonthlyRate.getText().isEmpty()||cmbMonthSelect.getValue()==null||lblTransporterLastPayment.getText().equals("0.0")){

            new Alert(Alert.AlertType.ERROR, "Input Details First").show();

        }else {
            int pay = paymentBO.getPaymentId();
            int payId = pay + 1;

            Integer tp_id = Integer.parseInt(lblTransporterId.getText());

            PaymentDTO payment = new PaymentDTO(payId, Integer.parseInt(txtMonthlyRate.getText()), "Transporter Bill", Double.parseDouble(lblTransporterLastPayment.getText()), (lblTransporterName.getText()) + "-->" + (lblMonth.getText()), null, tp_id);

            try {

                int month = cmbMonthSelect.getValue().getValue();
                int id = Integer.parseInt(txtTransporterIdSearch.getText());

                paymentBO.addPayment(payment);
                leafStockBO.addStockPayment(id,month);
                ButtonType yes = new ButtonType("payment", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("report", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Transporter payment added successful. Do yo want to add another supplier payment or see report", yes, no).showAndWait();

                if (result.orElse(no) == yes) {

                    clearForm();
                    txtTransporterIdSearch.requestFocus();

                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    //clear payment form
    private void clearForm() {

        txtMonthlyRate.clear();
        txtTransporterIdSearch.clear();

        lblTransporterId.setText("");
        lblTransporterName.setText("");
        lblTransporterRoute.setText("");
        lblTransporterLastPayment.setText("");
        lblTransporterTotalLeafValue.setText("");

    }

    //set values for labels with selected month
    public void selectedMonthOfTransporter(ActionEvent actionEvent) {

        if (!condition||txtTransporterIdSearch.getText().isEmpty()){

            new Alert(Alert.AlertType.ERROR, "Input Id First").show();

        }else {

            TransporterDTO transporter = searchedTransporterId(actionEvent);

            int month = cmbMonthSelect.getValue().getValue();
            int id = transporter.getId();


            try {

                int totalValue = leafStockBO.getTransporterValues(id,month);

                    lblTransporterId.setText(String.valueOf(transporter.getId()));
                    lblTransporterName.setText(transporter.getName());
                    lblMonth.setText(String.valueOf(cmbMonthSelect.getValue()));
                    lblTransporterTotalLeafValue.setText(String.valueOf(totalValue));
                    lblTransporterRoute.setText(transporter.getRoute());


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

    //validate transporter monthly rate
    public void enterTransporterMonthlyRate(KeyEvent keyEvent) {

        if (!txtMonthlyRate.getText().matches(Regex.valueRegEx())){
            condition=false;
            FontChanger.setTextColorRed(txtMonthlyRate);
        }else {
            FontChanger.setTextBlack(txtMonthlyRate);
            condition = true;
            double rate = Double.parseDouble(txtMonthlyRate.getText());
            double totalValue = Double.parseDouble(lblTransporterTotalLeafValue.getText());
            lblTransporterLastPayment.setText(String.valueOf(rate * totalValue));

        }
    }

    //validate transporter id
    public void enterTransporterIdSearch(KeyEvent keyEvent) {

        if (!txtTransporterIdSearch.getText().matches(Regex.idRegEx())){
            condition=false;
            FontChanger.setSearchBarRed(txtTransporterIdSearch);
        }else {
            FontChanger.setSearchBarBlack(txtTransporterIdSearch);
            condition = true;
            ActionEvent actionEvent=new ActionEvent();
            searchedTransporterId(actionEvent);
        }
    }

    //check if month selected or not
    public void checkMonth(MouseEvent mouseEvent) {

        if(cmbMonthSelect.getValue()==null){
            new Alert(Alert.AlertType.ERROR, "Select Month First").show();
        }
        if(txtTransporterIdSearch.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Input Id First").show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initializedMonthBox();
    }
}
