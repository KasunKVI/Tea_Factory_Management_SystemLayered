package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.SupplierBO;
import lk.ijse.pos.dto.SupplierDTO;
import lk.ijse.pos.utill.font.FontChanger;
import lk.ijse.pos.utill.regEx.Regex;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupplierManageFormController implements Initializable {


    //components from supplier add and edit forms
    @FXML
    private Button btnSupAdd;
    @FXML
    private Button btnEditedSupAdd;
    @FXML
    private TextField txtSupId;
    @FXML
    private TextField txtSupName;
    @FXML
    private TextField txtSupContact;
    @FXML
    private DatePicker dtpSupRegDate;
    @FXML
    private TextField txtSupAddress;

    @FXML
    private TextField txtSupplierIdSearch;
    @FXML
    private Label lblSupIdEdit;
    @FXML
    private TextField txtSupNameEdit;
    @FXML
    private TextField txtSupContactEdit;
    @FXML
    private TextField txtSupAddressEdit;
    @FXML
    private TextField txtSupRegDateEdit ;
    @FXML
    private TextField txtSupStatusEdit;

    private boolean condition = true;
    private int id;
    private String name;
    private String contact_no;
    private String address;

    private Stage stage = new Stage();

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    //add new supplier details to database
    public void addSupplierToDatabase(ActionEvent actionEvent) {

        if(!condition||txtSupId.getText().isEmpty()||txtSupName.getText().isEmpty()||txtSupContact.getText().isEmpty()||txtSupAddress.getText().isEmpty()||dtpSupRegDate.getValue()==null){

            new Alert(Alert.AlertType.ERROR, "please enter details first").show();

        }else {

            id = Integer.parseInt(txtSupId.getText());
            name = txtSupName.getText();
            contact_no = txtSupContact.getText();
            Date reg_date = Date.valueOf(dtpSupRegDate.getValue());
            address = txtSupAddress.getText();

            SupplierDTO supplier = new SupplierDTO(id, name, contact_no, reg_date, address, "New");

            try {

                supplierBO.addSupplier(supplier);
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Supplier added successful. Do yo want to add another supplier", yes, no).showAndWait();

                if (result.orElse(no) == yes) {

                    discardNewSupplierAdd(actionEvent);

                }else {

                    stage = (Stage) btnSupAdd.getScene().getWindow();
                    stage.close();
                }

            } catch (SQLException throwable) {

                throwable.printStackTrace();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }

    }

    //add edited supplier details to database
    public void addEditedSupplierToDatabase(ActionEvent actionEvent) {

        if(!condition||txtSupNameEdit.getText().isEmpty()||txtSupContactEdit.getText().isEmpty()||txtSupAddressEdit.getText().isEmpty()){

            new Alert(Alert.AlertType.ERROR, "Input Valid Details").show();

        }else {

            id = Integer.parseInt(lblSupIdEdit.getText());
            name = txtSupNameEdit.getText();
            contact_no = txtSupContactEdit.getText();
            address = txtSupAddressEdit.getText();

            SupplierDTO supplier = new SupplierDTO(id, name, contact_no, null, address, null);

            try {


                supplierBO.updateSupplier(supplier);

                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Supplier edited successful. Do yo want to edit another supplier", yes, no).showAndWait();

                if (result.orElse(no) == yes) {

                    discardEditSupplierValues(actionEvent);

                }else {

                    stage = (Stage) btnEditedSupAdd.getScene().getWindow();
                    stage.close();
                }

            } catch (SQLException throwable) {
                throwable.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    //search supplier details from database with selected id
    public void searchSupplierFromDatabase(ActionEvent actionEvent) throws IOException {

        if(!condition){
            new Alert(Alert.AlertType.ERROR, "Input Valid Id").show();
        }else {

            id = Integer.parseInt(txtSupplierIdSearch.getText());

            try {

                SupplierDTO supplier = supplierBO.searchSupplier(String.valueOf(id));

                if (supplier == null) {

                    new Alert(Alert.AlertType.ERROR, "There is no supplier in this id").show();
                    FontChanger.setSearchBarRed(txtSupplierIdSearch);
                    txtSupplierIdSearch.requestFocus();

                } else {

                    lblSupIdEdit.setText(String.valueOf(id));
                    txtSupNameEdit.setText(supplier.getName());
                    txtSupContactEdit.setText(supplier.getContact());
                    txtSupAddressEdit.setText(supplier.getAddress());
                    txtSupRegDateEdit.setText(String.valueOf(supplier.getReg_date()));
                    txtSupStatusEdit.setText(supplier.getStatus());

                }

            } catch (SQLException throwable) {
                throwable.printStackTrace();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    public void enteredSupplierId(ActionEvent actionEvent) {
        txtSupName.requestFocus();
    }

    public void enteredSupplierAllDetails(ActionEvent actionEvent) {
        addSupplierToDatabase(actionEvent);
    }

    public void enteredSupplierContact(ActionEvent actionEvent) {
        dtpSupRegDate.requestFocus();
    }

    public void enteredSupplierName(ActionEvent actionEvent) {
        txtSupContact.requestFocus();
    }

    public void enteredSupplierRegDate(ActionEvent actionEvent) {
        txtSupAddress.requestFocus();
    }

    //clear supplier edit form
    public void discardEditSupplierValues(ActionEvent actionEvent) {

        txtSupplierIdSearch.clear();
        lblSupIdEdit.setText("");
        txtSupNameEdit.clear();
        txtSupContactEdit.clear();
        txtSupAddressEdit.clear();
        txtSupStatusEdit.clear();
        txtSupRegDateEdit.clear();

    }

    //clear supplier add form
    public void discardNewSupplierAdd(ActionEvent actionEvent) {

        txtSupId.clear();
        txtSupName.clear();
        txtSupAddress.clear();
        txtSupContact.clear();
        dtpSupRegDate.setValue(null);

        txtSupId.requestFocus();

    }

    //validate supplier id
    public void enterSupId(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {

        if (!txtSupId.getText().matches(Regex.idRegEx())){
            condition=false;
            FontChanger.setTextColorRed(txtSupId);

        }else if(!supplierBO.existSupplier(txtSupId.getText())) {

            FontChanger.setTextColorRed(txtSupId);
            condition = false;

        }else {
            FontChanger.setTextBlack(txtSupId);
            condition = true;
        }
    }

    //validate supplier address
    public void enterSupAddress(KeyEvent keyEvent) {

        if (!txtSupAddress.getText().matches(Regex.addressRegEx())){
            condition=false;
            FontChanger.setTextColorRed(txtSupAddress);
        }else {
            FontChanger.setTextBlack(txtSupAddress);
            condition = true;
        }
    }

    //validate supplier contact
    public void enterSupContact(KeyEvent keyEvent) {

        if (!txtSupContact.getText().matches(Regex.contactRegEx())){
            condition=false;
            FontChanger.setTextColorRed(txtSupContact);
        }else {
            FontChanger.setTextBlack(txtSupContact);
            condition = true;
        }
    }

    //validate supplier name
    public void enterSupName(KeyEvent keyEvent) {

        if (!txtSupName.getText().matches(Regex.nameRegEx())){
            condition=false;
            FontChanger.setTextColorRed(txtSupName);
        }else {
            FontChanger.setTextBlack(txtSupName);
            condition = true;
        }
    }

    //validate edited supplier address
    public void enterSupplierAddressED(KeyEvent keyEvent) {

        if (!txtSupAddressEdit.getText().matches(Regex.addressRegEx())){
            condition=false;
            FontChanger.setTextColorRed(txtSupAddressEdit);
        }else {
            FontChanger.setTextBlack(txtSupAddressEdit);
            condition = true;
        }
    }

    public void enteredSupContactEdit(ActionEvent actionEvent) {
        txtSupAddressEdit.requestFocus();
    }

    //validate edited supplier contact
    public void enterSupplierContactED(KeyEvent keyEvent) {

        if (!txtSupContactEdit.getText().matches(Regex.contactRegEx())){
            condition=false;
            FontChanger.setTextColorRed(txtSupContactEdit);
        }else {
            FontChanger.setTextBlack(txtSupContactEdit);
            condition = true;
        }
    }

    public void enteredSupNameEdit(ActionEvent actionEvent) {
        txtSupContactEdit.requestFocus();
    }

    //validate edited supplier name
    public void enterSupplierNameED(KeyEvent keyEvent) {

        if (!txtSupNameEdit.getText().matches(Regex.nameRegEx())){
            condition=false;
            FontChanger.setTextColorRed(txtSupNameEdit);
        }else {
            FontChanger.setTextBlack(txtSupNameEdit);
            condition = true;
        }
    }

    //validate selected supplier id from search bar
    public void enterSupplierIdSearch(KeyEvent keyEvent) {

        if (!txtSupplierIdSearch.getText().matches(Regex.idRegEx())){
            condition=false;
            FontChanger.setTextColorRed(txtSupplierIdSearch);
        }else {
            FontChanger.setTextBlack(txtSupplierIdSearch);
            condition = true;
        }
    }
}
