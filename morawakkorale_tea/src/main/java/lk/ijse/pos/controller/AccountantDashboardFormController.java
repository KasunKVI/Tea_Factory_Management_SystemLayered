package lk.ijse.pos.controller;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.AddProductBO;
import lk.ijse.pos.bo.custom.PlaceOrderBO;
import lk.ijse.pos.bo.custom.SupplierBO;
import lk.ijse.pos.bo.custom.TransporterBO;
import lk.ijse.pos.model.OrderModel;
import lk.ijse.pos.model.ProductModel;
import lk.ijse.pos.model.SupplierModel;
import lk.ijse.pos.model.TransporterModel;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.Month;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

public class AccountantDashboardFormController implements Initializable,MenuBarControl {



    //Components for accountant dashboard
    @FXML
    public AnchorPane bgPane;
    @FXML
    private Label lblSupplierCount;
    @FXML
    private Label lblTransportersCount;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private AreaChart<String,Number> orderSummeryAreaChart;
    @FXML
    private BarChart<String,Number> productSummeryBarChart;
    @FXML
    public Pane menuBarPanel;
    @FXML
    private Button btnLogOut;

    @FXML
    private ImageView menuImg;

    AddProductBO addProductBO = (AddProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADD_PRODUCT);
    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);
    TransporterBO transporterBO = (TransporterBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TRANSPORTER);

    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACE_ORDER);
    //load supplier bill form
    public void loadSupplierBills(ActionEvent actionEvent) throws IOException {

        bgPane.getChildren().clear();
        bgPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/suppliers_bill_get__form.fxml")));

    }


    //initialize bar chart, area chart and labels
    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {

            initializeAreaChart();
            initializeBarChart();
            lblSupplierCount.setText(String.valueOf(supplierBO.getSupplierCount()));
            lblTransportersCount.setText(String.valueOf(transporterBO.getTransporterCount()));
            initializeTimeLabel();

    }

    //initialize label with current time and date
    private void initializeTimeLabel() {

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e ->{
            LocalTime time = LocalTime.now();
            lblTime.setText(time.getHour()+":"+time.getMinute()+":"+time.getSecond());
        }), new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    //initialize bar chart
    private void initializeBarChart() throws SQLException, ClassNotFoundException {


        XYChart.Series<String, Number>[] series = new XYChart.Series[3];

        series[0] = new XYChart.Series<>();
        series[0].setName("English Afternoon");
        series[0].getData().add(new XYChart.Data<>("", addProductBO.getProductCount("English Afternoon - Green Tea")));

        series[1] = new XYChart.Series<>();
        series[1].setName("English Breakfast");
        series[1].getData().add(new XYChart.Data<>("", addProductBO.getProductCount("English Breakfast - Black Tea")));

        series[2] = new XYChart.Series<>();
        series[2].setName("Earl Grey");
        series[2].getData().add(new XYChart.Data<>("", addProductBO.getProductCount("Earl Grey Tea - Black Tea")));


        productSummeryBarChart.getData().addAll(series);

    }


    //initialize area chart
    private void initializeAreaChart() throws SQLException, ClassNotFoundException {

        XYChart.Series [] series = new XYChart.Series[12];

        for (int i = 0; i < 12; i++) {

            series[i] = new XYChart.Series<>();
            series[i].setName(String.valueOf(Month.of(i+1)));
            series[i].getData().add(new XYChart.Data<>(String.valueOf(Month.of(i+1)), placeOrderBO.getMonthlyOrderCount(i+1)));

        }

        orderSummeryAreaChart.getData().addAll(series);

    }

    //load supplier bill form
    public void loadTransporterBills(ActionEvent actionEvent) throws IOException {

        bgPane.getChildren().clear();
        bgPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/transporters_payment_get_form.fxml")));

    }

    //load add order form
    public void loadOrderAdder(ActionEvent actionEvent) throws IOException {

        bgPane.getChildren().clear();
        bgPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/orders_payment_add_form.fxml")));

    }

    //load dashboard back
    public void showDashboard(MouseEvent mouseEvent) throws IOException {

        bgPane.getChildren().clear();
        bgPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/clone_accountant_dashboard_form.fxml")));
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
