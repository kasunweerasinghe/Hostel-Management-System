package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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

public class ManageStudentFormController {
    public AnchorPane ManageStudentFormContext;
    public Label lblDate;
    public Label lblTime;
    public JFXButton btnSave;
    public JFXTextField txtStudentID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXDatePicker txtDate;
    public JFXComboBox cmbGender;
    public JFXButton btnDelete;
    public TableView tblStudent;
    public TableColumn colStudentID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDOB;
    public TableColumn colGender;



    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }



    public void btnGoBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) ManageStudentFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
        stage.centerOnScreen();
    }
}
