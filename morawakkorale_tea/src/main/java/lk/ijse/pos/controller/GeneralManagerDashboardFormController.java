package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.PlaceOrderBO;
import lombok.SneakyThrows;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class GeneralManagerDashboardFormController implements Initializable ,MenuBarControl{

    //components from general manager dashboard
    @FXML
    public Pane menuBarPanel;

    @FXML
    private PieChart chartProductSales;

    @FXML
    private AnchorPane bgPane;

    @FXML
    private Button btnLogOut;

    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACE_ORDER);

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initializeLineChart();
    }

    //initialize pie chart
    private void initializeLineChart() throws SQLException {

        chartProductSales.getData().add(new PieChart.Data("English Afternoon", placeOrderBO.getSales("English Afternoon - Green Tea")));
        chartProductSales.getData().add(new PieChart.Data("Earl Grey Tea", placeOrderBO.getSales("Earl Grey Tea - Black Tea")));
        chartProductSales.getData().add(new PieChart.Data("English Breakfast",placeOrderBO.getSales("English Breakfast - Black Tea")));

    }

    //load customer manage form
    public void customerFormLoad(ActionEvent actionEvent) throws IOException {


            bgPane.getChildren().clear();
            bgPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/view-customers-form.fxml")));

    }

    //load transporter manage form or transporter payment form
    public void transportersFormLoad(ActionEvent actionEvent) throws IOException {

        ButtonType manage = new ButtonType("Manage", ButtonBar.ButtonData.YES);
        ButtonType bill = new ButtonType("Account", ButtonBar.ButtonData.YES);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Did you want to load Manage section or Account section", manage, bill).showAndWait();

        if (result.orElse(null)==bill) {

            bgPane.getChildren().clear();
            bgPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/transporters_payment_get_form.fxml")));


        }else{
            bgPane.getChildren().clear();
            bgPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/transporters_manage_form.fxml")));


        }

    }
    //load supplier manage form or supplier payment form
    public void supplierFormLoad(ActionEvent actionEvent) throws IOException {

        ButtonType manage = new ButtonType("Manage", ButtonBar.ButtonData.YES);
        ButtonType bill = new ButtonType("Account", ButtonBar.ButtonData.YES);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Did you want to load Manage section or Account section", manage, bill).showAndWait();

        if (result.orElse(null)==bill) {

            bgPane.getChildren().clear();
            bgPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/suppliers_bill_get__form.fxml")));


        }else{
            bgPane.getChildren().clear();
            bgPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/suppliers_manage_form.fxml")));


        }


    }

    //load product manage form or order payment form
    public void productFormLoad(ActionEvent actionEvent) throws IOException {

        ButtonType manage = new ButtonType("Manage", ButtonBar.ButtonData.YES);
        ButtonType bill = new ButtonType("Order", ButtonBar.ButtonData.YES);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Did you want to load Manage section or Order section", manage, bill).showAndWait();

        if (result.orElse(null)==bill) {

            bgPane.getChildren().clear();
            bgPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/orders_payment_add_form.fxml")));


        }else{
            bgPane.getChildren().clear();
            bgPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/product_manage_form.fxml")));


        }
    }

    //show main dashboard back
    public void showDashboard(MouseEvent mouseEvent) throws IOException {

        bgPane.getChildren().clear();
        bgPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/clone_general_manager_dashboard_form.fxml")));
    }

    /*these five  methods use for operate menu bar*/
    @Override
    public void hideMenuBar(MouseEvent mouseEvent) {

        SideBarOperations.hideMenuBar(menuBarPanel);

    }

    @Override
    public void showMenuBar(MouseEvent mouseEvent) {

        SideBarOperations.showMenuBar(menuBarPanel);

    }

    @Override
    public void exitSystem(MouseEvent mouseEvent) {
        System.exit(0);
    }

    @Override
    public void openSettingForm(MouseEvent mouseEvent) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/setting_form.fxml"));
        stage.setTitle("Setting Window");
        stage.centerOnScreen();
        stage.setScene(new Scene(root));

        stage.show();
    }

    @Override
    public void logOut(ActionEvent actionEvent) throws IOException {

        SideBarOperations.logOut(btnLogOut);

    }
}
