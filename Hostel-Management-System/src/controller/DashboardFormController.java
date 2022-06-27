package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class DashboardFormController {
    public AnchorPane DashboardFormContext;
    public Label lblDate;
    public Label lblTime;

    public void btnRoomOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) DashboardFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ManageRoomForm.fxml"))));
        stage.centerOnScreen();
    }

    public void btnStudentOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) DashboardFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ManageStudentForm.fxml"))));
        stage.centerOnScreen();
    }

    public void btnUserOnAction(ActionEvent actionEvent) throws IOException {

    }

    public void btnGoBackOnAction(ActionEvent actionEvent) {

    }

    public void btnBookRoom(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) DashboardFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/MakeReservationForm.fxml"))));
        stage.centerOnScreen();
    }

    public void btnReservationDetail(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) DashboardFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ReservationDetailForm.fxml"))));
        stage.centerOnScreen();
    }
}
