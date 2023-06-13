package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.LeafStockBO;
import lk.ijse.pos.bo.custom.PaymentBO;
import lk.ijse.pos.bo.custom.TransporterBO;
import lk.ijse.pos.dto.TransporterDTO;
import lk.ijse.pos.view.tdm.TransporterTM;
import lk.ijse.pos.model.PaymentModel;
import lk.ijse.pos.model.StockModel;
import lk.ijse.pos.model.TransporterModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewTransporterFormController implements Initializable {

    private Stage stage = new Stage();

    //components from transporter manage form
    @FXML
    private TableView<TransporterTM> allTransportersDetails;

    @FXML
    private TableColumn<?,?> clmTrpId;
    @FXML
    private TableColumn<?,?> clmTrpName;
    @FXML
    private TableColumn<?,?> clmTrpContact;
    @FXML
    private TableColumn<?,?> clmTrpRoute;
    @FXML
    private TableColumn<?,?> clmTrpAddress;

    TransporterBO transporterBO = (TransporterBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TRANSPORTER);

    LeafStockBO leafStockBO = (LeafStockBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LEAF_STOCK);

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    //show add new transporter form
    public void addNewTransporter(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/transporter_adding_form.fxml"));
        stage.setTitle("Transporter Adder");
        stage.setScene(new Scene(root));
        stage.show();
        Scene scene = root.getScene();
        scene.getStylesheets().add("/cssStyle/textRedStyle.css");

    }

    //show edit transporter form
    public void loadEditForm(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/transporter_edit_form.fxml"));
        stage.setTitle("Transporter  Edit");
        stage.setScene(new Scene(root));
        stage.show();
        Scene scene = root.getScene();
        scene.getStylesheets().add("/cssStyle/textRedStyle.css");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setCellValueFactory();
        getAll();

    }
    private void setCellValueFactory() {

        clmTrpId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        clmTrpName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        clmTrpContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        clmTrpRoute.setCellValueFactory(new PropertyValueFactory<>("Route"));
        clmTrpAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));


    }

    //get all transporters from database and initialize table with those details
    void getAll() {
        try {
            ObservableList<TransporterTM> obList = FXCollections.observableArrayList();
            ArrayList<TransporterDTO> trpList = transporterBO.getAllTransporters();

            for(TransporterDTO transporter : trpList) {
                obList.add(new TransporterTM(
                        transporter.getId(),
                        transporter.getName(),
                        transporter.getContact(),
                        transporter.getRoute(),
                        transporter.getAddress()
                ));
            }
            allTransportersDetails.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //refresh table view
    public void refreshTable(MouseEvent mouseEvent) {

        getAll();
        allTransportersDetails.refresh();
    }

    //delete transporter detail from database
    public void deleteTransporterFromDatabase(ActionEvent actionEvent) {

        int selectedID=allTransportersDetails.getSelectionModel().getSelectedIndex();

        TransporterTM transporterTM = allTransportersDetails.getSelectionModel().getSelectedItem();

        int transporterId = transporterTM.getId();

        allTransportersDetails.getItems().remove(selectedID);

        try {

            String pay_id = transporterBO.getTransporterPaymentId(transporterId);
            leafStockBO.deleteTransporterDetails(transporterId);
            transporterBO.delete(String.valueOf(transporterId));

            if(pay_id!=null){

                //PaymentModel.deleteTransporterPaymentFromDatabase(pay_id);
                paymentBO.deletePayment(pay_id);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
