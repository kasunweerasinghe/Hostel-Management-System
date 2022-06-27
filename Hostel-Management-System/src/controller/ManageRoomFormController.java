package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageRoomFormController {
    public AnchorPane ManageRoomFormContext;
    public Label lblDate;
    public Label lblTime;
    public JFXTextField txtRoomID;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public JFXButton btnSave;
    public JFXComboBox cmbRoomType;
    public JFXButton btnDelete;
    public TableView tblRoom;
    public TableColumn colRoomID;
    public TableColumn colRoomType;
    public TableColumn colKeyMoney;
    public TableColumn colQty;


    public void textFields_Key_Released(KeyEvent keyEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }





    public void btnGoBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) ManageRoomFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
        stage.centerOnScreen();
    }
}
