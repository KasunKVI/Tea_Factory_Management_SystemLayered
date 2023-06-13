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
import lk.ijse.pos.bo.custom.AddProductBO;
import lk.ijse.pos.dto.ProductDTO;
import lk.ijse.pos.dto.Stock_ProductDTO;
import lk.ijse.pos.view.tdm.ProductTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ViewProductFormController implements Initializable {

    //components from product manage dashboard
    @FXML
    private TableView<ProductTM> allProductDetails;
    @FXML
    private TableColumn<?,?> clmProductId;
    @FXML
    private TableColumn<?,?> clmProductMadeDate;
    @FXML
    private TableColumn<?,?> clmProductStockId;
    @FXML
    private TableColumn<?,?> clmProductLeafValue;
    @FXML
    private TableColumn<?,?> clmProductType;
    @FXML
    private TableColumn<?,?> clmProductQuantity;

    Stage stage = new Stage();

   AddProductBO addProductBO = (AddProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADD_PRODUCT);
    //show add new product form
    public void addNewProduct(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/product_adding_form.fxml"));
        stage.setTitle("Product Adder");
        stage.setScene(new Scene(root));
        stage.show();

    }

    //show edit product form
    public void loadEditForm(String form) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/"+form+"_edit_form.fxml"));
        stage.setTitle(form+"  Edit");
        stage.setScene(new Scene(root));
        stage.show();

    }


    public void editProduct(ActionEvent actionEvent) throws IOException {

        loadEditForm("product");

    }

    //refresh product table
    public void refreshTable(MouseEvent mouseEvent) {

        getAll();
        allProductDetails.refresh();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setCellValueFactory();
        getAll();

    }

    // initialize table with all products
    private void getAll() {


        ObservableList<ProductTM> obList = FXCollections.observableArrayList();
        try {
            List<ProductDTO> pdList = addProductBO.getAllProducts();
            List<Stock_ProductDTO> stpList = addProductBO.getAllStock_Products();

            for (int i = 0; i < pdList.size() ; i++) {

                ProductDTO product = pdList.get(i);
                Stock_ProductDTO stock_product = stpList.get(i);

                obList.add(new ProductTM(
                        product.getId(),
                        String.valueOf(product.getMade_date()),
                        stock_product.getStock_id(),
                        stock_product.getLeaf_value(),
                        product.getType(),
                        product.getQty_on_hand()
                ));

            }
            allProductDetails.setItems(obList);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private void  setCellValueFactory() {

        clmProductId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        clmProductMadeDate.setCellValueFactory(new PropertyValueFactory<>("Made_date"));
        clmProductStockId.setCellValueFactory(new PropertyValueFactory<>("Stock_id"));
        clmProductLeafValue.setCellValueFactory(new PropertyValueFactory<>("Leaf_value"));
        clmProductType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        clmProductQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

    }
}
