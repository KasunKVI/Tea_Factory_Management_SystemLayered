package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.LogInBO;
import lk.ijse.pos.dto.LogInDTO;
import lk.ijse.pos.model.LogInModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class LoginFormController {

    //components from loi in form
    @FXML
    private TextField txtUserId;
    @FXML
    private TextField txtPassword;


    @FXML
    private Button btnLogin;

    public static String id;

    LogInBO logInBO= (LogInBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOG_IN);

    //load dashboard using username and password
    public void loginBtnOnAction() throws IOException, SQLException {

        id= txtUserId.getText();
        String password = txtPassword.getText();
        Stage stage = new Stage();

        LogInDTO login=new LogInDTO(id,password);

       String pass =  logInBO.checkUser(login);


        if ( password.equals(pass)){

            if(id.equals("A001")) {

                Parent root = FXMLLoader.load(getClass().getResource("/view/collector_dashboard_form.fxml"));
                stage.setTitle("Collector Dashboard");
                stage.setScene(new Scene(root));

            }else if (id.equals("A002")){


                Parent root = FXMLLoader.load(getClass().getResource("/view/manager_dashboard_form.fxml"));
                stage.setTitle("Manager Dashboard");
                stage.setScene(new Scene(root));


             } else if (id.equals("A003") ){

                Parent root = FXMLLoader.load(getClass().getResource("/view/accountant_dashboard_form.fxml"));
                stage.setTitle("Accountant Dashboard");
                stage.setScene(new Scene(root));


             } else if (id.equals("A004") ) {

                Parent root = FXMLLoader.load(getClass().getResource("/view/general_manager_dashboard_form.fxml"));
                stage.setTitle("General Manager Dashboard");
                stage.setScene(new Scene(root));
            }

            stage.centerOnScreen();
            stage.show();
            Stage st = new Stage();
            st =(Stage) btnLogin.getScene().getWindow();
            st.close();


        }else{

            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Password or User Id incorrect. Do you want enter again?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {

                txtUserId.requestFocus();

            }else {
                System.exit(0);
            }
        }



    }

    public void doneUserId(ActionEvent actionEvent) {

        txtPassword.requestFocus();

    }

    public void doneUserIdAndPassword(ActionEvent actionEvent) throws IOException, SQLException {

        loginBtnOnAction();

    }

    //validate user id
    public void checkId(KeyEvent keyEvent) {

        if (!txtUserId.getText().matches(Regex.userName())){
            FontChanger.setTextColorRed(txtUserId);
        }else {
            FontChanger.setTextBlack(txtUserId);

        }
    }
}
