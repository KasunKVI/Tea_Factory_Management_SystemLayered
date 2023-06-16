package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.TransporterBO;
import lk.ijse.pos.dto.TransporterDTO;

import java.sql.SQLException;
import java.util.Optional;

public class TransporterManageFormController {

    //Components from supplier adding form
    @FXML
    private TextField txtTrpId;
    @FXML
    private TextField txtTrpName;
    @FXML
    private TextField txtTrpContact;
    @FXML
    private TextField txtTrpRoute;
    @FXML
    private TextField txtTrpAddress;

    @FXML
    private TextField txtTransporterIdSearch;
    @FXML
    private Label lblTransporterIdEdit;
    @FXML
    private TextField txtTransporterNameEdit;
    @FXML
    private TextField txtTransporterContactEdit;
    @FXML
    private TextField txtTransporterAddressEdit;
    @FXML
    private TextField txtTransporterRouteEdit;

    @FXML
    private Button btnTrpAdd;
    @FXML
    private Button btnTransporterAdd;

    private int id;
    private String name;
    private String contact_no;
    private String address;
    private String route;

    private boolean condition = true;
    private Stage stage = new Stage();

    TransporterBO transporterBO = (TransporterBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TRANSPORTER);

    //add new transporter to database
    public void addTransporterToDatabase(ActionEvent actionEvent) {

        if (!condition||txtTrpId.getText().isEmpty()||txtTrpName.getText().isEmpty()||txtTrpContact.getText().isEmpty()||txtTrpRoute.getText().isEmpty()||txtTrpAddress.getText().isEmpty()){

            new Alert(Alert.AlertType.ERROR, "Please enter valid details").show();

        }else {

            id = Integer.parseInt(txtTrpId.getText());
            name = txtTrpName.getText();
            contact_no = txtTrpContact.getText();
            route = txtTrpRoute.getText();
            address = txtTrpAddress.getText();

            TransporterDTO transporter = new TransporterDTO(id, name, contact_no, route, address);

            try {

                transporterBO.addTransporter(transporter);
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Transporter added successful. Do yo want to add another transporter", yes, no).showAndWait();

                if (result.orElse(no) == yes) {

                    discardAddTransporterForm(actionEvent);

                }else {

                    stage = (Stage) btnTrpAdd.getScene().getWindow();
                    stage.close();
                }

            } catch (SQLException throwable) {

                throwable.printStackTrace();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }


    //add edited transporter details to database
    public void addEditedTransportersToDatabase(ActionEvent actionEvent) {

        if (!condition||txtTransporterNameEdit.getText().isEmpty()||txtTransporterContactEdit.getText().isEmpty()||txtTransporterRouteEdit.getText().isEmpty()||txtTransporterAddressEdit.getText().isEmpty()||txtTransporterIdSearch.getText().isEmpty()){

            new Alert(Alert.AlertType.ERROR, "Please enter correct details").show();

        }else {

            id = Integer.parseInt(lblTransporterIdEdit.getText());
            name = txtTransporterNameEdit.getText();
            contact_no = txtTransporterContactEdit.getText();
            address = txtTransporterAddressEdit.getText();
            route = txtTransporterRouteEdit.getText();

            TransporterDTO transporter = new TransporterDTO(id, name, contact_no, route, address);

            try {
                transporterBO.updateTransporter(transporter);
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Transporter edited successful. Do yo want to edit another transporter", yes, no).showAndWait();

                if (result.orElse(no) == yes) {

                    discardTransporterEdit(actionEvent);

                }else {

                    stage = (Stage) btnTransporterAdd.getScene().getWindow();
                    stage.close();
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //search transporter from database with selected id
    public void searchTransporterFromDatabase(ActionEvent actionEvent) {

        if(!condition){
            new Alert(Alert.AlertType.ERROR, "Input Valid Id").show();
        }else {

            id = Integer.parseInt(txtTransporterIdSearch.getText());

            try {
                TransporterDTO transporter = transporterBO.searchTransporter(String.valueOf(id));

                if (transporter == null) {

                    new Alert(Alert.AlertType.ERROR, "There is no transporter in this id").show();
                    FontChanger.setSearchBarRed(txtTransporterIdSearch);
                    txtTransporterIdSearch.requestFocus();

                } else {


                    lblTransporterIdEdit.setText(String.valueOf(id));
                    txtTransporterNameEdit.setText(transporter.getName());
                    txtTransporterContactEdit.setText(transporter.getContact());
                    txtTransporterAddressEdit.setText(transporter.getAddress());
                    txtTransporterRouteEdit.setText(transporter.getRoute());

                }


            } catch (SQLException throwable) {
                throwable.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    //clear transporter add form
    public void discardAddTransporterForm(ActionEvent actionEvent) {

        txtTrpId.clear();
        txtTrpName.clear();
        txtTrpAddress.clear();
        txtTrpContact.clear();
        txtTrpRoute.clear();

    }

    //clear transporter edit form
    public void discardTransporterEdit(ActionEvent actionEvent) {

        txtTransporterIdSearch.clear();
        txtTransporterNameEdit.clear();
        lblTransporterIdEdit.setText("");
        txtTransporterAddressEdit.clear();
        txtTransporterContactEdit.clear();
        txtTransporterRouteEdit.clear();

    }

    public void enteredTransporterEditedAddress(ActionEvent actionEvent) {
        txtTransporterRouteEdit.requestFocus();
    }

    public void enteredTransporterEditedContact(ActionEvent actionEvent) {
        txtTransporterAddressEdit.requestFocus();
    }

    public void enteredTransporterEditedName(ActionEvent actionEvent) {
        txtTransporterContactEdit.requestFocus();
    }

    public void enteredTransporterEditedRoute(ActionEvent actionEvent) {
        addEditedTransportersToDatabase(actionEvent);
    }

    public void enteredTransporterId(ActionEvent actionEvent) {
        txtTrpName.requestFocus();
    }

    public void enteredTransporterAddress(ActionEvent actionEvent) {
        addTransporterToDatabase(actionEvent);
    }

    public void enteredTransporterContact(ActionEvent actionEvent) {
        txtTrpRoute.requestFocus();
    }

    public void enteredTransporterName(ActionEvent actionEvent) {
        txtTrpContact.requestFocus();
    }

    public void enteredTransporterRoute(ActionEvent actionEvent) {
        txtTrpAddress.requestFocus();
    }

    //validate transporter id
    public void enterTransporterId(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {

        if (!txtTrpId.getText().matches(Regex.idRegEx())) {
            condition = false;
            FontChanger.setTextColorRed(txtTrpId);

        } else if(!transporterBO.exist(txtTrpId.getText())) {
                FontChanger.setTextColorRed(txtTrpId);
                condition = false;
        }else {
            FontChanger.setTextBlack(txtTrpId);
            condition = true;
        }
    }

    //validate transporter address
    public void enterTransporterAddress(KeyEvent keyEvent) {

        if (!txtTrpAddress.getText().matches(Regex.addressRegEx())){
            condition =false;
            FontChanger.setTextColorRed(txtTrpAddress);
        }else {
            FontChanger.setTextBlack(txtTrpAddress);
            condition = true;
        }
    }

    //validate transporter contact
    public void enterTransporterContact(KeyEvent keyEvent) {

        if (!txtTrpContact.getText().matches(Regex.contactRegEx())){
            condition =false;
            FontChanger.setTextColorRed(txtTrpContact);
        }else {
            FontChanger.setTextBlack(txtTrpContact);
            condition = true;
        }
    }

    //validate transporter name
    public void enterTransporterName(KeyEvent keyEvent) {

        if (!txtTrpName.getText().matches(Regex.nameRegEx())) {
            condition = false;
            FontChanger.setTextColorRed(txtTrpName);
        } else{
            FontChanger.setTextBlack(txtTrpName);
            condition = true;
         }
    }
    //validate transporter route
    public void enterTransporterRoute(KeyEvent keyEvent) {

        if (!txtTrpRoute.getText().matches(Regex.routeRegEx())){
            condition =false;
            FontChanger.setTextColorRed(txtTrpRoute);
        }else {
            FontChanger.setTextBlack(txtTrpRoute);
            condition = true;
        }
    }

    //validate edited transporter contact
    public void enterTransporterContactED(KeyEvent keyEvent) {
        if (!txtTransporterContactEdit.getText().matches(Regex.contactRegEx())){
            condition =false;
            FontChanger.setTextColorRed(txtTransporterContactEdit);
        }else {
            FontChanger.setTextBlack(txtTransporterContactEdit);
            condition = true;
        }
    }

    //validate edited transporter name
    public void enterTransporterNameED(KeyEvent keyEvent) {

        if (!txtTransporterNameEdit.getText().matches(Regex.nameRegEx())){
            condition =false;
            FontChanger.setTextColorRed(txtTransporterNameEdit);
        }else {
            FontChanger.setTextBlack(txtTransporterNameEdit);
            condition = true;
        }
    }

    //validate edited transporter route
    public void enterTransporterRouteED(KeyEvent keyEvent) {

        if (!txtTransporterRouteEdit.getText().matches(Regex.routeRegEx())){
            condition =false;
            FontChanger.setTextColorRed(txtTransporterRouteEdit);
        }else {
            FontChanger.setTextBlack(txtTransporterRouteEdit);
            condition = true;
        }
    }

    //validate edited transporter address
    public void enterTransporterAddressED(KeyEvent keyEvent) {

        if (!txtTransporterAddressEdit.getText().matches(Regex.addressRegEx())){
            condition =false;
            FontChanger.setTextColorRed(txtTransporterAddressEdit);
        }else {
            FontChanger.setTextBlack(txtTransporterAddressEdit);
            condition = true;
        }
    }

    //validate transporter id from search bar
    public void enterTransporterIdSearchED(KeyEvent keyEvent) {

        if (!txtTransporterIdSearch.getText().matches(Regex.idRegEx())){
            condition =false;
            FontChanger.setTextColorRed(txtTransporterIdSearch);
        }else {
            FontChanger.setTextBlack(txtTransporterIdSearch);
            condition = true;
        }

    }
}
