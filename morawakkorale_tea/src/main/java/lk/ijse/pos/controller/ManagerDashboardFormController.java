package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.AddProductBO;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.bo.custom.SupplierBO;
import lk.ijse.pos.bo.custom.TransporterBO;
import lombok.SneakyThrows;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManagerDashboardFormController implements Initializable,MenuBarControl {

    //components from manager dashboard
    @FXML
    private AnchorPane backgroundPane;
    @FXML
    private Pane menuBarPanel;
    @FXML
    private BarChart<String,Number> suppliersChart;

    //available stock pane components
    @FXML
    private Label lblEnglishAfternoonCount;
    @FXML
    private Label lblEarlGreyTeaCount;
    @FXML
    private Label lblEnglishBreakfastTeaCount;

    //company summary pane components
    @FXML
    private Label lblSuppliersCount;
    @FXML
    private Label lblTransportersCount;
    @FXML
    private Label lblCustomersCount;

    @FXML
    private ComboBox <Year> cmbSupplierYear;


    @FXML
    private Button btnLogOut;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);
    TransporterBO transporterBO = (TransporterBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TRANSPORTER);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    AddProductBO addProductBO = (AddProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADD_PRODUCT);
    //show supplier manage window
    public void showSupplierManage(ActionEvent actionEvent) throws IOException {

        backgroundPane.getChildren().clear();
        backgroundPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/suppliers_manage_form.fxml")));

    }

    //show customer manage window
    public void showBuyersManage(ActionEvent actionEvent) throws IOException {

        backgroundPane.getChildren().clear();
        backgroundPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/view-customers-form.fxml")));

    }

    //show transporter manage window
    public void showTransportersManage(ActionEvent actionEvent) throws IOException {

        backgroundPane.getChildren().clear();
        backgroundPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/transporters_manage_form.fxml")));

    }

    //show product manage window
    public void showProductManage(ActionEvent actionEvent) throws IOException {

        backgroundPane.getChildren().clear();
        backgroundPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/product_manage_form.fxml")));

    }

    //load manager dashboard back
    public void showDashboard(MouseEvent mouseEvent) throws IOException {

        backgroundPane.getChildren().clear();
        backgroundPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/clone_manager_dashboard_form.fxml")));

    }



    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        updatePanes();
        updateBarChart();
        loadSupplierYears();

    }

    //initialize bar chart using all years
    private void updateBarChart() throws SQLException, ClassNotFoundException {

        XYChart.Series<String, Number>[] series1 = new XYChart.Series[24];
        String year="";

        for (int i = 0; i < 24; i++) {

            if(i<10) {
                year="200"+i;
            }else {
                year="20"+i;
            }
            series1[i] = new XYChart.Series<>();
            series1[i].setName(year);
            series1[i].getData().add(new XYChart.Data<>("", supplierBO.getNewSupplierCount(year)));
        }

        suppliersChart.getData().addAll(series1);

    }

    //initialize combo box
    private void loadSupplierYears() {

            ObservableList<Year> obList = FXCollections.observableArrayList();
            List<Year> years = new ArrayList<>();

            for (int year = 2000; year <= 2023; year++) {

                years.add(Year.of(year));
            }

            for (Year year : years) {

                obList.add(year);
            }

            cmbSupplierYear.setItems(obList);

    }

    //load values to labels
    public void updatePanes() throws SQLException, ClassNotFoundException {

        int supplier_count = supplierBO.getSupplierCount();
        int transporters_count = transporterBO.getTransporterCount();
        int customers_count = customerBO.getCustomerCount();

        lblSuppliersCount.setText(String.valueOf(supplier_count));
        lblTransportersCount.setText(String.valueOf(transporters_count));
        lblCustomersCount.setText(String.valueOf(customers_count));

        int englishAfternoonCount = addProductBO.getProductCount("English Afternoon - Green Tea");
        int earlGreyCount = addProductBO.getProductCount("Earl Grey Tea - Black Tea");
        int englishBreakfastCount = addProductBO.getProductCount("English Breakfast - Black Tea");

        lblEnglishAfternoonCount.setText(String.valueOf(englishAfternoonCount));
        lblEarlGreyTeaCount.setText(String.valueOf(earlGreyCount));
        lblEnglishBreakfastTeaCount.setText(String.valueOf(englishBreakfastCount));

    }

    //initialize bar chart using selected year
    public void initializeChartUsingYear(ActionEvent actionEvent) throws SQLException {

        suppliersChart.getData().clear();

        Year year = cmbSupplierYear.getValue();
        int yr = year.getValue();

        XYChart.Series<String, Number>[] series = new XYChart.Series[12];

        for (int i = 0; i < 12; i++) {

            series[i] = new XYChart.Series<>();
            series[i].setName(String.valueOf(Month.of(i+1)));
            series[i].getData().add(new XYChart.Data<>("", supplierBO.getNewSupplierCount(yr, i+1)));
        }

        suppliersChart.getData().addAll(series);


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



    /*these five  methods use for operate menu bar*/

    @Override
    public void logOut(ActionEvent actionEvent) throws IOException {

        SideBarOperations.logOut(btnLogOut);
    }

    @Override
    public void exitSystem(MouseEvent mouseEvent) {
        System.exit(0);
    }

    @Override
    public void hideMenuBar(MouseEvent mouseEvent) {

        SideBarOperations.hideMenuBar(menuBarPanel);

    }

    @Override
    public void showMenuBar(MouseEvent mouseEvent) {

        SideBarOperations.showMenuBar(menuBarPanel);

    }
}
